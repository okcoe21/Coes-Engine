package me.okcoe.rpg.api;

import com.google.gson.JsonObject;

public interface Ability {
    /**
     * Executes the ability logic.
     * 
     * @param context The execution context (player, stack, hand).
     * @param config  Optional configuration parameters for this ability instance.
     * @return true if execution was successful and should continue, false otherwise.
     */
    boolean execute(AbilityContext context, JsonObject config);

    /**
     * Returns the unique identifier for this ability.
     */
    String getId();
}
