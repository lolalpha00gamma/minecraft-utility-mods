package dev.spanline.miningreach;

import net.fabricmc.api.ModInitializer;

public final class FabricInit implements ModInitializer {
	@Override
	public void onInitialize() {
		MiningReachConfig.load();
	}
}
