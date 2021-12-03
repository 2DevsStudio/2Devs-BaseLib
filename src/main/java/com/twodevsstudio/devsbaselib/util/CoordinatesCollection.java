package com.twodevsstudio.devsbaselib.util;

import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class is used to make memory usage less of locations, Cause world name is duplicated in most
 * of the locations that a normal set would have, We just have one field worldName here and having
 * location as an array
 */
public record CoordinatesCollection<T extends Collection<int[]>>(@Getter String worldName,
                                                                 T coordinates) implements Iterable<int[]> {

  public void add(@NonNull Location location) {
    this.coordinates.add(
        new int[]{
            location.getBlockX(),
            location.getBlockY(),
            location.getBlockZ()
        }
    );
  }

  public void add(int[] coord) {
    this.coordinates.add(coord);
  }

  public boolean remove(int[] key) {
    return this.coordinates.remove(key);
  }

  public boolean contains(int[] key) {
    return this.coordinates.contains(key);
  }

  public Set<Location> asLocationSet() {
    Set<Location> locations = new HashSet<>();
    World world = Bukkit.getWorld(this.worldName);

    if (world == null) {
      throw new IllegalStateException(
          String.format("World by name %s does not exist!", this.worldName));
    }
    for (int[] coordinate : this.coordinates) {
      locations.add(new Location(
          world,
          coordinate[0],
          coordinate[1],
          coordinate[2]
      ));
    }
    return locations;
  }

  @NotNull
  @Override
  public Iterator<int[]> iterator() {
    return this.coordinates.iterator();
  }

  public void add(@NonNull Vector vector) {
    this.add(new int[]{vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()});
  }
}
