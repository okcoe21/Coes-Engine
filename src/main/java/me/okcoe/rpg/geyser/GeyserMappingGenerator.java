package me.okcoe.rpg.geyser;

import com.google.gson.JsonObject;
import me.okcoe.rpg.RPGMod;
import me.okcoe.rpg.data.WeaponDefinition;
import me.okcoe.rpg.data.WeaponLoader;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GeyserMappingGenerator {
    public static void generate() {
        Path geyserPath = FabricLoader.getInstance().getGameDir().resolve("plugins/Geyser");
        Path bedrockPackPath = FabricLoader.getInstance().getGameDir().resolve("resourcepacks/bedrock_pack");

        try {
            Files.createDirectories(geyserPath);
            Files.createDirectories(bedrockPackPath.resolve("textures/items"));

            generateCustomItemsJson(geyserPath);
            generateBedrockTextureJson(bedrockPackPath);

            RPGMod.LOGGER.info("Generated Geyser mappings and Bedrock pack stub.");
        } catch (IOException e) {
            RPGMod.LOGGER.error("Failed to generate Geyser mappings", e);
        }
    }

    private static void generateCustomItemsJson(Path geyserPath) throws IOException {
        JsonObject mapping = new JsonObject();

        for (WeaponDefinition def : WeaponLoader.getWeapons()) {
            JsonObject itemInfo = new JsonObject();
            itemInfo.addProperty("icon", def.id());
            itemInfo.addProperty("name", def.id()); // Could use a display name if added to definition

            mapping.add("rpg:" + def.id(), itemInfo);
        }

        try (FileWriter writer = new FileWriter(geyserPath.resolve("custom_items.json").toFile())) {
            writer.write(mapping.toString());
        }
    }

    private static void generateBedrockTextureJson(Path bedrockPackPath) throws IOException {
        JsonObject textureData = new JsonObject();
        JsonObject textureList = new JsonObject();

        for (WeaponDefinition def : WeaponLoader.getWeapons()) {
            JsonObject textureEntry = new JsonObject();
            textureEntry.addProperty("textures", "textures/items/" + def.id());
            textureList.add(def.id(), textureEntry);
        }

        textureData.add("texture_data", textureList);

        try (FileWriter writer = new FileWriter(bedrockPackPath.resolve("textures/item_texture.json").toFile())) {
            writer.write(textureData.toString());
        }
    }
}
