package me.okcoe.rpg.component;

import me.okcoe.rpg.RPGMod;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;

public class RPGComponents implements EntityComponentInitializer {
    public static final ComponentKey<PlayerStats> PLAYER_STATS = ComponentRegistry
            .getOrCreate(Identifier.of(RPGMod.MOD_ID, "player_stats"), PlayerStats.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        // Correctly handle respawn copying for CCA v6 with 6 parameters
        registry.registerForPlayers(PLAYER_STATS, BasePlayerStats::new,
                (from, to, lookup, lossless, keepInventory, sameCharacter) -> {
                    to.setMana(from.getMana());
                    to.setStamina(from.getStamina());
                });
    }
}
