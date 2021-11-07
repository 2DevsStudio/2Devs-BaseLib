package com.twodevsstudio.devsbaselib;

import com.twodevsstudio.simplejsonconfig.SimpleJSONConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class BaseLib extends JavaPlugin {

  @Override
  public void onEnable() {
    SimpleJSONConfig.INSTANCE.register(this);
  }

  @Override
  public void onDisable() {}
}
