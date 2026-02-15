package me.okcoe.rpg.resourcepack;

import com.google.gson.JsonObject;
import me.okcoe.rpg.RPGMod;
import me.okcoe.rpg.data.WeaponDefinition;
import me.okcoe.rpg.data.WeaponLoader;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResourcePackGenerator {
    public static void generate() {
        Path packPath = FabricLoader.getInstance().getGameDir().resolve("resourcepacks/rpg_generated");
        try {
            Files.createDirectories(packPath.resolve("assets/rpg/models/item"));
            Files.createDirectories(packPath.resolve("assets/rpg/textures/item"));

            createPackMcMeta(packPath);

            for (WeaponDefinition def : WeaponLoader.getWeapons()) {
                createItemModel(packPath, def);
                copyTexture(packPath, def);
            }

            RPGMod.LOGGER.info("Generated resource pack at {}", packPath);
        } catch (IOException e) {
            RPGMod.LOGGER.error("Failed to generate resource pack", e);
        }
    }

    private static void createPackMcMeta(Path packPath) throws IOException {
        JsonObject pack = new JsonObject();
        pack.addProperty("pack_format", 34); // Minecraft 1.21
        pack.addProperty("description", "RPG Framework Generated Pack");

        JsonObject root = new JsonObject();
        root.add("pack", pack);

        try (FileWriter writer = new FileWriter(packPath.resolve("pack.mcmeta").toFile())) {
            writer.write(root.toString());
        }
    }

    private static void createItemModel(Path packPath, WeaponDefinition def) throws IOException {
        JsonObject model = new JsonObject();
        model.addProperty("parent", "minecraft:item/handheld");

        JsonObject textures = new JsonObject();
        textures.addProperty("layer0", "rpg:item/" + def.id());
        model.add("textures", textures);

        File modelFile = packPath.resolve("assets/rpg/models/item/" + def.id() + ".json").toFile();
        try (FileWriter writer = new FileWriter(modelFile)) {
            writer.write(model.toString());
        }
    }

    private static void copyTexture(Path packPath, WeaponDefinition def) throws IOException {
        Path sourceTexture = FabricLoader.getInstance().getConfigDir().resolve("rpg/textures/" + def.texture());
        Path targetTexture = packPath.resolve("assets/rpg/textures/item/" + def.id() + ".png");

        if (Files.exists(sourceTexture)) {
            Files.copy(sourceTexture, targetTexture, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } else {
            RPGMod.LOGGER.warn("Texture not found: {}", sourceTexture);
        }
    }
}
