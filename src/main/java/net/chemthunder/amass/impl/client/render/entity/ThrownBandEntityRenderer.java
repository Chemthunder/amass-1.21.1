package net.chemthunder.amass.impl.client.render.entity;

import net.chemthunder.amass.impl.entity.ThrownBandEntity;
import net.chemthunder.amass.impl.index.AmassDataComponents;
import net.chemthunder.amass.impl.index.AmassItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import java.util.ArrayList;
import java.util.List;

public class ThrownBandEntityRenderer extends EntityRenderer<ThrownBandEntity> {
    public ThrownBandEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    public Identifier getTexture(ThrownBandEntity entity) {
        return null;
    }

    public void render(ThrownBandEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        ItemStack renderedStack = new ItemStack(AmassItems.GLIMMERING_BAND);
        renderedStack.set(AmassDataComponents.STORED_EFFECTS, List.of(new StatusEffectInstance(StatusEffects.GLOWING)));

        int rotationSpeed = 50;

        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90));

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((entity.age + tickDelta) * -rotationSpeed));

        MinecraftClient.getInstance().getItemRenderer().renderItem(
                renderedStack,
                ModelTransformationMode.THIRD_PERSON_RIGHT_HAND,
                light,
                OverlayTexture.DEFAULT_UV,
                matrices,
                vertexConsumers,
                entity.getWorld(),
                entity.getId()
        );

        matrices.pop();

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
