package dev.spanline.villagerlead;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.loading.FMLPaths;

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
		Path path = FMLPaths.CONFIGDIR.get().resolve("spanline_villager_lead.json");
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
		Path path = FMLPaths.CONFIGDIR.get().resolve("spanline_villager_lead.json");
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
