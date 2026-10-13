package com.SRilodonSaur.Plateau.mixin;

import com.SRilodonSaur.Plateau.tools.ClimateCache;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.RandomState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RandomState.class)
public class RandomStateMixin {
    @Inject(
            method = "<init>(Lnet/minecraft/world/level/levelgen/NoiseGeneratorSettings;Lnet/minecraft/core/HolderGetter;J)V",
            at = @At("TAIL")
    )
    private void onInit(NoiseGeneratorSettings settings, HolderGetter<?> noises, long seed, CallbackInfo ci) {
        ClimateCache.setSampler(((RandomState)(Object)this).sampler());
    }
}
