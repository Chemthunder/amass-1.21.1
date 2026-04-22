package net.chemthunder.amass.impl.index;

import net.acoyt.acornlib.api.registrants.EntityTypeRegistrant;
import net.chemthunder.amass.impl.Amass;
import net.chemthunder.amass.impl.client.render.entity.ThrownBandEntityRenderer;
import net.chemthunder.amass.impl.entity.ThrownBandEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public interface AmassEntities {
    EntityTypeRegistrant ENTITIES = new EntityTypeRegistrant<>(Amass.MOD_ID);

    EntityType<ThrownBandEntity> THROWN_BAND = ENTITIES.register(
            "thrown_band",
            EntityType.Builder.create(
                    ThrownBandEntity::new, SpawnGroup.MISC
            ).dimensions(1.0f, 0.4f)
    );

    static void init() {}

    static void clientInit() {
        EntityRendererRegistry.register(THROWN_BAND, ThrownBandEntityRenderer::new);
    }
}
