package net.chemthunder.amass.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.chemthunder.amass.impl.Amass;
import net.chemthunder.amass.impl.item.GlimmeringBandItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;

public interface AmassItems {
    ItemRegistrant ITEMS = new ItemRegistrant(Amass.MOD_ID);

    Item GLIMMERING_BAND = ITEMS.register("glimmering_band", GlimmeringBandItem::new, new Item.Settings().maxCount(1));

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(AmassItems::buildItemGroupModifier);

    }

    private static void buildItemGroupModifier(FabricItemGroupEntries entries) {
        entries.add(GLIMMERING_BAND);
    }
}
