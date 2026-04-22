package net.chemthunder.amass.mixin.client;

import net.chemthunder.amass.impl.index.AmassDataComponents;
import net.chemthunder.amass.impl.item.GlimmeringBandItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {

    @Inject(method = "getArmPose", at = @At(value = "HEAD"), cancellable = true)
    private static void customArmPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        ItemStack stack = player.getStackInHand(hand);

        if (stack.getItem() instanceof GlimmeringBandItem) {
            if (player.isUsingItem() && !stack.getOrDefault(AmassDataComponents.STORED_EFFECTS, List.of()).isEmpty()) {
                cir.setReturnValue(BipedEntityModel.ArmPose.BOW_AND_ARROW);
            }
        }
    }
}
