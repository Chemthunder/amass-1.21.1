package net.chemthunder.amass.impl.entity;

import net.acoyt.acornlib.api.util.ItemUtils;
import net.chemthunder.amass.impl.index.AmassDataComponents;
import net.chemthunder.amass.impl.index.AmassEntities;
import net.chemthunder.amass.impl.index.AmassItems;
import net.chemthunder.amass.impl.index.data.AmassEnchantments;
import net.chemthunder.amass.impl.item.GlimmeringBandItem;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ThrownBandEntity extends ThrownItemEntity {
    public static final TrackedData<ItemStack> BAND_ENTITY = DataTracker.registerData(ThrownBandEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    public boolean hasCollided = false;
    public int lifetimeTicks = 0;

    public ThrownBandEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(BAND_ENTITY, ItemStack.EMPTY);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("HasCollided", hasCollided);
        nbt.putInt("LifetimeTicks", lifetimeTicks);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.hasCollided = nbt.getBoolean("HasCollided");
        this.lifetimeTicks = nbt.getInt("LifetimeTicks");
    }

    protected Item getDefaultItem() {
        return AmassItems.GLIMMERING_BAND;
    }

    public void tick() {
        if (this.hasCollided) {
            if (this.lifetimeTicks > 0) {
                this.lifetimeTicks--;
                this.tickRadius();
                if (this.lifetimeTicks == 0) {
                    ItemStack toDrop = new ItemStack(AmassItems.GLIMMERING_BAND);
                    ItemUtils.enchantStack(getWorld(), toDrop, "amass:accretion");
                    if (this.getBandStack().get(DataComponentTypes.CUSTOM_NAME) != null) {
                        toDrop.set(DataComponentTypes.CUSTOM_NAME, this.getBandStack().get(DataComponentTypes.CUSTOM_NAME));
                    }

                    this.dropStack(toDrop);
                    this.discard();
                }
            }
        }
        super.tick();
    }

    private void tickRadius() {
        for (LivingEntity living : this.getWorld().getNonSpectatingEntities(LivingEntity.class, new Box(this.getBlockPos()).expand(6, 2, 6))) {
            this.getEffects().forEach(living::addStatusEffect);
        }
    }

    protected void onBlockCollision(BlockState state) {
        if (!state.isIn(BlockTags.AIR)) {
            if (!this.hasCollided) {
                this.setVelocity(0, 0, 0);
                this.setPos(this.getX(), this.getY() + 0.5f, this.getZ());
                this.lifetimeTicks = (2 * 20); // 12 seconds
                this.hasCollided = true;
            }
        }
        super.onBlockCollision(state);
    }

    public boolean hasNoGravity() {
        return this.hasCollided;
    }

    public ItemStack asItemStack() {
        return this.getBandStack();
    }

    public void setBandStack(ItemStack givenStack) {
        this.getDataTracker().set(BAND_ENTITY, givenStack);
    }

    public ItemStack getBandStack() {
        return this.getDataTracker().get(BAND_ENTITY);
    }

    public List<StatusEffectInstance> getEffects() {
        return new ArrayList<>(this.getBandStack().getOrDefault(AmassDataComponents.STORED_EFFECTS, List.of()));
    }

    public void writeEffects(List<StatusEffectInstance> instances) {
        if (this.getBandStack().getItem() instanceof GlimmeringBandItem) {
            List<StatusEffectInstance> toApply = new ArrayList<>(instances);
            this.getBandStack().set(AmassDataComponents.STORED_EFFECTS, toApply);
        }
    }
}
