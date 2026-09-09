package dev.spanline.villagerlead;

import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(VillagerLeadMod.MOD_ID)
public final class VillagerLeadMod {
	public static final String MOD_ID = "spanline_villager_lead";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public VillagerLeadMod() {
		VillagerLeadConfig.load();
		LOGGER.info("Villager Lead loaded (stepHeight={}, climbBoost={})", VillagerLeadConfig.get().stepHeight, VillagerLeadConfig.get().climbBoost);
	}
}
