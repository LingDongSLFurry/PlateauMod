package com.SRilodonSaur.Plateau.item;

import com.SRilodonSaur.Plateau.PlateauMod;
import com.SRilodonSaur.Plateau.entity.SnowLeopard;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class SpawnEntityTool extends Item {
    public SpawnEntityTool(Properties properties){
        super(properties);

    }


    @Override
    public InteractionResult useOn (UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        SnowLeopard snowLeopard = PlateauMod.SNOWLEOPARD.get().create(level);
        if (!level.isClientSide) {
            if (snowLeopard != null) {
                snowLeopard.setPos(blockPos.getCenter().x, blockPos.getCenter().y+0.5, blockPos.getCenter().z);
                level.addFreshEntity(snowLeopard);

            }

        }
        return InteractionResult.SUCCESS;

    }
}
