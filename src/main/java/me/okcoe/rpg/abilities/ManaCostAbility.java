package me.okcoe.rpg.abilities;

import com.google.gson.JsonObject;
import me.okcoe.rpg.api.Ability;
import me.okcoe.rpg.api.AbilityContext;
import me.okcoe.rpg.component.PlayerStats;
import me.okcoe.rpg.component.RPGComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class ManaCostAbility implements Ability {
    @Override
    public boolean execute(AbilityContext context, JsonObject config) {
        int cost = 10;
        if (config.has("value")) {
            try {
                cost = Integer.parseInt(config.get("value").getAsString());
            } catch (NumberFormatException ignored) {}
        }

        PlayerEntity player = context.player();
        PlayerStats stats = RPGComponents.PLAYER_STATS.get(player);

        if (stats.getMana() >= cost) {
            stats.addMana(-cost);
            return true;
        } else {
             player.sendMessage(Text.literal("Not enough mana!"), true);
             return false;
        }
    }

    @Override
    public String getId() {
        return "mana_cost";
    }
}
