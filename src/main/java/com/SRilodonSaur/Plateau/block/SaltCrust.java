package com.SRilodonSaur.Plateau.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class SaltCrust extends Block {
    public static final BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
            .mapColor(MapColor.QUARTZ)
            .strength(2.0F,4.0F)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops();
    public SaltCrust() {
        super(properties);
    }
}
