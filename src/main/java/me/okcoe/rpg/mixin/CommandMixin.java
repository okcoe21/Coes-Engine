package me.okcoe.rpg.mixin;

import com.mojang.brigadier.CommandDispatcher;
import me.okcoe.rpg.command.RPGCommands;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public abstract class CommandMixin {
    @Shadow
    private CommandDispatcher<net.minecraft.server.command.ServerCommandSource> dispatcher;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CommandManager.RegistrationEnvironment environment, CommandRegistryAccess registryAccess, CallbackInfo ci) {
        RPGCommands.register(this.dispatcher, registryAccess, environment);
    }
}
