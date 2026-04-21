package net.chemthunder.amass.impl.index.data;

import net.chemthunder.amass.impl.Amass;
import net.chemthunder.amass.impl.index.AmassEnchantEffects;
import net.chemthunder.amass.impl.index.tag.AmassItemTags;
import net.minecraft.block.Block;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface AmassEnchantments {
    RegistryKey<Enchantment> ACCRETION = create("accretion");

    private static RegistryKey<Enchantment> create(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Amass.id(id));
    }

    static void bootstrap(Registerable<Enchantment> registerable) {
        RegistryEntryLookup<Enchantment> enchantmentLookup = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<EntityType<?>> entityTypeLookup = registerable.getRegistryLookup(RegistryKeys.ENTITY_TYPE);
        RegistryEntryLookup<Block> blockLookup = registerable.getRegistryLookup(RegistryKeys.BLOCK);
        RegistryEntryLookup<Item> itemLookup = registerable.getRegistryLookup(RegistryKeys.ITEM);

        registerable.register(ACCRETION, Enchantment.builder(Enchantment.definition(
                                itemLookup.getOrThrow(AmassItemTags.BANDS),
                                2,
                                1,
                                Enchantment.leveledCost(5, 0),
                                Enchantment.leveledCost(17, 0),
                                7,
                                AttributeModifierSlot.MAINHAND
                        ))
                        .addEffect(AmassEnchantEffects.STATIONARY)
                        .build(ACCRETION.getValue())
        );
    }
}
