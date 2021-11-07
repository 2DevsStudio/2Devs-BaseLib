package com.twodevsstudio.devsbaselib.base;

import com.twodevsstudio.devsbaselib.util.TextUtility;
import de.tr7zw.changeme.nbtapi.NBTItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@Data
@Builder
public class BaseItem {

  private String id;
  private String displayName;

  @Builder.Default private Material material = Material.DIRT;
  @Builder.Default private int amount = 1;
  @Builder.Default private List<String> lore = new ArrayList<>();
  @Builder.Default private Map<String, String> nbtTags = new HashMap<>();

  @Builder.Default
  private List<ItemFlag> flags = new ArrayList<>(Arrays.asList(ItemFlag.values().clone()));

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
    itemMeta.setDisplayName(TextUtility.colorize(this.displayName));
    itemMeta.setLore(TextUtility.colorize(this.lore));
    itemMeta.addItemFlags(this.flags.toArray(new ItemFlag[0]));
    itemStack.setAmount(this.amount);
    itemStack.setItemMeta(itemMeta);

    NBTItem nbtItem = new NBTItem(itemStack);

    nbtItem.setString("ID", this.id);

    return nbtItem.getItem();
  }
}
