package dev.spanline.elytraspeed;

import net.fabricmc.api.ModInitializer;

public final class FabricInit implements ModInitializer {
	@Override
	public void onInitialize() {
		ElytraSpeedConfig.load();
	}
}
