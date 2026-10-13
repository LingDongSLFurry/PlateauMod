package com.SRilodonSaur.Plateau.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class Peat extends Block {
    public static final BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN)
            .strength(1.5F,4.0F)
            .sound(SoundType.STONE);
    public Peat() {
        super(properties);
    }
}
