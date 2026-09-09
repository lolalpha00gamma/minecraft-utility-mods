package dev.spanline.beaconrange;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class BeaconRangeConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static BeaconRangeConfig instance = new BeaconRangeConfig();

	public double rangeMultiplier = 3.0;
	public double rangeBonus = 0.0;

	private BeaconRangeConfig() {}

	private static Path configDir() {
		try {
			Class<?> paths = Class.forName("net.minecraftforge.fml.loading.FMLPaths");
			Object dir = paths.getField("CONFIGDIR").get(null);
			return (Path) dir.getClass().getMethod("get").invoke(dir);
		} catch (Throwable ignored) {
		}
		try {
			Class<?> loader = Class.forName("net.fabricmc.loader.api.FabricLoader");
			Object inst = loader.getMethod("getInstance").invoke(null);
			return (Path) inst.getClass().getMethod("getConfigDir").invoke(inst);
		} catch (Throwable ignored) {
		}
		return Path.of("config");
	}

	public static BeaconRangeConfig get() {
		return instance;
	}

	public double scale(double vanillaRange) {
		double multiplier = Math.max(0.1, Math.min(50.0, rangeMultiplier));
		double bonus = Math.max(0.0, Math.min(10000.0, rangeBonus));
		return vanillaRange * multiplier + bonus;
	}

	public static void load() {
		Path path = configDir().resolve("spanline_beacon_range.json");
		if (Files.isRegularFile(path)) {
			try (Reader reader = Files.newBufferedReader(path)) {
				BeaconRangeConfig loaded = GSON.fromJson(reader, BeaconRangeConfig.class);
				if (loaded != null) {
					instance = loaded;
				}
			} catch (IOException exception) {
				BeaconRangeMod.LOGGER.warn("Could not read {}", path, exception);
			}
		}
		save();
	}

	public static void save() {
		Path path = configDir().resolve("spanline_beacon_range.json");
		try {
			Files.createDirectories(path.getParent());
			try (Writer writer = Files.newBufferedWriter(path)) {
				GSON.toJson(instance, writer);
			}
		} catch (IOException exception) {
			BeaconRangeMod.LOGGER.warn("Could not write {}", path, exception);
		}
	}
}
