package dev.spanline.villagerlead.mixin;

import dev.spanline.villagerlead.VillagerLeadConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractVillager.class)
public abstract class AbstractVillagerMixin {
	private static final double VANILLA_STEP = 0.6;

	@Inject(method = "canBeLeashed", at = @At("HEAD"), cancellable = true)
	private void spanline$allowLead(CallbackInfoReturnable<Boolean> cir) {
		if (VillagerLeadConfig.get().enabled) {
			cir.setReturnValue(true);
		}
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void spanline$climbWhenLeashed(CallbackInfo ci) {
		AbstractVillager self = (AbstractVillager) (Object) this;
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
