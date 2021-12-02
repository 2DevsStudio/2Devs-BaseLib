package com.twodevsstudio.devsbaselib.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
@UtilityClass
public class InventoryUtility {

  /** @implNote check if target inventory contains specified itemstack */
  public boolean inventoryContainsItemStack(Inventory inventory, ItemStack itemStack) {
    for (ItemStack content : inventory.getContents()) {
      if (content == null) {
        continue;
      }
      if (content.isSimilar(itemStack)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasPlace(int amount, Inventory inventory) {
    int slot = 1;

    for (ItemStack itemStack : inventory.getStorageContents()) {
      if (itemStack.getType() != Material.AIR) {
        slot++;
      }
    }
    double slotNeed = (double) amount / 64;
    int inventorySpace = (int) Math.ceil(slotNeed);
    return slot >= inventorySpace;
  }

  public int getFreeSlots(Inventory inventory) {
    int count = 0;

    for (int i = 0; i < inventory.getSize(); i++) {
      if (inventory.getItem(i).getType().isAir()) {
        count++;
      }
    }
    return count;
  }

  public boolean compareInventories(InventoryView firstView, InventoryView secondView) {
    return compareInventories(firstView.getTitle(), secondView.getTitle());
  }

  public boolean compareInventories(String firstString, String secondString) {
    String firstViewTitle = TextUtility.removeColor(firstString);
    String secondViewTitle = TextUtility.removeColor(secondString);

    return firstViewTitle.equalsIgnoreCase(secondViewTitle);
  }
}
