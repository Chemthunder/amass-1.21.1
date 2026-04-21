package net.chemthunder.amass.data.provider.resources;

import net.chemthunder.amass.impl.index.AmassItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class AmassModelGen extends FabricModelProvider {
    public AmassModelGen(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(AmassItems.GLIMMERING_BAND, Models.HANDHELD);
        itemModelGenerator.register(AmassItems.GLIMMERING_BAND, "_filled", Models.HANDHELD);
    }
}
