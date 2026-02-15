package me.okcoe.rpg.abilities;

import com.google.gson.JsonObject;
import me.okcoe.rpg.api.Ability;
import me.okcoe.rpg.api.AbilityContext;
import net.minecraft.util.math.Vec3d;

public class DashAbility implements Ability {
    @Override
    public boolean execute(AbilityContext context, JsonObject config) {
        double multiplier = 2.0;
        if (config.has("multiplier")) {
            multiplier = config.get("multiplier").getAsDouble();
        } else if (config.has("value")) {
            try {
                multiplier = Double.parseDouble(config.get("value").getAsString());
            } catch (NumberFormatException ignored) {
            }
        }

        Vec3d velocity = context.player().getRotationVector().multiply(multiplier);
        context.player().addVelocity(velocity.x, velocity.y, velocity.z);
        // Signaling client update
        context.player().velocityDirty = true;
        return true;
    }

    @Override
    public String getId() {
        return "dash";
    }
}
