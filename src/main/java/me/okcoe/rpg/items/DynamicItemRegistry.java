package me.okcoe.rpg.items;

import me.okcoe.rpg.RPGMod;
import me.okcoe.rpg.data.WeaponDefinition;
import me.okcoe.rpg.data.WeaponLoader;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class DynamicItemRegistry {
        public static void registerAll() {
                WeaponLoader.load();
                for (WeaponDefinition def : WeaponLoader.getWeapons()) {
                        registerWeapon(def);
                }
        }

        private static void registerWeapon(WeaponDefinition def) {
                Identifier id = Identifier.of(RPGMod.MOD_ID, def.id());
                RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id); // RegistryKey is mandatory in 1.21.2+

                // Standard IDs for attack damage and speed in 1.21.11
                Identifier damageId = Identifier.ofVanilla("base_attack_damage");
                Identifier speedId = Identifier.ofVanilla("base_attack_speed");

                // ToolMaterial is a record in 1.21.11 - we use IRON as a base
                // ToolMaterial is a record in 1.21.11 - we use IRON as a base
                // ToolMaterial material = ToolMaterials.IRON; // Unused

                // In 1.21.2+, some attributes were renamed or moved to RegistryEntry
                AttributeModifiersComponent modifiers = AttributeModifiersComponent.builder()
                                .add(EntityAttributes.ATTACK_DAMAGE,
                                                new EntityAttributeModifier(damageId, (double) def.stats().damage(),
                                                                EntityAttributeModifier.Operation.ADD_VALUE),
                                                AttributeModifierSlot.MAINHAND)
                                .add(EntityAttributes.ATTACK_SPEED,
                                                new EntityAttributeModifier(speedId, (double) def.stats().speed(),
                                                                EntityAttributeModifier.Operation.ADD_VALUE),
                                                AttributeModifierSlot.MAINHAND)
                                .build();

                // In 1.21.2+, some builds use id() instead of registryKey() or set it
                // implicitly via register()
                Item.Settings settings = new Item.Settings()
                                .registryKey(key)
                                .maxCount(1)
                                .maxDamage((int) def.stats().durability())
                                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, modifiers);

                ConfigurableWeaponItem item = new ConfigurableWeaponItem(def, settings);
                Registry.register(Registries.ITEM, key, item);
                RPGMod.LOGGER.info("Registered item: {}", id);
        }
}
