package dev.spanline.miningreach.mixin;

import dev.spanline.miningreach.MiningReachConfig;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {
	@Inject(method = "blockInteractionRange", at = @At("RETURN"), cancellable = true)
	private void spanline$extendMiningRange(CallbackInfoReturnable<Double> cir) {
		MiningReachConfig config = MiningReachConfig.get();
		Player self = (Player) (Object) this;
		if (!config.affectCreative && self.getAbilities().instabuild) {
			return;
		}
		cir.setReturnValue(cir.getReturnValueD() + config.clampedExtra());
	}
}
