package com.SRilodonSaur.Plateau.entity.function;

import com.SRilodonSaur.Plateau.entity.SnowLeopard;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SnowLeopardSensations {

    public static float getTemperature(SnowLeopard snowLeopard){
        BlockPos blockPos = snowLeopard.getOnPos();
        Level level = snowLeopard.level();
        Biome biome = level.getBiome(blockPos).value();
        return biome.getBaseTemperature();
    }
    public static float getAltitude(SnowLeopard snowLeopard){
        return snowLeopard.getOnPos().getY();
    }
    public static int getLight(SnowLeopard snowLeopard){
        Level level = snowLeopard.level();
        return level.getLightEmission(snowLeopard.getOnPos());
    }
    public static List<Animal> getView(SnowLeopard snowLeopard){
        //锥形搜索+傻逼视觉遮挡
        AABB aabb = snowLeopard.getBoundingBox().inflate(20f);
        Vec3 seye = snowLeopard.getEyePosition();
        Level level = snowLeopard.level();
        List<Animal> elist = level.getEntitiesOfClass(Animal.class,aabb,animal -> {
            Float distance = snowLeopard.distanceTo(animal);
            Vec3 eye = snowLeopard.getViewVector(1.0f);
            if (distance<=50) {
                if (!(animal instanceof SnowLeopard)){
                Vec3 snowleopardPos = snowLeopard.getEyePosition();
                Vec3 animalPos = animal.position();
                Vec3 v = animalPos.subtract(snowleopardPos).normalize();
                double cos = eye.dot(v);
                if (cos <= 1 && cos >= 0.866) {//cos30 = 0.866
                    return true;
                    }
                 }
            }
            return false;
        });
        Iterator<Animal> iterator = elist.iterator();
        while (iterator.hasNext()){
            Animal animal = iterator.next();
            AABB bbox = animal.getBoundingBoxForCulling();
            List<Vec3> bcorner = new ArrayList<>(8);
            double[] xs = {bbox.minX, bbox.maxX};
            double[] ys = {bbox.minY, bbox.maxY};
            double[] zs = {bbox.minZ, bbox.maxZ};
            for (int i = 0;i < 2;i++) {
                for (int j = 0;j < 2;j++) {
                    for (int k = 0;k < 2;k++) {
                        bcorner.add(new Vec3(xs[i], ys[j], zs[k]));
                    }
                }
            }
            Vec3 avc = new Vec3(bbox.getCenter().x,bbox.getCenter().y,bbox.getCenter().z);
            bcorner.add(avc);
                Iterator<Vec3> abcorner = bcorner.iterator();
                while (abcorner.hasNext()){
                    Vec3 v = abcorner.next();
                    BlockHitResult entityHitResult = level.clip(new ClipContext(seye,v, ClipContext.Block.COLLIDER,ClipContext.Fluid.SOURCE_ONLY,snowLeopard));
                    if (entityHitResult.getType() != HitResult.Type.MISS){
                        abcorner.remove();
                    }
                }
                if (bcorner.size() <= 3){
                    Random random = new Random();
                    int a = random.nextInt(100);
                    if (a <= 70){
                        iterator.remove();
                    }
                }
        }
            return elist;
    }
}