package com.zyrex.cpvpoptimiser.hud;

import com.zyrex.cpvpoptimiser.ZyrexCpvpOptimiserClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public final class HudRenderer {
    private HudRenderer() {
    }

    public static void render(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null || !ZyrexCpvpOptimiserClient.CONFIG.hudEnabled) {
            return;
        }

        int x = 12;
        int y = 12;
        int width = 190;
        int height = 80;
        int bgColor = 0xB3000000;

        drawContext.fill(x - 5, y - 5, x + width, y + height, bgColor);
        drawContext.drawText(client.textRenderer, "Zyrex_x Cpvp Optimiser", x, y, 0xFF00F7FF, true);

        y += 12;
        drawContext.drawText(client.textRenderer, "Optimizer Anchor: " + (ZyrexCpvpOptimiserClient.CONFIG.optimizerAnchor ? "ON" : "OFF"), x, y, 0xFF66FF66, true);
        y += 12;
        drawContext.drawText(client.textRenderer, "Optimizer Crystal: " + (ZyrexCpvpOptimiserClient.CONFIG.optimizerCrystal ? "ON" : "OFF"), x, y, 0xFF66FF66, true);
        y += 12;
        drawContext.drawText(client.textRenderer, "Optimizer Pearl Catch: " + (ZyrexCpvpOptimiserClient.CONFIG.optimizerPearlCatch ? "ON" : "OFF"), x, y, 0xFF66FF66, true);
        y += 12;
        drawContext.drawText(client.textRenderer, "Crystal Speed: " + ZyrexCpvpOptimiserClient.CONFIG.crystalSpeed + "x", x, y, 0xFFFFD166, true);
        y += 12;
        drawContext.drawText(client.textRenderer, "Anchor Speed: " + ZyrexCpvpOptimiserClient.CONFIG.anchorSpeed + "x", x, y, 0xFFFFD166, true);
    }
}
