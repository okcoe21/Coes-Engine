package me.okcoe.rpg;

import me.okcoe.rpg.abilities.*;
import me.okcoe.rpg.api.AbilityRegistry;
import me.okcoe.rpg.command.RPGCommands;
import me.okcoe.rpg.geyser.GeyserMappingGenerator;
import me.okcoe.rpg.items.DynamicItemRegistry;
import me.okcoe.rpg.resourcepack.ResourcePackGenerator;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RPGMod implements ModInitializer {
    public static final String MOD_ID = "coes-engine";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Coes Engine RPG Framework...");

        try {
            LOGGER.info("Attempting to load classes via reflection...");
            String[] classesToTest = {
                    "net.minecraft.item.ToolMaterial",
                    "net.minecraft.world.item.Tier",
                    "net.minecraft.world.item.ToolMaterial",
                    "net.minecraft.item.SwordItem",
                    "net.minecraft.world.item.SwordItem"
            };
            for (String className : classesToTest) {
                try {
                    Class<?> clazz = Class.forName(className);
                    LOGGER.info("Successfully loaded {}: {}", className, clazz.getName());
                } catch (ClassNotFoundException e) {
                    LOGGER.warn("Class not found: {}", className);
                }
            }
        } catch (Exception e) {
            LOGGER.warn("Reflection check failed: {}", e.getMessage());
        }

        try {
            // Register abilities
            AbilityRegistry.register(new IgniteAbility());
            AbilityRegistry.register(new DashAbility());
            AbilityRegistry.register(new HealAbility());
            AbilityRegistry.register(new ProjectileAbility());
            AbilityRegistry.register(new AoEAbility());
            AbilityRegistry.register(new TeleportAbility());
            AbilityRegistry.register(new ShieldAbility());
            AbilityRegistry.register(new ManaCostAbility());
            AbilityRegistry.register(new CooldownAbility());

            // Register dynamic items
            LOGGER.info("Calling DynamicItemRegistry.registerAll()...");
            DynamicItemRegistry.registerAll();

            // Generate packs
            LOGGER.info("Generating packs...");
            ResourcePackGenerator.generate();
            GeyserMappingGenerator.generate();

            LOGGER.info("Initialization complete.");
        } catch (Throwable t) {
            LOGGER.error("CRITICAL ERROR DURING INITIALIZATION", t);
            throw t;
        }
    }
}
