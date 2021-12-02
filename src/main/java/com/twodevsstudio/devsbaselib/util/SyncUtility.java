package com.twodevsstudio.devsbaselib.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.logging.Level;

@UtilityClass
public class SyncUtility {

  public String getServerId(JavaPlugin javaPlugin) {
    Path serverIdFilePath = javaPlugin.getDataFolder().toPath().resolve("server_id.bin");
    File serverIdFile = serverIdFilePath.toFile();
    try {
      if (!serverIdFile.exists()) {
        String randomUUID = UUID.randomUUID().toString();
        Files.writeString(serverIdFilePath, randomUUID);
        return randomUUID;
      }
      return new String(Files.readAllBytes(serverIdFilePath));
    } catch (IOException exception) {
      Bukkit.getLogger()
          .log(Level.SEVERE, "Error while loading server id, can't start without it.");
      javaPlugin.getServer().shutdown();
    }
    return null;
  }
}
