package dev.spanline.villagerlead.mixin;

import dev.spanline.villagerlead.LeadHooks;
import dev.spanline.villagerlead.VillagerLeadConfig;
import net.minecraft.world.entity.PathfinderMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PathfinderMob.class)
public abstract class PathfinderMobMixin {
	@Inject(method = "followLeashSpeed", at = @At("RETURN"), cancellable = true)
	private void spanline$villagerFollowSpeed(CallbackInfoReturnable<Double> cir) {
		PathfinderMob self = (PathfinderMob) (Object) this;
		if (LeadHooks.isMerchant(self) && self.isLeashed() && VillagerLeadConfig.get().enabled) {
			cir.setReturnValue(VillagerLeadConfig.get().clampedFollowSpeed());
		}
	}
}
