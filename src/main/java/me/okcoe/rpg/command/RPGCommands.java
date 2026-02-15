package me.okcoe.rpg.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import me.okcoe.rpg.RPGMod;
import me.okcoe.rpg.data.WeaponDefinition;
import me.okcoe.rpg.data.WeaponLoader;
import me.okcoe.rpg.geyser.GeyserMappingGenerator;
import me.okcoe.rpg.resourcepack.ResourcePackGenerator;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.PlayerConfigEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RPGCommands {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("rpg")
                    .requires(source -> {
                        if (source.getEntity() == null) return true;
                        if (source.getEntity() instanceof ServerPlayerEntity player) {
                            return source.getServer().getPlayerManager().isOperator(new PlayerConfigEntry(player.getGameProfile()));
                        }
                        return false;
                    })
                    .then(CommandManager.literal("reload")
                            .executes(context -> {
                                WeaponLoader.load();
                                ResourcePackGenerator.generate();
                                GeyserMappingGenerator.generate();
                                context.getSource().sendFeedback(() -> Text.literal("RPG Framework reloaded!"), true);
                                return 1;
                            }))
                    .then(CommandManager.literal("give")
                            .then(CommandManager.argument("weapon", StringArgumentType.string())
                                    .suggests((context, builder) -> {
                                        for (WeaponDefinition def : WeaponLoader.getWeapons()) {
                                            builder.suggest(def.id());
                                        }
                                        return builder.buildFuture();
                                    })
                                    .executes(context -> {
                                        String weaponId = StringArgumentType.getString(context, "weapon");
                                        ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
                                        if (player != null) {
                                            Item item = Registries.ITEM.get(Identifier.of(RPGMod.MOD_ID, weaponId));
                                            if (item != null) {
                                                player.getInventory().insertStack(new ItemStack(item));
                                                context.getSource().sendFeedback(
                                                        () -> Text.literal("Gave " + weaponId + " to player"), true);
                                            } else {
                                                context.getSource()
                                                        .sendError(Text.literal("Weapon not found: " + weaponId));
                                            }
                                        }
                                        return 1;
                                    }))
                    .then(CommandManager.literal("mana")
                            .then(CommandManager.literal("set")
                                    .then(CommandManager.argument("target", net.minecraft.command.argument.EntityArgumentType.player())
                                            .then(CommandManager.argument("amount", com.mojang.brigadier.arguments.IntegerArgumentType.integer(0))
                                                    .executes(context -> {
                                                        ServerPlayerEntity player = net.minecraft.command.argument.EntityArgumentType.getPlayer(context, "target");
                                                        int amount = com.mojang.brigadier.arguments.IntegerArgumentType.getInteger(context, "amount");
                                                        me.okcoe.rpg.component.RPGComponents.PLAYER_STATS.get(player).setMana(amount);
                                                        context.getSource().sendFeedback(() -> Text.literal("Set mana for " + player.getName().getString() + " to " + amount), true);
                                                        return 1;
                                                    }))))
                            .then(CommandManager.literal("add")
                                    .then(CommandManager.argument("target", net.minecraft.command.argument.EntityArgumentType.player())
                                            .then(CommandManager.argument("amount", com.mojang.brigadier.arguments.IntegerArgumentType.integer())
                                                    .executes(context -> {
                                                        ServerPlayerEntity player = net.minecraft.command.argument.EntityArgumentType.getPlayer(context, "target");
                                                        int amount = com.mojang.brigadier.arguments.IntegerArgumentType.getInteger(context, "amount");
                                                        me.okcoe.rpg.component.RPGComponents.PLAYER_STATS.get(player).addMana(amount);
                                                        context.getSource().sendFeedback(() -> Text.literal("Added " + amount + " mana to " + player.getName().getString()), true);
                                                        return 1;
                                                    }))))
                            .then(CommandManager.literal("get")
                                    .then(CommandManager.argument("target", net.minecraft.command.argument.EntityArgumentType.player())
                                            .executes(context -> {
                                                ServerPlayerEntity player = net.minecraft.command.argument.EntityArgumentType.getPlayer(context, "target");
                                                int mana = me.okcoe.rpg.component.RPGComponents.PLAYER_STATS.get(player).getMana();
                                                context.getSource().sendFeedback(() -> Text.literal(player.getName().getString() + " has " + mana + " mana"), false);
                                                return 1;
                                            }))))));
        });
    }
}
