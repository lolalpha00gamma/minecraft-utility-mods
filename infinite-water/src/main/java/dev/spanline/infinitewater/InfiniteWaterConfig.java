package dev.spanline.infinitewater;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

	public static InfiniteWaterConfig get() {
		return instance;
	}

	public static void load() {
		Path path = configDir().resolve("spanline_infinite_water.json");
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
		Path path = configDir().resolve("spanline_infinite_water.json");
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
