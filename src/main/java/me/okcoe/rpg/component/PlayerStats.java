package me.okcoe.rpg.component;

import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

public interface PlayerStats extends Component, AutoSyncedComponent {
    int getMana();

    void setMana(int mana);

    int getMaxMana();

    int getStamina();

    void setStamina(int stamina);

    int getMaxStamina();

    default void addMana(int amount) {
        setMana(Math.min(getMaxMana(), Math.max(0, getMana() + amount)));
    }

    default void addStamina(int amount) {
        setStamina(Math.min(getMaxStamina(), Math.max(0, getStamina() + amount)));
    }
}
