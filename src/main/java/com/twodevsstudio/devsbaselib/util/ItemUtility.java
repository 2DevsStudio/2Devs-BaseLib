package com.twodevsstudio.devsbaselib.util;

import lombok.experimental.UtilityClass;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
@UtilityClass
public class ItemUtility {

  /** @implNote check if item has ItemMeta & displayname & lore */
  public boolean isValid(ItemStack itemStack) {
    if (itemStack != null && itemStack.hasItemMeta()) {
      ItemMeta itemMeta = itemStack.getItemMeta();
      return itemMeta.hasDisplayName() && itemMeta.hasLore();
    }
    return false;
  }

  /** @implNote adding all flags for itemstack */
  public ItemStack normalizeItemStack(ItemStack itemStack) {
    ItemMeta itemMeta = itemStack.getItemMeta();

    itemMeta.addItemFlags(ItemFlag.values());
    itemStack.setItemMeta(itemMeta);

    return itemStack;
  }

  public List<Enchantment> getCompatibleEnchantments(ItemStack itemStack) {
    List<Enchantment> compatible = new ArrayList<>();

    Arrays.stream(Enchantment.values())
        .forEach(
            enchantment -> {
              if (enchantment.getItemTarget().includes(itemStack)) {
                compatible.add(enchantment);
              }
            });

    return compatible;
  }
}
