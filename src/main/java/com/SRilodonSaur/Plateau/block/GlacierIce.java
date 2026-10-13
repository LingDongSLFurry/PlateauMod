package com.SRilodonSaur.Plateau.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class GlacierIce extends Block {
    public static final BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
            .mapColor(MapColor.ICE)
            .strength(10.0F,20.0F)
            .sound(SoundType.GLASS)
            .requiresCorrectToolForDrops()
            .friction(0.999F);
    public GlacierIce() {
        super(properties);
    }
}
