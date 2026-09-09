package dev.spanline.villagerlead;

import net.fabricmc.api.ModInitializer;

public final class FabricInit implements ModInitializer {
	@Override
	public void onInitialize() {
		VillagerLeadConfig.load();
	}
}
