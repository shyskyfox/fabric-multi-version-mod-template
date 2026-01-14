package com.multiversion.template;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.literal;

public class ExampleModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Registriert den Befehl beim Client Start (funktioniert auch im Singleplayer).
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            registerHelloCommand(dispatcher);
        });
    }

    private void registerHelloCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("hello")
            .executes(context -> {
                // Diese Logik wird ausgeführt, wenn jemand "/hello" eingibt.
                context.getSource().sendFeedback(() -> Text.literal("Hello, world!"), false);
                return 1; // 1 signalisiert eine erfolgreiche Ausführung
            }));
    }
}
