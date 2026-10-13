package com.SRilodonSaur.Plateau.tools;

import com.SRilodonSaur.Plateau.entity.SnowLeopard;
import net.minecraft.world.level.biome.Climate;

public class NoiseValue {

    public static float getTemperature(SnowLeopard snowLeopard){
        Climate.Sampler sampler = ClimateCache.getSampler();
        Climate.TargetPoint point = sampler.sample(snowLeopard.getBlockX(),snowLeopard.getBlockY(),snowLeopard.getBlockZ());

        return Climate.unquantizeCoord(point.temperature());
    }
    public static float getHumidity(SnowLeopard snowLeopard){
        Climate.Sampler sampler = ClimateCache.getSampler();
        Climate.TargetPoint point = sampler.sample(snowLeopard.getBlockX(),snowLeopard.getBlockY(),snowLeopard.getBlockZ());

        return Climate.unquantizeCoord(point.humidity());
    }
    public static float getContinentalness(SnowLeopard snowLeopard){
        Climate.Sampler sampler = ClimateCache.getSampler();
        Climate.TargetPoint point = sampler.sample(snowLeopard.getBlockX(),snowLeopard.getBlockY(),snowLeopard.getBlockZ());

        return Climate.unquantizeCoord(point.continentalness());
    }
    public static float getErosion(SnowLeopard snowLeopard){
        Climate.Sampler sampler = ClimateCache.getSampler();
        Climate.TargetPoint point = sampler.sample(snowLeopard.getBlockX(),snowLeopard.getBlockY(),snowLeopard.getBlockZ());

        return Climate.unquantizeCoord(point.erosion());
    }
    public static float getDepth(SnowLeopard snowLeopard){
        Climate.Sampler sampler = ClimateCache.getSampler();
        Climate.TargetPoint point = sampler.sample(snowLeopard.getBlockX(),snowLeopard.getBlockY(),snowLeopard.getBlockZ());

        return Climate.unquantizeCoord(point.depth());
    }
    public static float getWeirdness(SnowLeopard snowLeopard){
        Climate.Sampler sampler = ClimateCache.getSampler();
        Climate.TargetPoint point = sampler.sample(snowLeopard.getBlockX(),snowLeopard.getBlockY(),snowLeopard.getBlockZ());

        return Climate.unquantizeCoord(point.weirdness());
    }
}
