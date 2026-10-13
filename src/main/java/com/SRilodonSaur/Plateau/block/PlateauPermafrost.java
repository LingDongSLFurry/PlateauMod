package com.SRilodonSaur.Plateau.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class PlateauPermafrost extends Block {
    public static final BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .strength(1.5F,3.0F)
            .sound(SoundType.STONE);
    public PlateauPermafrost() {
        super(properties);
    }
}
