package dev.spanline.elytraspeed;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

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
		Path path = FabricLoader.getInstance().getConfigDir().resolve("spanline-elytra-speed.json");
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
		Path path = FabricLoader.getInstance().getConfigDir().resolve("spanline-elytra-speed.json");
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
