package com.twodevsstudio.devsbaselib.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@UtilityClass
public class NbtUtility {

  @SneakyThrows
  public Map<String, String> readAllTags(ItemStack itemStack) {
    Map<String, String> tags = new HashMap<>();

    net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
    NBTTagCompound nbtTagCompound = nmsItem.getOrCreateTag();
    Field nbtTagCompoundMapField = nbtTagCompound.getClass().getDeclaredField("x");
    Map<String, NBTBase> map = (Map<String, NBTBase>) nbtTagCompoundMapField.get(nbtTagCompound);

    for (Map.Entry<String, NBTBase> entry : map.entrySet()) {
      tags.put(entry.getKey(), entry.getValue().asString());
    }
    return tags;
  }

  @Nullable
  public String readTag(ItemStack itemStack, String tag) {
    return readAllTags(itemStack).get(tag);
  }
}
