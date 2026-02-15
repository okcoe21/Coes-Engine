package me.okcoe.rpg.component;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.ComponentKey;

public class BasePlayerStats implements PlayerStats {
    private int mana = 100;
    private int maxMana = 100;
    private int stamina = 100;
    private int maxStamina = 100;
    private final PlayerEntity provider;

    public BasePlayerStats(PlayerEntity provider) {
        this.provider = provider;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setMana(int mana) {
        this.mana = mana;
        RPGComponents.PLAYER_STATS.sync(provider);
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public int getStamina() {
        return stamina;
    }

    @Override
    public void setStamina(int stamina) {
        this.stamina = stamina;
        RPGComponents.PLAYER_STATS.sync(provider);
    }

    @Override
    public int getMaxStamina() {
        return maxStamina;
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        // In some 1.21.11 environments, tag.getInt() returns Optional<Integer>
        Object manaObj = tag.getInt("mana");
        this.mana = manaObj instanceof java.util.Optional ? ((java.util.Optional<Integer>) manaObj).orElse(100)
                : (int) manaObj;

        Object maxManaObj = tag.getInt("maxMana");
        this.maxMana = maxManaObj instanceof java.util.Optional ? ((java.util.Optional<Integer>) maxManaObj).orElse(100)
                : (int) maxManaObj;

        Object staminaObj = tag.getInt("stamina");
        this.stamina = staminaObj instanceof java.util.Optional ? ((java.util.Optional<Integer>) staminaObj).orElse(100)
                : (int) staminaObj;

        Object maxStaminaObj = tag.getInt("maxStamina");
        this.maxStamina = maxStaminaObj instanceof java.util.Optional
                ? ((java.util.Optional<Integer>) maxStaminaObj).orElse(100)
                : (int) maxStaminaObj;
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("mana", mana);
        tag.putInt("maxMana", maxMana);
        tag.putInt("stamina", stamina);
        tag.putInt("maxStamina", maxStamina);
    }
}
