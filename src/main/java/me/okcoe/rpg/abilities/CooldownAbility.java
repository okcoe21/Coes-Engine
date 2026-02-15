package me.okcoe.rpg.abilities;

import com.google.gson.JsonObject;
import me.okcoe.rpg.api.Ability;
import me.okcoe.rpg.api.AbilityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class CooldownAbility implements Ability {
    @Override
    public boolean execute(AbilityContext context, JsonObject config) {
        double seconds = 1.0;
        if (config.has("value")) {
            try {
                seconds = Double.parseDouble(config.get("value").getAsString());
            } catch (NumberFormatException ignored) {}
        }
        
        PlayerEntity player = context.player();
        // Item specific cooldown - MC 1.21+ uses ItemStack instead of Item
        if (!player.getItemCooldownManager().isCoolingDown(context.stack())) {
            player.getItemCooldownManager().set(context.stack(), (int) (seconds * 20));
            return true;
        }
        
        // If already on cooldown, maybe we should return false?
        // But the item use itself usually checks cooldown before use if it's vanilla.
        // However, this is an ability triggered by use.
        // If we want to ENFORCE cooldown, we set it.
        // If we want to CHECK it, we should return false if cooling down.
        // But standard Item.use() checks cooldowns if set.
        // This ability SETS the cooldown for NEXT time.
        // So it should always succeed and return true.
        
        return true;
    }

    @Override
    public String getId() {
        return "cooldown";
    }
}
