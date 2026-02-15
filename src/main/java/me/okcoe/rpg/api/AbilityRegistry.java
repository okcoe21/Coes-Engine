package me.okcoe.rpg.api;

import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;

public class AbilityRegistry {
    private static final Map<String, Ability> ABILITIES = new HashMap<>();

    public static void register(Ability ability) {
        ABILITIES.put(ability.getId(), ability);
    }

    public static Ability get(String id) {
        return ABILITIES.get(id);
    }

    /**
     * Parses an ability string like "ignite:5" or a simple "dash"
     * and returns the ability and its configuration.
     * @return true if successful, false otherwise.
     */
    public static boolean executeFromString(String abilityStr, AbilityContext context) {
        String[] parts = abilityStr.split(":");
        String id = parts[0];
        Ability ability = get(id);

        if (ability != null) {
            JsonObject config = new JsonObject();
            if (parts.length > 1) {
                config.addProperty("value", parts[1]);
            }
            return ability.execute(context, config);
        }
        return true; // Unknown ability is ignored, continue chain
    }
}
