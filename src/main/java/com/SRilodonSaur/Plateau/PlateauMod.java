package com.SRilodonSaur.Plateau;


import com.SRilodonSaur.Plateau.block.*;
import com.SRilodonSaur.Plateau.command.TestCommand;
import com.SRilodonSaur.Plateau.entity.SnowLeopard;
import com.SRilodonSaur.Plateau.item.SpawnEntityTool;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(com.SRilodonSaur.Plateau.PlateauMod.MODID)
public class PlateauMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "plateau";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,MODID);
    public static final RegistryObject<Block> WEATHERED_ROCK =BLOCKS.register("weathered_rock", WeatheredRock::new);
    public static final RegistryObject<Block> FROST_WEATHERED_ROCK =BLOCKS.register("frost_weathered_rock", FrostWeatheredRock::new);
    public static final RegistryObject<Block> GLACIER_ICE =BLOCKS.register("glacier_ice", GlacierIce::new);
    public static final RegistryObject<Block> ICE_GRAVEL =BLOCKS.register("ice_gravel", IceGravel::new);
    public static final RegistryObject<Block> PEAT =BLOCKS.register("peat", Peat::new);
    public static final RegistryObject<Block> PLATEAU_PERMAFROST =BLOCKS.register("plateau_permafrost", PlateauPermafrost::new);
    public static final RegistryObject<Block> PLATEAU_SOIL =BLOCKS.register("plateau_soil", PlateauSoil::new);
    public static final RegistryObject<Block> ROUGH_GRAVEL =BLOCKS.register("rough_gravel", RoughGravel::new);
    public static final RegistryObject<Block> SALT_CRUST =BLOCKS.register("salt_crust", SaltCrust::new);
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "plateau");
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<Item> SPAWN_ENTITY_TOOL = ITEMS.register("spawn_entity_tool", () -> new SpawnEntityTool(new Item.Properties()));

    //blocks
    public static final RegistryObject<Item> WEATHERED_ROCK_ITEM = ITEMS.register("weathered_rock", () -> new BlockItem(WEATHERED_ROCK.get(),new Item.Properties()));
    public static final RegistryObject<Item> FROST_WEATHERED_ROCK_ITEM = ITEMS.register("frost_weathered_rock", () -> new BlockItem(FROST_WEATHERED_ROCK.get(),new Item.Properties()));
    public static final RegistryObject<Item> GLACIER_ICE_ITEM = ITEMS.register("glacier_ice", () -> new BlockItem(GLACIER_ICE.get(),new Item.Properties()));
    public static final RegistryObject<Item> ICE_GRAVEL_ITEM = ITEMS.register("ice_gravel", () -> new BlockItem(ICE_GRAVEL.get(),new Item.Properties()));
    public static final RegistryObject<Item> PEAT_ITEM = ITEMS.register("peat", () -> new BlockItem(PEAT.get(),new Item.Properties()));
    public static final RegistryObject<Item> PLATEAU_PERMAFROST_ITEM = ITEMS.register("plateau_permafrost", () -> new BlockItem(PLATEAU_PERMAFROST.get(),new Item.Properties()));
    public static final RegistryObject<Item> PLATEAU_SOIL_ITEM = ITEMS.register("plateau_soil", () -> new BlockItem(PLATEAU_SOIL.get(),new Item.Properties()));
    public static final RegistryObject<Item> ROUGH_GRAVEL_ITEM = ITEMS.register("rough_gravel", () -> new BlockItem(ROUGH_GRAVEL.get(),new Item.Properties()));
    public static final RegistryObject<Item> SALT_CRUST_ITEM = ITEMS.register("salt_crust", () -> new BlockItem(SALT_CRUST.get(),new Item.Properties()));

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("itemGroup.plateau.tab"))
            .icon(() -> GLACIER_ICE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(SPAWN_ENTITY_TOOL.get());
                output.accept(WEATHERED_ROCK_ITEM.get());
                output.accept(FROST_WEATHERED_ROCK_ITEM.get());
                output.accept(GLACIER_ICE_ITEM.get());
                output.accept(ICE_GRAVEL_ITEM.get());
                output.accept(PEAT_ITEM.get());
                output.accept(PLATEAU_PERMAFROST_ITEM.get());
                output.accept(PLATEAU_SOIL_ITEM.get());
                output.accept(ROUGH_GRAVEL_ITEM.get());
                output.accept(SALT_CRUST_ITEM.get());
                // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());
    public static final RegistryObject<EntityType<SnowLeopard>> SNOWLEOPARD =
            ENTITY.register("snowleopard", () ->
                    EntityType.Builder.<SnowLeopard>of(SnowLeopard::new, MobCategory.MISC)
                            .sized(1.0F, 1.0F)
                            .clientTrackingRange(8)
                            .updateInterval(1)
                            .build(ResourceLocation.fromNamespaceAndPath("plateau", "plateau").toString()));
    public PlateauMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.register(ITEMS);
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        ENTITY.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(new TestCommand());
        }
    }


