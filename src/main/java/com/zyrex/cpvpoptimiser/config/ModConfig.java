package com.zyrex.cpvpoptimiser.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("zyrex_cpvp_optimiser.json");

    public boolean hudEnabled = true;
    public boolean optimizerAnchor = true;
    public boolean optimizerCrystal = true;
    public boolean optimizerPearlCatch = true;
    public int crystalSpeed = 1;
    public int anchorSpeed = 1;

    public static ModConfig load() {
        ModConfig config = new ModConfig();

        if (Files.notExists(CONFIG_PATH)) {
            save(config);
            return config;
        }

        try {
            String raw = Files.readString(CONFIG_PATH);
            ModConfig loaded = GSON.fromJson(raw, ModConfig.class);
            if (loaded != null) {
                config = loaded;
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        config.normalize();
        save(config);
        return config;
    }

    public static void save() {
        save(com.zyrex.cpvpoptimiser.ZyrexCpvpOptimiserClient.CONFIG);
    }

    public static void save(ModConfig config) {
        config.normalize();

        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH, GSON.toJson(config));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void normalize() {
        hudEnabled = hudEnabled;
        optimizerAnchor = optimizerAnchor;
        optimizerCrystal = optimizerCrystal;
        optimizerPearlCatch = optimizerPearlCatch;
        crystalSpeed = clamp(crystalSpeed, 1, 4);
        anchorSpeed = clamp(anchorSpeed, 1, 4);
    }

    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
