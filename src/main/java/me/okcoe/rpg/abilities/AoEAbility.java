package me.okcoe.rpg.abilities;

import com.google.gson.JsonObject;
import me.okcoe.rpg.api.Ability;
import me.okcoe.rpg.api.AbilityContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class AoEAbility implements Ability {
    @Override
    public boolean execute(AbilityContext context, JsonObject config) {
        double radius = 5.0;
        float damage = 5.0f;

        if (config.has("value")) {
           // Parse "radius:damage" or just radius
           String val = config.get("value").getAsString();
           if (val.contains(":")) {
               String[] parts = val.split(":");
               try {
                   radius = Double.parseDouble(parts[0]);
                   damage = Float.parseFloat(parts[1]);
               } catch (NumberFormatException ignored) {}
           } else {
               try {
                   radius = Double.parseDouble(val);
               } catch (NumberFormatException ignored) {}
           }
        }

        PlayerEntity player = context.player();
        World world = player.getEntityWorld();
        Box box = player.getBoundingBox().expand(radius);

        List<Entity> entities = world.getOtherEntities(player, box, EntityPredicates.VALID_LIVING_ENTITY);
        for (Entity entity : entities) {
            if (entity instanceof LivingEntity living && entity.distanceTo(player) <= radius) {
                // MC 1.21+ requires ServerWorld as first parameter
                if (world instanceof ServerWorld serverWorld) {
                    living.damage(serverWorld, player.getDamageSources().playerAttack(player), damage);
                }
            }
        }
        return true;
    }

    @Override
    public String getId() {
        return "aoe";
    }
}
