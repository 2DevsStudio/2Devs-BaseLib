package com.twodevsstudio.devsbaselib.util;

import com.google.common.base.Strings;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@UtilityClass
public class TextUtility {

  public String colorize(String toColor) {
    return ChatColor.translateAlternateColorCodes('&', toColor);
  }

  public List<String> colorize(List<String> toColor) {
    return toColor.stream().map(TextUtility::colorize).collect(Collectors.toList());
  }

  public String removeColor(String name) {
    for (String color :
        Arrays.stream(ChatColor.values())
            .map(chatColor -> "&" + chatColor.getChar())
            .collect(Collectors.toList())) {
      name = name.replaceAll(color, "");
      name = name.replaceAll(colorize(color), "");
    }
    return name;
  }

  public List<String> removeColor(List<String> lines) {
    return lines.stream().map(TextUtility::removeColor).collect(Collectors.toList());
  }

  public String replace(String text, Placeholder... placeholders) {
    for (Placeholder placeholder : placeholders) {
      text = placeholder.replaceIn(text);
    }
    return text;
  }

  public String replace(String text, List<Placeholder> placeholders) {
    for (Placeholder placeholder : placeholders) {
      text = placeholder.replaceIn(text);
    }
    return text;
  }

  public List<String> replace(List<String> text, Placeholder... placeholders) {
    for (Placeholder placeholder : placeholders) {
      text = placeholder.replaceIn(text);
    }
    return text;
  }

  public String getEnumFriendlyName(Enum<?> anEnum) {
    return WordUtils.capitalizeFully(anEnum.name().replace("_", " "));
  }

  /**
   * @return parsed integers from string
   * @apiNote wrap all integers from string as array
   */
  public int[] parseStringIntegers(String string) {
    string = ChatColor.stripColor(string);

    String[] split = string.split(" ");
    int[] ints = new int[10];
    int index = 0;

    for (String value : split) {
      if (value.isEmpty()) {
        continue;
      }
      if (StringUtils.isNumeric(value)) {
        ints[index] = Integer.parseInt(value);
        index = index + 1;
      }
    }
    return ints;
  }

  /**
   * Puts color char before every character in given string. Simply makes this string invisible
   *
   * @param line to apply invisibility
   * @return Invisible line
   * @see #visible(String)
   */
  @NotNull
  public String invisible(@NotNull String line) {
    StringBuilder invisibleString = new StringBuilder();

    for (char c : line.toCharArray()) {
      invisibleString.append(colorize("&")).append(c);
    }
    return invisibleString.toString();
  }

  /**
   * Removes all color chars from string to make every character visible. It simply reverts action
   * made by {@code invisible(String)} method
   *
   * @param line to apply visibility
   * @return Visible string
   * @see #invisible(String)
   */
  @NotNull
  public String visible(@NotNull String line) {
    return line.replaceAll(colorize("&"), "");
  }

  public String getProgressBar(
      int current,
      int max,
      int totalBars,
      char symbol,
      ChatColor completedColor,
      ChatColor notCompletedColor) {
    float percent = (float) current / max;
    int progressBars = (int) (totalBars * percent);

    return Strings.repeat("" + completedColor + symbol, progressBars)
        + Strings.repeat("" + notCompletedColor + symbol, totalBars - progressBars);
  }
}
