package net.chemthunder.amass.impl;

import net.chemthunder.amass.impl.index.AmassDataComponents;
import net.chemthunder.amass.impl.index.AmassEnchantEffects;
import net.chemthunder.amass.impl.index.AmassEntities;
import net.chemthunder.amass.impl.index.AmassItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Amass implements ModInitializer {
	public static final String MOD_ID = "amass";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
        AmassItems.init();
        AmassDataComponents.init();
        AmassEnchantEffects.init();
        AmassEntities.init();

		LOGGER.info("Stockpiling...");
	}

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}