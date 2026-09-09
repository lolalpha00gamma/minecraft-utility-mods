package dev.spanline.infinitewater;

import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(InfiniteWaterMod.MOD_ID)
public final class InfiniteWaterMod {
	public static final String MOD_ID = "spanline_infinite_water";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public InfiniteWaterMod() {
		InfiniteWaterConfig.load();
		LOGGER.info("Infinite Water loaded (enabled={})", InfiniteWaterConfig.get().enabled);
	}
}
