package dev.spanline.beaconrange;

import net.fabricmc.api.ModInitializer;

public final class FabricInit implements ModInitializer {
	@Override
	public void onInitialize() {
		BeaconRangeConfig.load();
	}
}
