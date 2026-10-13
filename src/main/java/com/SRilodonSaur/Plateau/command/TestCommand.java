package com.SRilodonSaur.Plateau.command;

import com.SRilodonSaur.Plateau.tools.ClimateCache;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.biome.Climate;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class TestCommand {
    @SubscribeEvent
    public void registerCommand(RegisterCommandsEvent event){
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(Commands.literal("plateau").then(Commands.literal("climate").executes(
                context -> {
                    Climate.Sampler sampler = ClimateCache.getSampler();
                    if (ClimateCache.getSampler() != null) {
                        context.getSource().sendSystemMessage(Component.literal(""));
                    }else {
                        context.getSource().sendSystemMessage(Component.literal("h"));

                    }
                    return 1;
                })));

    }

}
