package net.chemthunder.amass.impl.index;

import net.acoyt.acornlib.api.registrants.EnchantmentEffectRegistrant;
import net.chemthunder.amass.impl.Amass;
import net.minecraft.component.ComponentType;
import net.minecraft.util.Unit;

public interface AmassEnchantEffects {
    EnchantmentEffectRegistrant ENCHANTMENT_EFFECTS = new EnchantmentEffectRegistrant(Amass.MOD_ID);

    ComponentType<Unit> STATIONARY = ENCHANTMENT_EFFECTS.register("stationary", unitBuilder -> unitBuilder.codec(Unit.CODEC));

    static void init() {}
}
