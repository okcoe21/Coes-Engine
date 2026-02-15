package me.okcoe.rpg.abilities;

import com.google.gson.JsonObject;
import me.okcoe.rpg.api.Ability;
import me.okcoe.rpg.api.AbilityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ProjectileAbility implements Ability {
    @Override
    public boolean execute(AbilityContext context, JsonObject config) {
        String type = "arrow";
        if (config.has("value")) {
            type = config.get("value").getAsString();
        }

        PlayerEntity player = context.player();
        World world = player.getEntityWorld();
        Vec3d look = player.getRotationVector();

        if (type.equals("fireball")) {
            SmallFireballEntity fireball = new SmallFireballEntity(world, player, look.normalize());
            fireball.setPosition(player.getX(), player.getEyeY(), player.getZ());
            world.spawnEntity(fireball);
        } else if (type.equals("large_fireball")) {
            FireballEntity fireball = new FireballEntity(world, player, look.normalize(), 1);
            fireball.setPosition(player.getX(), player.getEyeY(), player.getZ());
            world.spawnEntity(fireball);
        } else {
            // Default to arrow - MC 1.21+ requires weapon stack and projectile stack
            ArrowEntity arrow = new ArrowEntity(world, player, context.stack(), new ItemStack(Items.ARROW));
            arrow.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 3.0F, 1.0F);
            world.spawnEntity(arrow);
        }
        return true;
    }

    @Override
    public String getId() {
        return "projectile";
    }
}
