package me.okcoe.rpg.items;

import me.okcoe.rpg.api.AbilityContext;
import me.okcoe.rpg.api.AbilityRegistry;
import me.okcoe.rpg.data.WeaponDefinition;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import net.minecraft.item.Item;

public class ConfigurableWeaponItem extends Item {
    private final WeaponDefinition definition;

    public ConfigurableWeaponItem(WeaponDefinition definition, Settings settings) {
        super(settings);
        this.definition = definition;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            AbilityContext context = new AbilityContext(user, user.getStackInHand(hand), hand);
            for (String abilityStr : definition.abilities().rightClick()) {
                if (!AbilityRegistry.executeFromString(abilityStr, context)) {
                    break;
                }
            }
        }
        return super.use(world, user, hand);
    }

    // @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player && !attacker.getEntityWorld().isClient()) {
            AbilityContext context = new AbilityContext(player, stack, Hand.MAIN_HAND);
            for (String abilityStr : definition.abilities().onHit()) {
                if (!AbilityRegistry.executeFromString(abilityStr, context)) {
                    break;
                }
            }
        }
    }

    public WeaponDefinition getDefinition() {
        return definition;
    }
}
