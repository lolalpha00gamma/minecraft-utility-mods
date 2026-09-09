package dev.spanline.miningreach;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class MiningReachConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final double MIN_EXTRA = 0.0;
	private static final double MAX_EXTRA = 64.0;
	private static MiningReachConfig instance = new MiningReachConfig();

	public double extraBlocks = 8.0;
	public boolean affectCreative = true;

	private MiningReachConfig() {}

	public static MiningReachConfig get() {
		return instance;
	}

	public double clampedExtra() {
		return Math.max(MIN_EXTRA, Math.min(MAX_EXTRA, extraBlocks));
	}

	public static void load() {
		Path path = FMLPaths.CONFIGDIR.get().resolve("spanline_mining_reach.json");
		if (Files.isRegularFile(path)) {
			try (Reader reader = Files.newBufferedReader(path)) {
				MiningReachConfig loaded = GSON.fromJson(reader, MiningReachConfig.class);
				if (loaded != null) {
					instance = loaded;
				}
			} catch (IOException exception) {
				MiningReachMod.LOGGER.warn("Could not read {}", path, exception);
			}
		}
		instance.extraBlocks = instance.clampedExtra();
		save();
	}

	public static void save() {
		Path path = FMLPaths.CONFIGDIR.get().resolve("spanline_mining_reach.json");
		try {
			Files.createDirectories(path.getParent());
			try (Writer writer = Files.newBufferedWriter(path)) {
				GSON.toJson(instance, writer);
			}
		} catch (IOException exception) {
			MiningReachMod.LOGGER.warn("Could not write {}", path, exception);
		}
	}
}
