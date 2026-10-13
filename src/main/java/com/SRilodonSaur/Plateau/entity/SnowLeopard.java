package com.SRilodonSaur.Plateau.entity;

import com.SRilodonSaur.Plateau.entity.ai.AttackMove;
import com.SRilodonSaur.Plateau.tools.NoiseValue;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SnowLeopard extends Animal implements GeoEntity {
    //理想值


    Level level = this.level();
    Biome biome = level.getBiome(this.blockPosition()).value();
    //NoiseC

    private static Animal prey;
    private static int stamina = 100;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Integer> DATA = SynchedEntityData.defineId(SnowLeopard.class, EntityDataSerializers.INT);
    public SnowLeopard(EntityType<? extends Animal> type, Level level){
        super(type,level);
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller",0,state -> {
            if (state.isMoving()){
                Vec3 deltaMovement = this.getDeltaMovement();
                double speed = deltaMovement.length()*20;
                if (speed >= 2.5){
                    return state.setAndContinue(RawAnimation.begin().thenLoop("animation.plateau.run2"));
                }else {
                    return state.setAndContinue(RawAnimation.begin().thenLoop("animation.plateau.walk2"));
                }
            }
                return state.setAndContinue(RawAnimation.begin().thenLoop("animation.plateau.idle"));
            })
        );
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public SnowLeopard getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return null;
    }

    @Override
    protected void defineSynchedData(){
        super.defineSynchedData();
        this.entityData.define(DATA,0);
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        this.entityData.set(DATA, tag.getInt("Plateau"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {

        tag.putInt("Plateau", this.entityData.get(DATA));
    }
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(0, new AttackMove(this));
    }
    @Override
    public void tick(){
     super.tick();
     System.out.println("坐标:"+this.getBlockX()+","+this.getBlockY()+","+this.getBlockZ()+"适应值："+this.getAdaptationIndex());
     //this.setDeltaMovement(0.05,this.getDeltaMovement().y,0);

    }
    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 200.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D);
    }
    public void setPrey(Animal mob){
        prey = mob;
    }
    public Animal itsPrey(){
        return prey;
    }
      private static double gaussian(double value, double ideal, double width) {
        double diff = value - ideal;
        return Math.exp(-(diff * diff) / (2 * width * width));
    }
    public double getAdaptationIndex(){
       double IDEAL_TEMP = -0.5;
       double IDEAL_HUMID = -0.2;
       double IDEAL_CONT = 0.3;
       double IDEAL_EROSION = -0.5;
       double IDEAL_WEIRD = 0.0;
       double IDEAL_DEPTH = 0.0;
        //容忍度
        double WIDTH_TEMP = 0.5;
        double WIDTH_HUMID = 0.6;
        double WIDTH_CONT = 0.5;
        double WIDTH_EROSION = 0.5;
        double WIDTH_WEIRD = 0.8;
        double WIDTH_DEPTH = 0.4;
        //权重
        double W_TEMP = 3.0;
        double W_HUMID = 1.0;
        double W_CONT = 2.0;
        double W_EROSION = 2.5;
        double W_WEIRD = 0.5;
        double W_DEPTH = 2.0;
        double temp = NoiseValue.getTemperature(this);
        double humid = NoiseValue.getHumidity(this);
        double cont = NoiseValue.getContinentalness(this);
        double erosion = NoiseValue.getErosion(this);
        double weird = NoiseValue.getWeirdness(this);
        double depth = NoiseValue.getDepth(this);
        double sTemp = gaussian(temp, IDEAL_TEMP, WIDTH_TEMP);
        double sHumid = gaussian(humid, IDEAL_HUMID, WIDTH_HUMID);
        double sCont = gaussian(cont, IDEAL_CONT, WIDTH_CONT);
        double sErosion = gaussian(erosion, IDEAL_EROSION, WIDTH_EROSION);
        double sWeird = gaussian(weird, IDEAL_WEIRD, WIDTH_WEIRD);
        double sDepth = gaussian(depth, IDEAL_DEPTH, WIDTH_DEPTH);
        double weightedSum = sTemp * W_TEMP + sHumid * W_HUMID + sCont * W_CONT + sErosion * W_EROSION + sWeird * W_WEIRD + sDepth * W_DEPTH;
        double totalWeight = W_TEMP + W_HUMID + W_CONT + W_EROSION + W_WEIRD + W_DEPTH;
            return weightedSum / totalWeight;
    }
    public boolean changeStamina(int delt){
        if (stamina - delt >= 0 && stamina - delt <= 100) {
            stamina = stamina - delt;
            return true;
        }
        return false;
    }
}
