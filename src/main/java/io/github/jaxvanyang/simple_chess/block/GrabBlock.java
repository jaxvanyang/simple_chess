package io.github.jaxvanyang.simple_chess.block;

import io.github.jaxvanyang.simple_chess.Chess;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
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
    public static final TagKey<Block> PIECE_BLOCK = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("simple_chess", "chess_piece"));
    public static final TagKey<Item> PIECE_ITEM = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simple_chess", "chess_piece"));

    public GrabBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (hand == InteractionHand.OFF_HAND || !(itemStack.is(PIECE_ITEM) && itemStack.getCount() == 1 && state.is(PIECE_BLOCK))) {
            return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
        }

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        if (!(itemStack.getItem() instanceof BlockItem blockItem)) {
            Chess.LOGGER.error("expected block item");
            return InteractionResult.FAIL;
        }

        Block block = blockItem.getBlock();
        BlockState blockState = block.defaultBlockState();
        if (block instanceof DirectionBlock) {
            blockState = blockState.setValue(DirectionBlock.FACING, player.getDirection());
        }

        level.setBlock(pos, blockState, Block.UPDATE_ALL);
        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(this));

        return InteractionResult.SUCCESS;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!player.getMainHandItem().isEmpty()) {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(this));

        return InteractionResult.SUCCESS;
    }
}
