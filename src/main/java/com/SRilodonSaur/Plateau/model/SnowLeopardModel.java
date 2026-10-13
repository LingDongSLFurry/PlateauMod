package com.SRilodonSaur.Plateau.model;

import com.SRilodonSaur.Plateau.entity.SnowLeopard;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SnowLeopardModel extends GeoModel<SnowLeopard> {


    @Override
    public ResourceLocation getModelResource(SnowLeopard snowLeopard) {
        return ResourceLocation.fromNamespaceAndPath("plateau", "geo/snowleopard.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SnowLeopard snowLeopard) {
        return ResourceLocation.fromNamespaceAndPath("plateau", "textures/entity/snowleopard.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SnowLeopard snowLeopard) {
        return ResourceLocation.fromNamespaceAndPath("plateau", "animations/entity/snowleopard.animation.json");
    }
}
