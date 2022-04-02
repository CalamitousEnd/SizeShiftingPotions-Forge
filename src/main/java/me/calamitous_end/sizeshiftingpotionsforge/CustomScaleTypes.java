package me.calamitous_end.sizeshiftingpotionsforge;


import net.minecraft.resources.ResourceLocation;
import virtuoel.pehkui.api.*;


public class CustomScaleTypes {


    public static final ScaleModifier SIZE_MODIFIER = registerModifier("size_modifier", new TypedScaleModifier(() -> CustomScaleTypes.SIZE));
    public static final ScaleModifier THICKNESS_MODIFIER = registerModifier("thickness_modifier", new TypedScaleModifier(() -> CustomScaleTypes.THICKNESS));

    public static final ScaleType SIZE = registerScale("size", CustomScaleTypes.SIZE_MODIFIER);
    public static final ScaleType THICKNESS = registerScale("thickness", CustomScaleTypes.THICKNESS_MODIFIER);



    private static ScaleType registerScale(String id, ScaleModifier dependantModifier) {
        final ScaleType.Builder builder = ScaleType.Builder.create().affectsDimensions();

        builder.addDependentModifier(dependantModifier);

        return ScaleRegistries.register(ScaleRegistries.SCALE_TYPES,
                new ResourceLocation(Main.MOD_ID, id),
                builder.build());
    }
    public static ScaleModifier registerModifier(String id, ScaleModifier modifier) {
        return ScaleRegistries.register(ScaleRegistries.SCALE_MODIFIERS, new ResourceLocation(Main.MOD_ID, id), modifier);
    }

    public static void init() {
        ScaleTypes.HEIGHT.getDefaultBaseValueModifiers().add(SIZE_MODIFIER);
        ScaleTypes.WIDTH.getDefaultBaseValueModifiers().add(SIZE_MODIFIER);
        ScaleTypes.VISIBILITY.getDefaultBaseValueModifiers().add(SIZE_MODIFIER);
        ScaleTypes.MOTION.getDefaultBaseValueModifiers().add(SIZE_MODIFIER);
        ScaleTypes.HEALTH.getDefaultBaseValueModifiers().add(SIZE_MODIFIER);
        ScaleTypes.DEFENSE.getDefaultBaseValueModifiers().add(SIZE_MODIFIER);
        ScaleTypes.ATTACK.getDefaultBaseValueModifiers().add(SIZE_MODIFIER);
        ScaleTypes.KNOCKBACK.getDefaultBaseValueModifiers().add(SIZE_MODIFIER);
        ScaleTypes.WIDTH.getDefaultBaseValueModifiers().add(THICKNESS_MODIFIER);
    }
}
