package me.okcoe.rpg.abilities;

import com.google.gson.JsonObject;
import me.okcoe.rpg.api.Ability;
import me.okcoe.rpg.api.AbilityContext;
import net.minecraft.entity.player.PlayerEntity;

public class HealAbility implements Ability {
    @Override
    public boolean execute(AbilityContext context, JsonObject config) {
        float amount = 4.0f; // 2 hearts default
        if (config.has("value")) {
            try {
                amount = Float.parseFloat(config.get("value").getAsString());
            } catch (NumberFormatException ignored) {
            }
        }

        PlayerEntity player = context.player();
        player.heal(amount);
        return true;
    }

    @Override
    public String getId() {
        return "heal";
    }
}
