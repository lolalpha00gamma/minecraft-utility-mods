package dev.spanline.beaconrange.mixin;

import dev.spanline.beaconrange.BeaconRangeConfig;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityMixin {
	/**
	 * Vanilla range is {@code levels * 10 + 10}, then {@code AABB.inflate(range)}.
	 * Scale that radius by the configured multiplier and bonus.
	 */
	@ModifyArg(
		method = "applyEffects",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/phys/AABB;inflate(D)Lnet/minecraft/world/phys/AABB;"
		),
		index = 0
	)
	private static double spanline$scaleBeaconRange(double vanillaRange) {
		return BeaconRangeConfig.get().scale(vanillaRange);
	}
}
