package com.SRilodonSaur.Plateau.event;


import com.SRilodonSaur.Plateau.PlateauMod;
import com.SRilodonSaur.Plateau.entity.SnowLeopard;
import com.SRilodonSaur.Plateau.renderer.SnowLeopardRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PlateauMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegisterEvent {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(
                com.SRilodonSaur.Plateau.PlateauMod.SNOWLEOPARD.get(),
                SnowLeopardRenderer::new
        );
    }
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(com.SRilodonSaur.Plateau.PlateauMod.SNOWLEOPARD.get(),SnowLeopard.createAttributes().build());
    }
}
