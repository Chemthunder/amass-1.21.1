package net.chemthunder.amass.data.provider.tag;

import net.chemthunder.amass.impl.index.tag.AmassStatusEffectTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AmassStatusEffectTagGen extends FabricTagProvider<StatusEffect> {
    public AmassStatusEffectTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.STATUS_EFFECT, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getOrCreateTagBuilder(AmassStatusEffectTags.CANNOT_BE_EXTRACTED)
                .add(StatusEffects.WITHER.value())
                .add(StatusEffects.HEALTH_BOOST.value())
                .add(StatusEffects.INSTANT_DAMAGE.value())
                .add(StatusEffects.INSTANT_HEALTH.value())
                .add(StatusEffects.RAID_OMEN.value())
                .add(StatusEffects.TRIAL_OMEN.value())
                .add(StatusEffects.BAD_OMEN.value())
                .setReplace(false);
    }
}
