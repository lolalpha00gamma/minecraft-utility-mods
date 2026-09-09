package dev.spanline.villagerlead.mixin;

import dev.spanline.villagerlead.VillagerLeadConfig;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractVillager.class)
public abstract class AbstractVillagerMixin {
	@Inject(method = "canBeLeashed", at = @At("HEAD"), cancellable = true)
	private void spanline$allowLead(CallbackInfoReturnable<Boolean> cir) {
		if (VillagerLeadConfig.get().enabled) {
			cir.setReturnValue(true);
		}
	}
}
