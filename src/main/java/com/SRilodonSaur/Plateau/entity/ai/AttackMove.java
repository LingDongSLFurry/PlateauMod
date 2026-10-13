package com.SRilodonSaur.Plateau.entity.ai;

import com.SRilodonSaur.Plateau.entity.SnowLeopard;
import com.SRilodonSaur.Plateau.entity.function.SnowLeopardSensations;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class AttackMove extends Goal {
    private final SnowLeopard snowLeopard;
    int cooltime = 0;
    private List<Animal> elist;
    Double mx;
    Double my;
    Double mz;
    boolean isMoveSuccess = true;
    public  AttackMove(SnowLeopard snowLeopard){
        this.snowLeopard = snowLeopard;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }
    @Override
    public boolean canUse() {
        if (cooltime <= 0){
            int x = snowLeopard.getBlockX();
             int y = snowLeopard.getBlockY();
                int z = snowLeopard.getBlockZ();
        Level level = snowLeopard.level();
        AABB aabb = snowLeopard.getBoundingBox().inflate(10f);
        elist = SnowLeopardSensations.getView(snowLeopard);
            Iterator<Animal> iterator = elist.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next().getName());


            }





        //level.getEntitiesOfClass(Mob.class, aabb, mob -> {
            //if (!(mob instanceof Plateau)) {
                //Float distance = snowLeopard.distanceTo(mob);
                //if (mob instanceof Monster && distance <= 10) {
                  //  return true;
                //}
            //}
            //return false;
        //});
        if (elist.isEmpty()) {
            return false;
        }
        return true;
        }else {
            cooltime--;
        }
        return false;
    }
    @Override
    public void start() {
        double min = Double.MAX_VALUE;
        Animal nearanimal = null;
        for (int i = 0; i <= elist.size()-1; i++) {
            Animal mob = elist.get(i);
            Float distance = snowLeopard.distanceTo(mob);
            if (distance <= min) {
                min = distance;
                nearanimal = mob;
            }
        }
        if (nearanimal != null) {
            snowLeopard.setPrey(nearanimal);
            Level level = snowLeopard.level();

            isMoveSuccess = snowLeopard.getNavigation().moveTo(nearanimal, 1.2);

        }
        cooltime = 10;
    }
    @Override
    public void tick() {
        if (snowLeopard.itsPrey() != null){
            Float distance = snowLeopard.distanceTo(snowLeopard.itsPrey());
        Level level = snowLeopard.level();

        if (distance <= 2.5) {
            //Level level = snowLeopard.level();
            //Player player = level.getNearestPlayer(snowLeopard, 10000);
            //HitResult seen = snowLeopard.pick(1.5f,1.0f,false);



            // player.sendSystemMessage(Component.literal(e.toString()));
            // if (e.equals(snowLeopard.itsPrey())){
            //Level level = snowLeopard.level();
            //Player player = level.getNearestPlayer(snowLeopard, 100);

            DamageSource damageSource = level.damageSources().mobAttack(snowLeopard);
            snowLeopard.itsPrey().hurt(damageSource, 500.0f);
            // }

                 }
          }
    }
    @Override
    public void stop(){
        if (snowLeopard.itsPrey() != null && snowLeopard.itsPrey().isDeadOrDying()){
            snowLeopard.setPrey(null);
            isMoveSuccess = true;
            cooltime = 0;
        }
    }
}