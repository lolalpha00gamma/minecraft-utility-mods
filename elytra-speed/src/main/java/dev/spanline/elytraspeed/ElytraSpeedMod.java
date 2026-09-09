package dev.spanline.elytraspeed;

import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ElytraSpeedMod.MOD_ID)
public final class ElytraSpeedMod {
	public static final String MOD_ID = "spanline_elytra_speed";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public ElytraSpeedMod() {
		ElytraSpeedConfig.load();
		LOGGER.info("Elytra Speed loaded (multiplier={}, maxSpeed={})", ElytraSpeedConfig.get().speedMultiplier, ElytraSpeedConfig.get().maxSpeed);
	}
}
