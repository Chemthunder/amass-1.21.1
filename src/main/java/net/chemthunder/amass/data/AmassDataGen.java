package net.chemthunder.amass.data;

import net.chemthunder.amass.data.provider.AmassDynamicRegistryGen;
import net.chemthunder.amass.data.provider.resources.AmassLangGen;
import net.chemthunder.amass.data.provider.resources.AmassModelGen;
import net.chemthunder.amass.data.provider.tag.AmassItemTagGen;
import net.chemthunder.amass.data.provider.tag.AmassStatusEffectTagGen;
import net.chemthunder.amass.impl.index.data.AmassEnchantments;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class AmassDataGen implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(AmassDynamicRegistryGen::new);


        pack.addProvider(AmassModelGen::new);
        pack.addProvider(AmassLangGen::new);

        pack.addProvider(AmassItemTagGen::new);
        pack.addProvider(AmassStatusEffectTagGen::new);
	}

    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, AmassEnchantments::bootstrap);
    }
}
