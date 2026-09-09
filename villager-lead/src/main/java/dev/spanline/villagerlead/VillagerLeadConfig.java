package dev.spanline.villagerlead;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class VillagerLeadConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static VillagerLeadConfig instance = new VillagerLeadConfig();

	public boolean enabled = true;
	public double stepHeight = 1.25;
	public double climbBoost = 0.42;
	public double followSpeed = 1.25;

	private VillagerLeadConfig() {}

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

	public static VillagerLeadConfig get() {
		return instance;
	}

	public double clampedStepHeight() {
		return Math.max(0.6, Math.min(3.0, stepHeight));
	}

	public double clampedClimbBoost() {
		return Math.max(0.0, Math.min(1.2, climbBoost));
	}

	public double clampedFollowSpeed() {
		return Math.max(0.6, Math.min(2.5, followSpeed));
	}

	public static void load() {
		Path path = configDir().resolve("spanline_villager_lead.json");
		if (Files.isRegularFile(path)) {
			try (Reader reader = Files.newBufferedReader(path)) {
				VillagerLeadConfig loaded = GSON.fromJson(reader, VillagerLeadConfig.class);
				if (loaded != null) {
					instance = loaded;
				}
			} catch (IOException exception) {
				VillagerLeadMod.LOGGER.warn("Could not read {}", path, exception);
			}
		}
		save();
	}

	public static void save() {
		Path path = configDir().resolve("spanline_villager_lead.json");
		try {
			Files.createDirectories(path.getParent());
			try (Writer writer = Files.newBufferedWriter(path)) {
				GSON.toJson(instance, writer);
			}
		} catch (IOException exception) {
			VillagerLeadMod.LOGGER.warn("Could not write {}", path, exception);
		}
	}
}
