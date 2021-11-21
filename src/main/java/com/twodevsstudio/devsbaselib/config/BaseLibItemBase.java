package com.twodevsstudio.devsbaselib.config;

import com.twodevsstudio.devsbaselib.base.AbstractItem;
import com.twodevsstudio.devsbaselib.base.BaseItem;
import com.twodevsstudio.simplejsonconfig.api.Config;
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("FieldMayBeFinal") // simplejsonconfig not supporting finals yet
@Getter
@Configuration("itembase.json")
public class BaseLibItemBase extends Config {

  private List<AbstractItem> items = exampleItems();

  @Nullable
  public AbstractItem getItemById(String id){
    return this.items.stream()
        .filter(abstractItem -> ((BaseItem) abstractItem).getId().equalsIgnoreCase(id))
        .findFirst()
        .orElse(null);
  }

  private List<AbstractItem> exampleItems() {
    List<AbstractItem> baseItems = new ArrayList<>();

    baseItems.add(
        new BaseItem("exampleItem1", Material.ANVIL, 1, "Example Item 1"));
    baseItems.add(
        new BaseItem("exampleItem2", Material.DIRT, 1, "Example Item 2"));
    return baseItems;
  }
}
