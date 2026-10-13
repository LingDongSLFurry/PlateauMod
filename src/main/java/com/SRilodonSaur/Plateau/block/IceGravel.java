package com.SRilodonSaur.Plateau.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class IceGravel extends Block {
    public static final BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .strength(0.5F,0.5F)
            .sound(SoundType.GRAVEL);
    public IceGravel() {
        super(properties);
    }
}
