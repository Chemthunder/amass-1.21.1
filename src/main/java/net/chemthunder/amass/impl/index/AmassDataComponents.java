package net.chemthunder.amass.impl.index;

import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.chemthunder.amass.impl.Amass;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.effect.StatusEffectInstance;

import java.util.List;

public interface AmassDataComponents {
    ComponentTypeRegistrant DATA_COMPONENTS = new ComponentTypeRegistrant(Amass.MOD_ID);

    ComponentType<List<StatusEffectInstance>> STORED_EFFECTS = DATA_COMPONENTS.register("stored_effects", builder -> builder.codec(StatusEffectInstance.CODEC.listOf()));

    static void init() {}
}
