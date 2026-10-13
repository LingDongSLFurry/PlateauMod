package com.SRilodonSaur.Plateau.entity.ai;

import com.SRilodonSaur.Plateau.entity.SnowLeopard;
import net.minecraft.world.entity.animal.Animal;

public class PredationNavigator {
    private final SnowLeopard snowLeopard;
    private final Animal prey;
    public PredationNavigator(SnowLeopard snowLeopard){
        this.snowLeopard = snowLeopard;
        this.prey = snowLeopard.itsPrey();
    }





}
