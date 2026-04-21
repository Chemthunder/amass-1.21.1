package net.chemthunder.amass.impl.index.tag;

import net.chemthunder.amass.impl.Amass;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public interface AmassItemTags {
    TagKey<Item> BANDS = create("bands");

    private static TagKey<Item> create(String id) {
        return TagKey.of(RegistryKeys.ITEM, Amass.id(id));
    }
}
