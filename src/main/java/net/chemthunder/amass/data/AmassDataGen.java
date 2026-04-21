package net.chemthunder.amass.data;

import net.chemthunder.amass.data.provider.resources.AmassLangGen;
import net.chemthunder.amass.data.provider.resources.AmassModelGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AmassDataGen implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(AmassModelGen::new);
        pack.addProvider(AmassLangGen::new);
	}
}
