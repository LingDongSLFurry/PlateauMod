package com.SRilodonSaur.Plateau.tools;

import net.minecraft.world.level.biome.Climate;

public class ClimateCache {
    private static Climate.Sampler sampler;

    public static void setSampler(Climate.Sampler s) {
        sampler = s;
    }

    public static Climate.Sampler getSampler() {
        return sampler;
    }
}
