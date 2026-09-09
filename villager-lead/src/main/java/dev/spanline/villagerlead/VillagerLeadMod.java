package dev.spanline.villagerlead;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class VillagerLeadMod implements ModInitializer {
	public static final String MOD_ID = "spanline-villager-lead";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		VillagerLeadConfig.load();
		LOGGER.info(
			"Villager Lead loaded (stepHeight={}, climbBoost={})",
			VillagerLeadConfig.get().stepHeight,
			VillagerLeadConfig.get().climbBoost
		);
	}
}
