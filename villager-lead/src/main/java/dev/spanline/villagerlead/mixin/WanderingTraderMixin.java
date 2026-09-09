package dev.spanline.villagerlead.mixin;

import dev.spanline.villagerlead.LeadHooks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.wanderingtrader.WanderingTrader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WanderingTrader.class)
public abstract class WanderingTraderMixin {
	@Inject(method = "aiStep", at = @At("TAIL"))
	private void spanline$climbWhenLeashed(CallbackInfo ci) {
		LeadHooks.tickLeashed((LivingEntity) (Object) this);
	}
}
