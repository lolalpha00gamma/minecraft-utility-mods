package dev.spanline.elytraspeed;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ElytraSpeedConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static ElytraSpeedConfig instance = new ElytraSpeedConfig();

	public double speedMultiplier = 2.0;
	public double maxSpeed = 6.0;

	private ElytraSpeedConfig() {}

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

	public static ElytraSpeedConfig get() {
		return instance;
	}

	public double clampedMultiplier() {
		return Math.max(0.25, Math.min(8.0, speedMultiplier));
	}

	public double clampedMaxSpeed() {
		return Math.max(1.0, Math.min(20.0, maxSpeed));
	}

	public static void load() {
		Path path = configDir().resolve("spanline_elytra_speed.json");
		if (Files.isRegularFile(path)) {
			try (Reader reader = Files.newBufferedReader(path)) {
				ElytraSpeedConfig loaded = GSON.fromJson(reader, ElytraSpeedConfig.class);
				if (loaded != null) {
					instance = loaded;
				}
			} catch (IOException exception) {
				ElytraSpeedMod.LOGGER.warn("Could not read {}", path, exception);
			}
		}
		save();
	}

	public static void save() {
		Path path = configDir().resolve("spanline_elytra_speed.json");
		try {
			Files.createDirectories(path.getParent());
			try (Writer writer = Files.newBufferedWriter(path)) {
				GSON.toJson(instance, writer);
			}
		} catch (IOException exception) {
			ElytraSpeedMod.LOGGER.warn("Could not write {}", path, exception);
		}
	}
}
