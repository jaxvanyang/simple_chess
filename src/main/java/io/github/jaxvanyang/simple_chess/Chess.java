package io.github.jaxvanyang.simple_chess;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public class Chess implements ModInitializer {
    public static final String MOD_ID = "simple_chess";

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
