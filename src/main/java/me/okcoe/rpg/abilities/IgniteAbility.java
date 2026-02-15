package me.okcoe.rpg.abilities;

import com.google.gson.JsonObject;
import me.okcoe.rpg.api.Ability;
import me.okcoe.rpg.api.AbilityContext;

public class IgniteAbility implements Ability {
    @Override
    public boolean execute(AbilityContext context, JsonObject config) {
        int duration = 5;
        if (config.has("value")) {
            try {
                duration = Integer.parseInt(config.get("value").getAsString());
            } catch (NumberFormatException ignored) {
            }
        }

        // This is a placeholder for on-hit logic.
        // For right-click, we might ignite nearby entities.
        context.player().setOnFireFor(duration);
        return true;
    }

    @Override
    public String getId() {
        return "ignite";
    }
}
