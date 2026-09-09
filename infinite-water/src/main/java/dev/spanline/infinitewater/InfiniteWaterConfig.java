package dev.spanline.infinitewater;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public final class InfiniteWaterConfig {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static InfiniteWaterConfig instance = new InfiniteWaterConfig();

	public boolean enabled = true;

	private InfiniteWaterConfig() {}

	public static InfiniteWaterConfig get() {
		return instance;
	}

	public static void load() {
		Path path = FMLPaths.CONFIGDIR.get().resolve("spanline_infinite_water.json");
		if (Files.isRegularFile(path)) {
			try (Reader reader = Files.newBufferedReader(path)) {
				InfiniteWaterConfig loaded = GSON.fromJson(reader, InfiniteWaterConfig.class);
				if (loaded != null) {
					instance = loaded;
				}
			} catch (IOException exception) {
				InfiniteWaterMod.LOGGER.warn("Could not read {}", path, exception);
			}
		}
		save();
	}

	public static void save() {
		Path path = FMLPaths.CONFIGDIR.get().resolve("spanline_infinite_water.json");
		try {
			Files.createDirectories(path.getParent());
			try (Writer writer = Files.newBufferedWriter(path)) {
				GSON.toJson(instance, writer);
			}
		} catch (IOException exception) {
			InfiniteWaterMod.LOGGER.warn("Could not write {}", path, exception);
		}
	}
}
