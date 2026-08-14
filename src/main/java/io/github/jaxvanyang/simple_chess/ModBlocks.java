package io.github.jaxvanyang.simple_chess;

import io.github.jaxvanyang.simple_chess.block.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
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
    public static final Block WHITE_BISHOP = register("white_bishop", Bishop::new, BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK));
    public static final Block WHITE_KING = register("white_king", King::new, BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK));
    public static final Block WHITE_KNIGHT = register("white_knight", Knight::new, BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK));
    public static final Block WHITE_PAWN = register("white_pawn", Pawn::new, BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK));
    public static final Block WHITE_QUEEN = register("white_queen", Queen::new, BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK));
    public static final Block WHITE_ROOK = register("white_rook", Rook::new, BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK));
    public static final Block BLACK_BISHOP = register("black_bishop", Bishop::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
    public static final Block BLACK_KING = register("black_king", King::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
    public static final Block BLACK_KNIGHT = register("black_knight", Knight::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
    public static final Block BLACK_PAWN = register("black_pawn", Pawn::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
    public static final Block BLACK_QUEEN = register("black_queen", Queen::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
    public static final Block BLACK_ROOK = register("black_rook", Rook::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
    public static final ResourceKey<CreativeModeTab> CHESS_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), ResourceLocation.fromNamespaceAndPath(Chess.MOD_ID, "chess_tab"));
    public static final CreativeModeTab CHESS_TAB = FabricItemGroup.builder().icon(() -> new ItemStack(WHITE_PAWN)).title(Component.translatable("itemGroup.simple_chess")).build();


    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CHESS_TAB_KEY, CHESS_TAB);

        ItemGroupEvents.modifyEntriesEvent(CHESS_TAB_KEY).register(itemGroup -> {
            itemGroup.accept(WHITE_PAWN);
            itemGroup.accept(WHITE_KNIGHT);
            itemGroup.accept(WHITE_BISHOP);
            itemGroup.accept(WHITE_ROOK);
            itemGroup.accept(WHITE_QUEEN);
            itemGroup.accept(WHITE_KING);
            itemGroup.accept(BLACK_PAWN);
            itemGroup.accept(BLACK_KNIGHT);
            itemGroup.accept(BLACK_BISHOP);
            itemGroup.accept(BLACK_ROOK);
            itemGroup.accept(BLACK_QUEEN);
            itemGroup.accept(BLACK_KING);
        });
    }

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        Block block = blockFactory.apply(properties);
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Chess.MOD_ID, name);
        BlockItem blockItem = new BlockItem(block, new Item.Properties());
        Registry.register(BuiltInRegistries.ITEM, id, blockItem);

        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }
}
