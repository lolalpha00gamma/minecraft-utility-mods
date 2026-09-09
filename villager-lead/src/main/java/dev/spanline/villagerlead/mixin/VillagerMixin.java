package dev.spanline.villagerlead.mixin;

import dev.spanline.villagerlead.LeadHooks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.villager.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public abstract class VillagerMixin {
	@Inject(method = "tick", at = @At("TAIL"))
	private void spanline$climbWhenLeashed(CallbackInfo ci) {
		LeadHooks.tickLeashed((LivingEntity) (Object) this);
	}
}
