package dev.spanline.miningreach;

import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(MiningReachMod.MOD_ID)
public final class MiningReachMod {
	public static final String MOD_ID = "spanline_mining_reach";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public MiningReachMod() {
		MiningReachConfig.load();
		LOGGER.info("Mining Reach loaded (extraBlocks={})", MiningReachConfig.get().extraBlocks);
	}
}
