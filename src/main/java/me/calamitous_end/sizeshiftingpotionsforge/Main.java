package me.calamitous_end.sizeshiftingpotionsforge;

import me.calamitous_end.sizeshiftingpotionsforge.config.Common;
import me.calamitous_end.sizeshiftingpotionsforge.effects.DividingSizeStatusEffect;
import me.calamitous_end.sizeshiftingpotionsforge.effects.MultiplyingSizeStatusEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "sizeshiftingpotions";

    public static final DeferredRegister<MobEffect> POTION_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Main.MOD_ID);

    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, Main.MOD_ID);

    public static final RegistryObject<MobEffect> SHRINKING = POTION_EFFECTS.register("shrinking", () -> new DividingSizeStatusEffect(MobEffectCategory.NEUTRAL, 0xcca468, CustomScaleTypes.SIZE));
    public static RegistryObject<Potion> SHRINKING_POTION = POTIONS.register("shrinking", () -> new Potion(new MobEffectInstance(Main.SHRINKING.get(), 3600, 0)));
    public static RegistryObject<Potion> LONG_SHRINKING_POTION = POTIONS.register("shrinking_long", () -> new Potion(new MobEffectInstance(Main.SHRINKING.get(), 9600, 0)));
    public static RegistryObject<Potion> STRONG_SHRINKING_POTION = POTIONS.register("shrinking_strong", () -> new Potion(new MobEffectInstance(Main.SHRINKING.get(), 1800, 1)));

    public static final RegistryObject<MobEffect> GROWING = POTION_EFFECTS.register("growing", () -> new MultiplyingSizeStatusEffect(MobEffectCategory.NEUTRAL, 0xc90000, CustomScaleTypes.SIZE));
    public static RegistryObject<Potion> GROWING_POTION = POTIONS.register("growing", () -> new Potion(new MobEffectInstance(Main.GROWING.get(), 3600, 0)));
    public static RegistryObject<Potion> LONG_GROWING_POTION = POTIONS.register("growing_long", () -> new Potion(new MobEffectInstance(Main.GROWING.get(), 9600, 0)));
    public static RegistryObject<Potion> STRONG_GROWING_POTION = POTIONS.register("growing_strong", () -> new Potion(new MobEffectInstance(Main.GROWING.get(), 1800, 1)));

    public static final RegistryObject<MobEffect> WIDENING = POTION_EFFECTS.register("widening", () -> new MultiplyingSizeStatusEffect(MobEffectCategory.NEUTRAL, 0xb3ffc2, CustomScaleTypes.THICKNESS));
    public static RegistryObject<Potion> WIDENING_POTION = POTIONS.register("widening", () -> new Potion(new MobEffectInstance(Main.WIDENING.get(), 3600, 0)));
    public static RegistryObject<Potion> LONG_WIDENING_POTION = POTIONS.register("widening_long", () -> new Potion(new MobEffectInstance(Main.WIDENING.get(), 9600, 0)));
    public static RegistryObject<Potion> STRONG_WIDENING_POTION = POTIONS.register("widening_strong", () -> new Potion(new MobEffectInstance(Main.WIDENING.get(), 1800, 1)));

    public static RegistryObject<MobEffect> THINNING = POTION_EFFECTS.register("thinning", () -> new DividingSizeStatusEffect(MobEffectCategory.NEUTRAL, 0xe3b3ff, CustomScaleTypes.THICKNESS));

    public static RegistryObject<Potion> THINNING_POTION = POTIONS.register("thinning", () -> new Potion(new MobEffectInstance(Main.THINNING.get(), 3600, 0)));
    public static RegistryObject<Potion> LONG_THINNING_POTION = POTIONS.register("thinning_long", () -> new Potion(new MobEffectInstance(Main.THINNING.get(), 9600, 0)));
    public static RegistryObject<Potion> STRONG_THINNING_POTION = POTIONS.register("thinning_strong", () -> new Potion(new MobEffectInstance(Main.THINNING.get(), 1800, 1)));

    public Main() {
        POTION_EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());

        POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Common.SPEC, MOD_ID + "-config.toml");

        System.out.println(MOD_ID + ":registering mod and configs...");
    }

    @SubscribeEvent
    public static void Setup(final FMLCommonSetupEvent e) {

        CustomScaleTypes.init();

        e.enqueueWork(() -> {
                if (Common.useNetherMushrooms.get()) {
                    if (Common.shrinkingPotionRecipeEnabled.get()) {
                        PotionBrewing.addMix(Potions.WEAKNESS, Items.WARPED_FUNGUS, Main.SHRINKING_POTION.get());
                        PotionBrewing.addMix(Main.SHRINKING_POTION.get(), Items.REDSTONE, Main.LONG_SHRINKING_POTION.get());
                        PotionBrewing.addMix(Main.SHRINKING_POTION.get(), Items.GLOWSTONE_DUST, Main.STRONG_SHRINKING_POTION.get());
                    }
                    if (Common.growingPotionRecipeEnabled.get()) {
                        PotionBrewing.addMix(Potions.STRENGTH, Items.CRIMSON_FUNGUS, Main.GROWING_POTION.get());
                        PotionBrewing.addMix(Main.GROWING_POTION.get(), Items.REDSTONE, Main.LONG_GROWING_POTION.get());
                        PotionBrewing.addMix(Main.GROWING_POTION.get(), Items.GLOWSTONE_DUST, Main.STRONG_GROWING_POTION.get());
                    }
                }
                if (!Common.useNetherMushrooms.get()) {
                    if (Common.shrinkingPotionRecipeEnabled.get()) {
                        PotionBrewing.addMix(Potions.WEAKNESS, Items.BROWN_MUSHROOM, Main.SHRINKING_POTION.get());
                        PotionBrewing.addMix(Main.SHRINKING_POTION.get(), Items.REDSTONE, Main.LONG_SHRINKING_POTION.get());
                        PotionBrewing.addMix(Main.SHRINKING_POTION.get(), Items.GLOWSTONE_DUST, Main.STRONG_SHRINKING_POTION.get());
                    }
                    if (Common.growingPotionRecipeEnabled.get()) {
                        PotionBrewing.addMix(Potions.STRENGTH, Items.RED_MUSHROOM, Main.GROWING_POTION.get());
                        PotionBrewing.addMix(Main.GROWING_POTION.get(), Items.REDSTONE, Main.LONG_GROWING_POTION.get());
                        PotionBrewing.addMix(Main.GROWING_POTION.get(), Items.GLOWSTONE_DUST, Main.STRONG_GROWING_POTION.get());
                    }
                }
                if (Common.thinningPotionRecipeEnabled.get()) {
                    PotionBrewing.addMix(Main.SHRINKING_POTION.get(), Items.FERMENTED_SPIDER_EYE, Main.THINNING_POTION.get());
                    PotionBrewing.addMix(Main.THINNING_POTION.get(), Items.REDSTONE, Main.LONG_THINNING_POTION.get());
                    PotionBrewing.addMix(Main.THINNING_POTION.get(), Items.GLOWSTONE_DUST, Main.STRONG_THINNING_POTION.get());
                }
                if (Common.wideningPotionRecipeEnabled.get()) {
                    PotionBrewing.addMix(Main.GROWING_POTION.get(), Items.FERMENTED_SPIDER_EYE, Main.WIDENING_POTION.get());
                    PotionBrewing.addMix(Main.WIDENING_POTION.get(), Items.REDSTONE, Main.LONG_WIDENING_POTION.get());
                    PotionBrewing.addMix(Main.WIDENING_POTION.get(), Items.GLOWSTONE_DUST, Main.STRONG_WIDENING_POTION.get());
                }
        });
    }
}
