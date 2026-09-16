package io.github.jaxvanyang.simple_chess;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;

import static io.github.jaxvanyang.simple_chess.Const.MOD_ID;

public class Chess implements ModInitializer {

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
