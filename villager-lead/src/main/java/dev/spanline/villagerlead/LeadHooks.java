package dev.spanline.villagerlead;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;

public final class LeadHooks {
	private static final double VANILLA_STEP = 0.6;

	private LeadHooks() {}

	public static boolean isMerchant(Entity entity) {
		String id = entity.getEncodeId();
		return "minecraft:villager".equals(id) || "minecraft:wandering_trader".equals(id);
	}

	public static void tickLeashed(LivingEntity self) {
		VillagerLeadConfig config = VillagerLeadConfig.get();
		if (!config.enabled) {
			return;
		}

		var step = self.getAttribute(Attributes.STEP_HEIGHT);
		boolean leashed = self.isLeashed();
		if (step != null) {
			step.setBaseValue(leashed ? config.clampedStepHeight() : VANILLA_STEP);
		}
		if (!leashed) {
			return;
		}

		Entity holder = self.getLeashHolder();
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
	}
}
