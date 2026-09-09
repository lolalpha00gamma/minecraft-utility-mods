package dev.spanline.infinitewater;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class InfiniteWaterMod implements ModInitializer {
	public static final String MOD_ID = "spanline-infinite-water";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		InfiniteWaterConfig.load();
		LOGGER.info("Infinite Water loaded (enabled={})", InfiniteWaterConfig.get().enabled);
	}
}
