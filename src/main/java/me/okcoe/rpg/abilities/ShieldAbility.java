package me.okcoe.rpg.abilities;

import com.google.gson.JsonObject;
import me.okcoe.rpg.api.Ability;
import me.okcoe.rpg.api.AbilityContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class ShieldAbility implements Ability {
    @Override
    public boolean execute(AbilityContext context, JsonObject config) {
        int duration = 100; // 5 seconds
        int amplifier = 0;

        if (config.has("value")) {
            // "amplifier:duration" or just amplifier
            String val = config.get("value").getAsString();
            if (val.contains(":")) {
                String[] parts = val.split(":");
                try {
                    amplifier = Integer.parseInt(parts[0]); // Keep 0-indexed internally? Or 1-indexed for user? Let's say user input 1 = level 1 = amplifier 0
                    if (amplifier > 0) amplifier--; // Adjust to 0-indexed if user thinks 1-based
                    duration = Integer.parseInt(parts[1]) * 20; // Seconds to ticks
                } catch (NumberFormatException ignored) {}
            } else {
                 try {
                    amplifier = Integer.parseInt(val);
                    if (amplifier > 0) amplifier--;
                } catch (NumberFormatException ignored) {}
            }
        }

        PlayerEntity player = context.player();
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, duration, amplifier));
        return true;
    }

    @Override
    public String getId() {
        return "shield";
    }
}
