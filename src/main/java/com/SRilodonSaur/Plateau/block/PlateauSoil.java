package com.SRilodonSaur.Plateau.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class PlateauSoil extends Block {
    public static final BlockBehaviour.Properties properties = BlockBehaviour.Properties.of()
            .mapColor(MapColor.DIRT)
            .strength(0.5F,0.5F)
            .sound(SoundType.ROOTED_DIRT);
    public PlateauSoil() {
        super(properties);
    }
}
