package net.chemthunder.amass.data.provider.resources;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AmassLangGen extends FabricLanguageProvider {
    public AmassLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("item.amass.glimmering_band", "Glimmering Band");

        translationBuilder.add("enchantment.amass.accretion", "Accretion");
        translationBuilder.add("enchantment.amass.accretion.desc", "Allows for the Glimmering Band to be thrown, creating a cloud of the stored effects.");
    }
}
