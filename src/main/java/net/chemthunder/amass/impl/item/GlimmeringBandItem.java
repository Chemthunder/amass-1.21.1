package net.chemthunder.amass.impl.item;

import com.nitron.nitrogen.util.interfaces.ColorableItem;
import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.chemthunder.amass.impl.Amass;
import net.chemthunder.amass.impl.index.AmassDataComponents;
import net.chemthunder.amass.impl.index.tag.AmassStatusEffectTags;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GlimmeringBandItem extends Item implements ModelVaryingItem, ColorableItem {
    public GlimmeringBandItem(Settings settings) {
        super(settings.component(AmassDataComponents.STORED_EFFECTS, List.of()));
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        List<StatusEffectInstance> core = stack.getOrDefault(AmassDataComponents.STORED_EFFECTS, List.of());
        List<StatusEffectInstance> toApply = new ArrayList<>(core);

        if (toApply.isEmpty()) {
            if (user.isSneaking()) {
                if (!user.getStatusEffects().isEmpty()) {

                    user.getStatusEffects().forEach(statusEffectInstance -> {
                        if (!statusEffectInstance.getEffectType().isIn(AmassStatusEffectTags.CANNOT_BE_EXTRACTED)) {
                            toApply.add(statusEffectInstance);
                        }
                    });

                    if (world.isClient) {
                        user.swingHand(hand);
                        user.playSoundToPlayer(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.PLAYERS, 1, 1);
                    }

                    stack.set(AmassDataComponents.STORED_EFFECTS, toApply);
                }
            }
        }
        return super.use(world, user, hand);
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        List<StatusEffectInstance> core = stack.getOrDefault(AmassDataComponents.STORED_EFFECTS, List.of());
        List<StatusEffectInstance> effects = new ArrayList<>(core);

        if (!effects.isEmpty()) {
            effects.forEach(instance -> {
                tooltip.add(Text.translatable(instance.getEffectType().value().getTranslationKey()).withColor(instance.getEffectType().value().getColor()).append(Text.of(" ")).append(instance.getAmplifier() == 0 ? Text.of("") : Text.translatable("enchantment.level." + instance.getAmplifier()).withColor(instance.getEffectType().value().getColor())));

                tooltip.add(Text.literal(instance.getDuration() / 20 + " seconds").formatted(Formatting.ITALIC).formatted(Formatting.DARK_GRAY));
            });
        }

        super.appendTooltip(stack, context, tooltip, type);
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().withColor(endColor(stack));
    }

    public int startColor(ItemStack itemStack) {
        return 0xFF5d391b;
    }

    public int endColor(ItemStack itemStack) {
        return 0xFFcb9e41;
    }

    public int backgroundColor(ItemStack itemStack) {
        return 0xFF15120e;
    }

    public Identifier getModel(ModelTransformationMode modelTransformationMode, ItemStack itemStack, @Nullable LivingEntity livingEntity) {
        return itemStack.getOrDefault(AmassDataComponents.STORED_EFFECTS, List.of()).isEmpty() ? Amass.id("glimmering_band") : Amass.id("glimmering_band_filled");
    }

    public List<Identifier> getModelsToLoad() {
        return Arrays.asList(
                Amass.id("glimmering_band"),
                Amass.id("glimmering_band_filled")
        );
    }
}