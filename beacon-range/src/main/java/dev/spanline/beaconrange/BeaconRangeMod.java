package dev.spanline.beaconrange;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BeaconRangeMod implements ModInitializer {
	public static final String MOD_ID = "spanline-beacon-range";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		BeaconRangeConfig.load();
		LOGGER.info(
			"Beacon Range loaded (multiplier={}, bonus={})",
			BeaconRangeConfig.get().rangeMultiplier,
			BeaconRangeConfig.get().rangeBonus
		);
	}
}
