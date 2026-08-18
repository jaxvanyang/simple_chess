package io.github.jaxvanyang.simple_chess;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class Chess implements ModInitializer {
    public static final String MOD_ID = "simple_chess";
    public static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
