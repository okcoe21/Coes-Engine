package me.okcoe.rpg.api;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public record AbilityContext(PlayerEntity player, ItemStack stack, Hand hand) {
}
