package net.chemthunder.amass.impl.index.tag;

import net.chemthunder.amass.impl.Amass;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public interface AmassStatusEffectTags {
    TagKey<StatusEffect> CANNOT_BE_EXTRACTED = create("cannot_be_extracted");

    private static TagKey<StatusEffect> create(String id) {
        return TagKey.of(RegistryKeys.STATUS_EFFECT, Amass.id(id));
    }
}
