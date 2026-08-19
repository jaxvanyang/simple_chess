package io.github.jaxvanyang.simple_chess.block;

import io.github.jaxvanyang.simple_chess.Chess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class GrabBlock extends Block {
    public static final TagKey<Block> PIECE_BLOCK = TagKey.create(Registry.BLOCK.key(), new ResourceLocation("simple_chess", "chess_piece"));
    public static final TagKey<Item> PIECE_ITEM = TagKey.create(Registry.ITEM.key(), new ResourceLocation("simple_chess", "chess_piece"));

    public GrabBlock(Properties properties) {
        super(properties);
    }

    private enum Action {
        GRAB, CAPTURE,
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack itemStack = player.getMainHandItem();
        Action action;
        if (interactionHand == InteractionHand.MAIN_HAND && itemStack.is(PIECE_ITEM) && itemStack.getCount() == 1 && blockState.is(PIECE_BLOCK)) {
            action = Action.CAPTURE;
        } else if (itemStack.isEmpty()) {
            action = Action.GRAB;
        } else {
            return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
        }

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        if (action == Action.CAPTURE) {
            if (!(itemStack.getItem() instanceof BlockItem blockItem)) {
                Chess.LOGGER.error("expected block item");
                return InteractionResult.FAIL;
            }

            Block block = blockItem.getBlock();
            BlockState state = block.defaultBlockState();
            if (block instanceof DirectionBlock) {
                state = state.setValue(DirectionBlock.FACING, player.getDirection());
            }

            level.setBlock(blockPos, state, Block.UPDATE_ALL);
            player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(this));
        } else {
            level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
            player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(this));
        }

        return InteractionResult.SUCCESS;
    }
}
