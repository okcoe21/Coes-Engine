package me.okcoe.rpg.data;

import java.util.List;
import java.util.Map;

public record WeaponDefinition(
        String id,
        String type,
        String texture,
        Stats stats,
        Abilities abilities) {
    public record Stats(
            double damage,
            double speed,
            int durability) {
    }

    public record Abilities(
            List<String> onHit,
            List<String> rightClick) {
    }
}
