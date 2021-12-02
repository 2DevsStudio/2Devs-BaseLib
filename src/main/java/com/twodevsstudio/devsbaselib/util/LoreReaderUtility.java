package com.twodevsstudio.devsbaselib.util;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public record LoreReaderUtility(List<String> lore) {

  public LoreReaderUtility(@NotNull List<String> lore) {
    this.lore = TextUtility.removeColor(lore);
  }

  public List<String> getAsStringList() {
    return TextUtility.colorize(this.lore);
  }

  public String get(int index) {
    return lore.size() > index ? lore.get(index) : null;
  }

  public List<String> getMatching(@NotNull Predicate<String> predicate) {
    return getAsStringList().parallelStream().filter(predicate).collect(Collectors.toList());
  }

  public List<String> getAllValues(@NotNull String key) {
    Predicate<String> containsKey = line -> line.startsWith(key);
    Function<String, String> getValue = line -> line.replace(key, "");
    return getAsStringList().parallelStream().filter(containsKey).map(getValue)
        .collect(Collectors.toList());
  }

  public String getFirstValue(@NotNull String key) {
    Predicate<String> containsKey = line -> line.startsWith(key);
    Function<String, String> getValue = line -> line.replace(key, "");
    return getAsStringList().parallelStream().filter(containsKey).map(getValue).findFirst()
        .orElse(null);
  }

  public double getDouble(@NotNull String key) {
    List<String> values = getAllValues(key);
    String value = values.get(0);
    return Double.parseDouble(value);
  }

  public double getInt(@NotNull String key) {
    List<String> values = getAllValues(key);
    String value = values.get(0);
    return Integer.parseInt(value);
  }

}
