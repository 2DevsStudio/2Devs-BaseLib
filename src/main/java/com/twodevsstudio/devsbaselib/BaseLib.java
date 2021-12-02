package com.twodevsstudio.devsbaselib;

import com.twodevsstudio.simplejsonconfig.SimpleJSONConfig;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

public final class BaseLib extends JavaPlugin {

  @Getter private static BaseLib instance;

  @Getter
  private final Path skinTexturesDirectory =
      Paths.get(getDataFolder().toPath().toString(), "skinTextures");

  @Getter
  private final Path schematicDirectory =
      Paths.get(getDataFolder().toPath().toString(), "schematics");

  public BaseLib() {
    instance = this;

    processIO();
  }

  @Override
  public void onEnable() {
    SimpleJSONConfig.INSTANCE.register(this);
  }

  @Override
  public void onDisable() {}

  private void processIO() {
    File schematicsDirectoryFile = schematicDirectory.toFile();
    File skinTexturesDirectoryFile = skinTexturesDirectory.toFile();

    if (!schematicsDirectoryFile.exists()) {
      if (schematicsDirectoryFile.mkdirs()) {
        getLogger().log(Level.INFO, "Created Schematics Directory");
      }
    }
    if (!skinTexturesDirectoryFile.exists()) {
      if (skinTexturesDirectoryFile.mkdirs()) {
        getLogger().log(Level.INFO, "Created Skin Textures Directory");
      }
    }
  }
}
