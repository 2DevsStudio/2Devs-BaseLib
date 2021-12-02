package com.twodevsstudio.devsbaselib.util;

import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("unused")
@EqualsAndHashCode
public class LoreBuilderUtility {

  private List<String> lore = new ArrayList<>();

  public LoreBuilderUtility() {}

  public LoreBuilderUtility(List<String> list) {
    this.lore = list;
  }

  public LoreBuilderUtility(@NotNull LoreBuilderUtility other) {
    this.lore.addAll(other.lore);
  }

  @NotNull
  public LoreBuilderUtility append(@NotNull String line) {
    lore.add(line);
    return this;
  }

  @NotNull
  public <T> LoreBuilderUtility appendIf(
      @NotNull String line, @NotNull T predicateSource, @NotNull Predicate<T> predicate) {
    if (predicate.test(predicateSource)) {
      lore.add(line);
    }
    return this;
  }

  @NotNull
  public LoreBuilderUtility insert(int index, @NotNull String line) {
    lore.add(index, line);
    return this;
  }

  @NotNull
  public <T> LoreBuilderUtility insertIf(
      int index,
      @NotNull String line,
      @NotNull T predicateSource,
      @NotNull Predicate<T> predicate) {
    if (predicate.test(predicateSource)) {
      lore.add(index, line);
    }
    return this;
  }

  @NotNull
  public LoreBuilderUtility remove(int index) {
    lore.remove(index);
    return this;
  }

  @NotNull
  public LoreBuilderUtility remove(@NotNull String line) {
    lore.remove(line);
    return this;
  }

  @NotNull
  public <T> LoreBuilderUtility removeIf(
      int index, @NotNull T predicateSource, @NotNull Predicate<T> predicate) {
    if (predicate.test(predicateSource)) {
      lore.remove(index);
    }
    return this;
  }

  @NotNull
  public <T> LoreBuilderUtility removeIf(
      @NotNull String line, @NotNull T predicateSource, @NotNull Predicate<T> predicate) {
    if (predicate.test(predicateSource)) {
      lore.remove(line);
    }
    return this;
  }

  public LoreBuilderUtility removeLast() {
    int lastIndex = lore.size() - 1;

    if (lastIndex >= 0) {
      lore.remove(lastIndex);
    }
    return this;
  }

  public LoreBuilderUtility replace(@NotNull String toReplace, @NotNull String newValue) {
    lore.replaceAll((val) -> val.equals(toReplace) ? newValue : val);
    return this;
  }

  public LoreBuilderUtility replaceIf(
      @NotNull Predicate<String> predicate, @NotNull String newValue) {
    lore.replaceAll((val) -> predicate.test(val) ? newValue : val);
    return this;
  }

  @NotNull
  public LoreBuilderUtility clear() {
    lore.clear();
    return this;
  }

  @NotNull
  public <T> LoreBuilderUtility clearIf(
      @NotNull T predicateSource, @NotNull Predicate<T> predicate) {
    if (predicate.test(predicateSource)) {
      lore.clear();
    }
    return this;
  }

  @NotNull
  public String get(int index) {
    return lore.get(index);
  }

  public boolean isEmpty() {
    return lore.isEmpty();
  }

  public boolean contains(@NotNull String line) {
    return lore.contains(line);
  }

  public List<String> build() {
    return lore;
  }
}
