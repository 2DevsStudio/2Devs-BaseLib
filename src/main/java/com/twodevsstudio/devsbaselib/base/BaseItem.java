package com.twodevsstudio.devsbaselib.base;

import com.twodevsstudio.devsbaselib.util.TextUtility;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

@AllArgsConstructor
@Data
public class BaseItem implements AbstractItem {

  private String id;
  private String displayName;

  private Material material = Material.DIRT;
  private int amount = 1;

  private Map<String, String> nbtTags = new HashMap<>();

  private List<String> lore = new ArrayList<>();
  private List<ItemFlag> flags = new ArrayList<>(Arrays.asList(ItemFlag.values().clone()));

  public BaseItem(String id, Material material, int amount) {
    this.id = id;
    this.material = material;
    this.amount = amount;
  }

  public BaseItem(String id, Material material, int amount, String displayName) {
    this.id = id;
    this.material = material;
    this.amount = amount;
    this.displayName = displayName;
  }

  public ItemStack give(Player player) {
    ItemStack itemStack = getItemStack();
    player.getInventory().addItem(itemStack);

    return itemStack;
  }

  public ItemStack getItemStack() {
    ItemStack defaultItemStack = new ItemStack(Material.AIR);

    if (this.material == Material.AIR) {
      return defaultItemStack;
    }
    ItemStack itemStack = new ItemStack(this.material);
    ItemMeta itemMeta = itemStack.getItemMeta();

    if (itemMeta == null) {
      return defaultItemStack;
    }
    if (this.displayName != null) {
      itemMeta.setDisplayName(TextUtility.colorize(this.displayName));
    }
    if (this.lore != null) {
      itemMeta.setLore(TextUtility.colorize(this.lore));
    }
    if (this.flags != null) {
      itemMeta.addItemFlags(this.flags.toArray(new ItemFlag[0]));
    }
    itemStack.setAmount(this.amount);
    itemStack.setItemMeta(itemMeta);

    NBTItem nbtItem = new NBTItem(itemStack);

    nbtItem.setString("ID", this.id);

    return nbtItem.getItem();
  }
}
