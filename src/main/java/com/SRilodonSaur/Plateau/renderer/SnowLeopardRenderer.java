package com.SRilodonSaur.Plateau.renderer;

import com.SRilodonSaur.Plateau.entity.SnowLeopard;
import com.SRilodonSaur.Plateau.model.SnowLeopardModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SnowLeopardRenderer extends GeoEntityRenderer<SnowLeopard> {
    public SnowLeopardRenderer(EntityRendererProvider.Context context) {
        super(context,new SnowLeopardModel());
    }

    @Override
    public void scaleModelForRender(float width, float height, PoseStack poseStack, SnowLeopard snowLeopard, BakedGeoModel model, boolean a, float b, int c, int d) {
        float scale = 1.74f;
        poseStack.scale(scale, scale, scale);
        super.scaleModelForRender(width, height, poseStack, snowLeopard, model, a, b, c, d);
    }
}
