package com.twodevsstudio.devsbaselib.base;

import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ClassCanBeRecord")
@Data
public final class Cuboid implements Cloneable {

  private final Location minimum;
  private final Location maximum;

  public Cuboid(@NotNull Location minimum, @NotNull Location maximum) {
    if (minimum.getX() > maximum.getX()) {
      double x = minimum.getX();
      minimum.setX(maximum.getX());
      maximum.setX(x);
    }
    if (minimum.getY() > maximum.getY()) {
      double y = minimum.getY();
      minimum.setY(maximum.getY());
      maximum.setY(y);
    }
    if (minimum.getZ() > maximum.getZ()) {
      double z = minimum.getZ();
      minimum.setZ(maximum.getZ());
      maximum.setZ(z);
    }
    this.minimum = minimum;
    this.maximum = maximum;
  }

  public boolean isInside(@NotNull Location location) {

    int minX = minimum.getBlockX();
    int maxX = maximum.getBlockX();
    int x = location.getBlockX();

    int minY = minimum.getBlockY();
    int maxY = maximum.getBlockY();
    int y = location.getBlockY();

    int minZ = minimum.getBlockZ();
    int maxZ = maximum.getBlockZ();
    int z = location.getBlockZ();
    return ((x >= minX) && (x <= maxX))
        && ((y >= minY) && (y <= maxY))
        && ((z >= minZ) && (z <= maxZ));
  }

  public int getBlockCount() {
    Location copy = minimum.clone();
    int count = 0;

    for (int x = minimum.getBlockX(); x <= maximum.getBlockX(); x++) {
      for (int z = minimum.getBlockZ(); z <= maximum.getBlockZ(); z++) {
        for (int y = minimum.getBlockY(); y <= maximum.getBlockY(); y++) {
          copy.setX(x);
          copy.setY(y);
          copy.setZ(z);
          copy.getBlock();
          if (copy.getBlock().getType() != Material.AIR) {
            count++;
          }
        }
      }
    }
    return count;
  }

  public List<Block> getBlocks() {
    List<Block> blockList = new ArrayList<>();

    for (int x = minimum.getBlockX(); x <= maximum.getBlockX(); x++) {
      for (int y = minimum.getBlockY(); y <= maximum.getBlockY(); y++) {
        for (int z = minimum.getBlockZ(); z <= maximum.getBlockZ(); z++) {
          Location location = new Location(minimum.getWorld(), x, y, z);
          Block block = location.getBlock();
          blockList.add(block);
        }
      }
    }
    return blockList;
  }

  public void sendParticles(Particle particle) {
    for (int x = minimum.getBlockX(); x <= maximum.getBlockX(); x++) {
      for (int y = minimum.getBlockY(); y <= maximum.getBlockY(); y++) {
        for (int z = minimum.getBlockZ(); z <= maximum.getBlockZ(); z++) {
          Location location = new Location(minimum.getWorld(), x, y, z);
          location.getWorld().spawnParticle(particle, location, 1);
        }
      }
    }
  }

  public Location getCenter() {

    int minX = minimum.getBlockX();
    int minY = minimum.getBlockY();
    int minZ = minimum.getBlockZ();
    int x1 = maximum.getBlockX() + 1;
    int y1 = maximum.getBlockY() + 1;
    int z1 = maximum.getBlockZ() + 1;

    return new Location(
        minimum.getWorld(),
        minX + (x1 - minX) / 2.0D,
        minY + (y1 - minY) / 2.0D,
        minZ + (z1 - minZ) / 2.0D);
  }

  @NotNull
  public List<Item> getItemsOnGround() {

    return minimum
        .getWorld()
        .getNearbyEntities(
            getCenter(),
            minimum.distance(maximum) / 2,
            minimum.distance(maximum) / 2,
            minimum.distance(maximum) / 2)
        .stream()
        .filter(entity -> entity instanceof Item)
        .map(entity -> (Item) entity)
        .collect(Collectors.toList());
  }

  @Override
  public Cuboid clone() throws CloneNotSupportedException {
    return ((Cuboid) super.clone());
  }
}
