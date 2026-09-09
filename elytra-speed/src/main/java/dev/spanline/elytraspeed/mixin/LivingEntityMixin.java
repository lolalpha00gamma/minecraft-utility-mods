package dev.spanline.elytraspeed.mixin;

import dev.spanline.elytraspeed.ElytraSpeedConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@Shadow
	public abstract boolean isFallFlying();

	@Shadow
	public abstract Vec3 getLookAngle();

	@Shadow
	public abstract Vec3 getDeltaMovement();

	@Shadow
	public abstract void setDeltaMovement(Vec3 motion);

	/**
	 * Extra look-direction thrust after vanilla elytra physics.
	 * {@code speedMultiplier} 2.0 doubles the 0.1 vanilla look acceleration.
	 */
	@Inject(method = "travel", at = @At("TAIL"))
	private void spanline$boostElytra(Vec3 input, CallbackInfo ci) {
		if (!this.isFallFlying()) {
			return;
		}

		ElytraSpeedConfig config = ElytraSpeedConfig.get();
		double multiplier = config.clampedMultiplier();
		if (multiplier == 1.0) {
			return;
		}

		double extraThrust = (multiplier - 1.0) * 0.1;
		Vec3 boosted = this.getDeltaMovement().add(this.getLookAngle().scale(extraThrust));
		double max = config.clampedMaxSpeed();
		if (boosted.length() > max) {
			boosted = boosted.normalize().scale(max);
		}
		this.setDeltaMovement(boosted);
	}
}
