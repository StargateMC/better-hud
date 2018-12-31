package jobicade.betterhud.config;

import static jobicade.betterhud.BetterHud.MODID;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;

import jobicade.betterhud.util.HudConfig;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;

public class ConfigManager implements IResourceManagerReloadListener {
    public static final ResourceLocation CONFIGS_LOCATION = new ResourceLocation(MODID, "configs/configs.json");
    private PathMatcher pathMatcher;

    private IResourceManager resourceManager;
    private Gson gson = new Gson();
    private List<ConfigSlot> internalConfigs;
    private Path rootDirectory;

    private Path configPath;
    private HudConfig config;

    public ConfigManager(Path configPath, Path rootDirectory) {
        this.setRootDirectory(rootDirectory);
        this.reloadConfig();
    }

    public void reloadConfig() {
        this.config = new HudConfig(configPath.toFile());
    }

    public HudConfig getConfig() {
        return config;
    }

    public Path getConfigPath() {
        return configPath;
    }

    public Path getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(Path rootDirectory) {
        this.rootDirectory = rootDirectory;
        this.pathMatcher = rootDirectory.getFileSystem().getPathMatcher("glob:**/*.cfg");
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        this.internalConfigs = null;
    }

    public List<ConfigSlot> getSlots() {
        return Stream.concat(getInternalSlots().stream(), streamExternalSlots()).collect(ImmutableList.toImmutableList());
    }

    public List<ConfigSlot> getInternalSlots() {
        if(internalConfigs == null) {
            internalConfigs = streamInternalSlots().collect(ImmutableList.toImmutableList());
        }
        return internalConfigs;
    }

    private Stream<ConfigSlot> streamInternalSlots() {
        try {
            return resourceManager.getAllResources(CONFIGS_LOCATION).stream().flatMap(this::streamJsonSlots);
        } catch(IOException e) {
            return Stream.empty();
        }
    }

    private Stream<ConfigSlot> streamJsonSlots(IResource resource) {
        try(Reader reader = new InputStreamReader(resource.getInputStream())) {
            String[] paths = gson.fromJson(reader, String[].class);
            return Arrays.stream(paths).map(path -> new ResourceConfigSlot(new ResourceLocation(path)));
        } catch(IOException e) {
            return Stream.empty();
        }
    }

    public List<ConfigSlot> getExternalSlots() {
        return streamExternalSlots().collect(ImmutableList.toImmutableList());
    }

    private Stream<ConfigSlot> streamExternalSlots() {
        try {
            return Files.walk(rootDirectory).filter(pathMatcher::matches).map(FileConfigSlot::new);
        } catch(IOException e) {
            return Stream.empty();
        }
    }
}
