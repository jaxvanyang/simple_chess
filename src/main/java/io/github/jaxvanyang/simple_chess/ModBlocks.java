package io.github.jaxvanyang.simple_chess;

import io.github.jaxvanyang.simple_chess.block.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {
    public static final Block WHITE_BISHOP = register("white_bishop", Bishop::new, BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK));
    public static final Block WHITE_KING = register("white_king", King::new, BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK));
    public static final Block WHITE_KNIGHT = register("white_knight", Knight::new, BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK));
    public static final Block WHITE_PAWN = register("white_pawn", Pawn::new, BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK));
    public static final Block WHITE_QUEEN = register("white_queen", Queen::new, BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK));
    public static final Block WHITE_ROOK = register("white_rook", Rook::new, BlockBehaviour.Properties.copy(Blocks.QUARTZ_BLOCK));
    public static final Block BLACK_BISHOP = register("black_bishop", Bishop::new, BlockBehaviour.Properties.copy(Blocks.BLACKSTONE));
    public static final Block BLACK_KING = register("black_king", King::new, BlockBehaviour.Properties.copy(Blocks.BLACKSTONE));
    public static final Block BLACK_KNIGHT = register("black_knight", Knight::new, BlockBehaviour.Properties.copy(Blocks.BLACKSTONE));
    public static final Block BLACK_PAWN = register("black_pawn", Pawn::new, BlockBehaviour.Properties.copy(Blocks.BLACKSTONE));
    public static final Block BLACK_QUEEN = register("black_queen", Queen::new, BlockBehaviour.Properties.copy(Blocks.BLACKSTONE));
    public static final Block BLACK_ROOK = register("black_rook", Rook::new, BlockBehaviour.Properties.copy(Blocks.BLACKSTONE));
    public static final CreativeModeTab CHESS_TAB = FabricItemGroupBuilder.create(new ResourceLocation(Chess.MOD_ID, "chess_tab")).icon(() -> new ItemStack(WHITE_PAWN)).appendItems(stacks -> {
        // sorted by piece value
        stacks.add(new ItemStack(WHITE_PAWN));
        stacks.add(new ItemStack(WHITE_KNIGHT));
        stacks.add(new ItemStack(WHITE_BISHOP));
        stacks.add(new ItemStack(WHITE_ROOK));
        stacks.add(new ItemStack(WHITE_QUEEN));
        stacks.add(new ItemStack(WHITE_KING));
        stacks.add(new ItemStack(BLACK_PAWN));
        stacks.add(new ItemStack(BLACK_KNIGHT));
        stacks.add(new ItemStack(BLACK_BISHOP));
        stacks.add(new ItemStack(BLACK_ROOK));
        stacks.add(new ItemStack(BLACK_QUEEN));
        stacks.add(new ItemStack(BLACK_KING));
    }).build();

    public static void initialize() {
    }

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        Block block = blockFactory.apply(properties);
        ResourceLocation id = new ResourceLocation(Chess.MOD_ID, name);
        BlockItem blockItem = new BlockItem(block, new Item.Properties());
        Registry.register(Registry.ITEM, id, blockItem);

        return Registry.register(Registry.BLOCK, id, block);
    }
}
