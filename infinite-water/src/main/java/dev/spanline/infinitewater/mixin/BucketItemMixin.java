package dev.spanline.infinitewater.mixin;

import dev.spanline.infinitewater.InfiniteWaterConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Full water buckets stay full after placing. Empty buckets stay empty after
 * picking up a water source — the source is still removed.
 */
@Mixin(BucketItem.class)
public abstract class BucketItemMixin {
	@Shadow
	@Final
	private Fluid content;

	@Unique
	private ItemStack spanline$captured;

	@Unique
	private boolean spanline$keepState;

	@Inject(method = "use", at = @At("HEAD"))
	private void spanline$capture(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
		this.spanline$captured = player.getItemInHand(hand).copy();
		this.spanline$keepState = false;

		if (!InfiniteWaterConfig.get().enabled || player.getAbilities().instabuild) {
			return;
		}

		if (this.content == Fluids.WATER) {
			this.spanline$keepState = true;
			return;
		}

		if (this.content == Fluids.EMPTY) {
			BlockHitResult hit = ItemInvoker.spanline$raycast(level, player, ClipContext.Fluid.SOURCE_ONLY);
			if (hit.getType() == HitResult.Type.BLOCK) {
				BlockPos pos = hit.getBlockPos();
				FluidState fluidState = level.getFluidState(pos);
				this.spanline$keepState = fluidState.is(FluidTags.WATER) && fluidState.isSource();
			}
		}
	}

	@Inject(method = "use", at = @At("RETURN"), cancellable = true)
	private void spanline$restore(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
		ItemStack captured = this.spanline$captured;
		boolean keep = this.spanline$keepState;
		this.spanline$captured = null;
		this.spanline$keepState = false;

		if (!keep || captured == null || !InfiniteWaterConfig.get().enabled) {
			return;
		}

		InteractionResult result = cir.getReturnValue();
		if (!(result instanceof InteractionResult.Success success)) {
			return;
		}

		if (this.content == Fluids.WATER && !captured.is(Items.WATER_BUCKET)) {
			return;
		}
		if (this.content == Fluids.EMPTY && !captured.is(Items.BUCKET)) {
			return;
		}

		player.setItemInHand(hand, captured);
		cir.setReturnValue(success.heldItemTransformedTo(captured));
	}

	@Inject(method = "getEmptySuccessItem", at = @At("HEAD"), cancellable = true)
	private static void spanline$keepFullBucket(ItemStack stack, Player player, CallbackInfoReturnable<ItemStack> cir) {
		if (InfiniteWaterConfig.get().enabled
				&& !player.getAbilities().instabuild
				&& stack.is(Items.WATER_BUCKET)) {
			cir.setReturnValue(stack.copy());
		}
	}
}
