package me.calamitous_end.sizeshiftingpotionsforge.effects;

import me.calamitous_end.sizeshiftingpotionsforge.config.Common;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import virtuoel.pehkui.api.ScaleData;
import virtuoel.pehkui.api.ScaleType;

public class MultiplyingSizeStatusEffect extends MobEffect {
    private final ScaleType scaleType;

    public MultiplyingSizeStatusEffect(MobEffectCategory statusEffectType, int i, ScaleType scaleType) {
        super(statusEffectType, i);
        this.scaleType = scaleType;
    }
    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier) {
        final ScaleData scaleData = scaleType.getScaleData(entity);
        double newScale = ((amplifier + 1) * 2);
        newScale = Math.min(newScale, Common.maxSize.get());
        scaleData.setTargetScale((float)newScale);
        scaleData.setScaleTickDelay(scaleData.getScaleTickDelay());
    }
    @Override
    public void removeAttributeModifiers(LivingEntity entity, AttributeMap attributes, int amplifier) {
        final ScaleData scaleData = scaleType.getScaleData(entity);
        scaleData.setTargetScale(1.0F);
        scaleData.setScaleTickDelay(scaleData.getScaleTickDelay());
    }
}
