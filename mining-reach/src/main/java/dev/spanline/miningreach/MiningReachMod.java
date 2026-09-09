package dev.spanline.miningreach;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MiningReachMod implements ModInitializer {
	public static final String MOD_ID = "spanline-mining-reach";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MiningReachConfig.load();
		LOGGER.info(
			"Mining Reach loaded (extraBlocks={}, affectCreative={})",
			MiningReachConfig.get().extraBlocks,
			MiningReachConfig.get().affectCreative
		);
	}
}
