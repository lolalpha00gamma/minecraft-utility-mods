package dev.spanline.infinitewater;

import net.fabricmc.api.ModInitializer;

public final class FabricInit implements ModInitializer {
	@Override
	public void onInitialize() {
		InfiniteWaterConfig.load();
	}
}
