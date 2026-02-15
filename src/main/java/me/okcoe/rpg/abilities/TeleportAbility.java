package me.okcoe.rpg.abilities;

import com.google.gson.JsonObject;
import me.okcoe.rpg.api.Ability;
import me.okcoe.rpg.api.AbilityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.Set;

public class TeleportAbility implements Ability {
    @Override
    public boolean execute(AbilityContext context, JsonObject config) {
        double distance = 8.0;
        if (config.has("value")) {
            try {
                distance = Double.parseDouble(config.get("value").getAsString());
            } catch (NumberFormatException ignored) {}
        }

        PlayerEntity player = context.player();
        World world = player.getEntityWorld();
        Vec3d start = player.getCameraPosVec(1.0F);
        Vec3d end = start.add(player.getRotationVector().multiply(distance));

        HitResult result = world.raycast(new RaycastContext(
                start, end,
                RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE,
                player
        ));

        if (result.getType() != HitResult.Type.MISS) {
            end = result.getPos();
        }

        // MC 1.21+ teleport API: teleport(x, y, z, keepLoaded)
        player.teleport(end.x, end.y, end.z, true);
        return true;
    }

    @Override
    public String getId() {
        return "teleport";
    }
}
