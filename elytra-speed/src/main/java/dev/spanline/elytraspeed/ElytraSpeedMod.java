package dev.spanline.elytraspeed;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ElytraSpeedMod implements ModInitializer {
	public static final String MOD_ID = "spanline-elytra-speed";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ElytraSpeedConfig.load();
		LOGGER.info(
			"Elytra Speed loaded (multiplier={}, maxSpeed={})",
			ElytraSpeedConfig.get().speedMultiplier,
			ElytraSpeedConfig.get().maxSpeed
		);
	}
}
