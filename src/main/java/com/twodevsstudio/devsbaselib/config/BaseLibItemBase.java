package com.twodevsstudio.devsbaselib.config;

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

  private List<BaseItem> items = exampleItems();

  @Nullable
  public BaseItem getItemById(String id){
    return this.items.stream().filter(baseItem -> baseItem.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
  }

  private List<BaseItem> exampleItems() {
    List<BaseItem> baseItems = new ArrayList<>();

    baseItems.add(
        BaseItem.builder()
            .id("exampleItem1")
            .displayName("Example Item 1")
            .amount(3)
            .material(Material.ANVIL)
            .build());

    baseItems.add(
        BaseItem.builder()
            .id("exampleItem2")
            .displayName("Example Item 2")
            .amount(5)
            .material(Material.NETHER_PORTAL)
            .build());
    return baseItems;
  }
}
