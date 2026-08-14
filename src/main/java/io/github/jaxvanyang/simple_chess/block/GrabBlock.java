package io.github.jaxvanyang.simple_chess.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class GrabBlock extends Block {
    public GrabBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!player.getMainHandItem().isEmpty()) {
            return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
        }

        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
        player.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(this));

        return InteractionResult.SUCCESS;
    }
}
