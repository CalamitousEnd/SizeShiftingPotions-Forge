package me.calamitous_end.sizeshiftingpotionsforge.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Common {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> shrinkingPotionRecipeEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> growingPotionRecipeEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> thinningPotionRecipeEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> wideningPotionRecipeEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> useNetherMushrooms;
    public static final ForgeConfigSpec.ConfigValue<Double> minSize;
    public static final ForgeConfigSpec.ConfigValue<Double> maxSize;

    static {
        BUILDER.push("CONFIG").comment("These options require you to restart your game.");

        minSize = BUILDER.define("Min Size", 0.1);
        maxSize = BUILDER.define("Max Size", 10.0);
        useNetherMushrooms = BUILDER.define("Potion Recipes use Nether Mushrooms?", false);
        shrinkingPotionRecipeEnabled = BUILDER.define("Enable Shrinking Potion Recipe", true);
        growingPotionRecipeEnabled = BUILDER.define("Enable Growing Potion Recipe", true);
        thinningPotionRecipeEnabled = BUILDER.define("Enable Thinning Potion Recipe", true);
        wideningPotionRecipeEnabled = BUILDER.define("Enable Widening Potion Recipe", true);


        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}