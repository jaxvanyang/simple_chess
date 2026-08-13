package io.github.jaxvanyang.simple_chess;

import io.github.jaxvanyang.simple_chess.block.*;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
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
    public static final ResourceKey<CreativeModeTab> CHESS_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Chess.MOD_ID, "chess_tab"));
    public static final CreativeModeTab CHESS_TAB = FabricCreativeModeTab.builder().icon(() -> new ItemStack(WHITE_PAWN)).title(Component.translatable("itemGroup.simple_chess")).displayItems((_, output) -> {
        // sort by piece value
        output.accept(WHITE_PAWN);
        output.accept(WHITE_KNIGHT);
        output.accept(WHITE_BISHOP);
        output.accept(WHITE_ROOK);
        output.accept(WHITE_QUEEN);
        output.accept(WHITE_KING);
        output.accept(BLACK_PAWN);
        output.accept(BLACK_KNIGHT);
        output.accept(BLACK_BISHOP);
        output.accept(BLACK_ROOK);
        output.accept(BLACK_QUEEN);
        output.accept(BLACK_KING);
    }).build();


    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CHESS_TAB_KEY, CHESS_TAB);
    }

    private static Block register(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        Block block = blockFactory.apply(properties.setId(id));

        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        Identifier identifier = Identifier.fromNamespaceAndPath(Chess.MOD_ID, name);
        BlockItemId id = BlockItemId.create(identifier, identifier);
        Block block = register(id.block(), blockFactory, properties);

        BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()));
        Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);

        return block;
    }
}
