package net.chemthunder.amass.data.provider.tag;

import net.chemthunder.amass.impl.index.AmassItems;
import net.chemthunder.amass.impl.index.tag.AmassItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AmassItemTagGen extends FabricTagProvider.ItemTagProvider {
    public AmassItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getOrCreateTagBuilder(AmassItemTags.BANDS)
                .add(AmassItems.GLIMMERING_BAND)
                .setReplace(false);
    }
}
