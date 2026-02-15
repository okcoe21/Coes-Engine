package me.okcoe.rpg.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.okcoe.rpg.RPGMod;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WeaponLoader {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final List<WeaponDefinition> WEAPONS = new ArrayList<>();

    public static void load() {
        WEAPONS.clear();
        Path configDir = FabricLoader.getInstance().getConfigDir().resolve("rpg/weapons");
        File dir = configDir.toFile();

        if (!dir.exists()) {
            dir.mkdirs();
            // Create a sample weapon
            createSampleWeapon(configDir);
        }

        File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
        if (files != null) {
            for (File file : files) {
                try (FileReader reader = new FileReader(file)) {
                    WeaponDefinition def = GSON.fromJson(reader, WeaponDefinition.class);
                    WEAPONS.add(def);
                    RPGMod.LOGGER.info("Loaded weapon: {}", def.id());
                } catch (IOException e) {
                    RPGMod.LOGGER.error("Failed to load weapon from {}", file.getName(), e);
                }
            }
        }
    }

    private static void createSampleWeapon(Path configDir) {
        WeaponDefinition sample = new WeaponDefinition(
                "ruby_sword",
                "sword",
                "ruby_sword.png",
                new WeaponDefinition.Stats(8.0, 1.6, 900),
                new WeaponDefinition.Abilities(
                        List.of("ignite:5"),
                        List.of("dash:2")));
        File sampleFile = configDir.resolve("ruby_sword.json").toFile();
        try (java.io.FileWriter writer = new java.io.FileWriter(sampleFile)) {
            GSON.toJson(sample, writer);
        } catch (IOException e) {
            RPGMod.LOGGER.error("Failed to create sample weapon", e);
        }
    }

    public static List<WeaponDefinition> getWeapons() {
        return WEAPONS;
    }
}
