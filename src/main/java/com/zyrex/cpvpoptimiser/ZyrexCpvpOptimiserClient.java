package com.zyrex.cpvpoptimiser;

import com.zyrex.cpvpoptimiser.config.ModConfig;
import com.zyrex.cpvpoptimiser.hud.HudRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZyrexCpvpOptimiserClient implements ClientModInitializer {
    public static final String MOD_ID = "zyrex_cpvp_optimiser";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ModConfig CONFIG = ModConfig.load();

    private static KeyBinding toggleHudKey;

    @Override
    public void onInitializeClient() {
        toggleHudKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.zyrex.toggle_hud",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            "category.zyrex.optimiser"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleHudKey.wasPressed()) {
                CONFIG.hudEnabled = !CONFIG.hudEnabled;
                ModConfig.save();
                LOGGER.info("HUD toggled: {}", CONFIG.hudEnabled);
            }
        });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> HudRenderer.render(drawContext, tickDelta));

        LOGGER.info("Zyrex_x Cpvp Optimiser initialized");
    }
}
