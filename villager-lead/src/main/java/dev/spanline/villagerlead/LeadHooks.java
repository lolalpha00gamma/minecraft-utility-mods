package dev.spanline.villagerlead;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;

public final class LeadHooks {
	private static final double VANILLA_STEP = 0.6;

	private LeadHooks() {}

	public static void tickLeashed(LivingEntity self) {
		try {
			VillagerLeadConfig config = VillagerLeadConfig.get();
			if (!config.enabled) {
				return;
			}
			if (!(self instanceof Leashable leashable)) {
				return;
			}

			boolean leashed = leashable.isLeashed();
			var step = self.getAttribute(Attributes.STEP_HEIGHT);
			if (step != null) {
				double desired = leashed ? config.clampedStepHeight() : VANILLA_STEP;
				if (Math.abs(step.getBaseValue() - desired) > 0.001) {
					step.setBaseValue(desired);
				}
			}
			if (!leashed) {
				return;
			}

			Entity holder = leashable.getLeashHolder();
			if (holder == null) {
				return;
			}

			double dy = holder.getY() - self.getY();
			if (dy <= 0.5) {
				return;
			}

			boolean blocked = self.horizontalCollision || dy > 1.0;
			if (!blocked) {
				return;
			}

			Vec3 delta = self.getDeltaMovement();
			double boost = Math.min(config.clampedClimbBoost(), 0.18 * dy);
			if (delta.y < boost) {
				self.setDeltaMovement(delta.x, boost, delta.z);
			}
		} catch (Throwable ignored) {
		}
	}
}
