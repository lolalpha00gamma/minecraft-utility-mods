package dev.spanline.beaconrange;

import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(BeaconRangeMod.MOD_ID)
public final class BeaconRangeMod {
	public static final String MOD_ID = "spanline_beacon_range";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public BeaconRangeMod() {
		BeaconRangeConfig.load();
		LOGGER.info("Beacon Range loaded (multiplier={}, bonus={})", BeaconRangeConfig.get().rangeMultiplier, BeaconRangeConfig.get().rangeBonus);
	}
}
