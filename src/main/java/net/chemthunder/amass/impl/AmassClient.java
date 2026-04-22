package net.chemthunder.amass.impl;

import net.chemthunder.amass.impl.index.AmassEntities;
import net.fabricmc.api.ClientModInitializer;

public class AmassClient implements ClientModInitializer {
    public void onInitializeClient() {
        AmassEntities.clientInit();
    }
}
