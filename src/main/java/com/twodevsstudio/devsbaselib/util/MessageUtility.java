package com.twodevsstudio.devsbaselib.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;

@SuppressWarnings("unused")
@UtilityClass
public class MessageUtility {

  public void send(CommandSender target, String text, Placeholder... placeholders) {
    text = TextUtility.replace(text, placeholders);
    target.sendMessage(TextUtility.colorize(text));
  }

  public void send(CommandSender target, List<String> text, Placeholder... placeholders) {
    text.forEach(message -> send(target, message, placeholders));
  }

  public void sendConsole(String text) {
    send(Bukkit.getConsoleSender(), text);
  }

  public void sendConsole(List<String> text) {
    send(Bukkit.getConsoleSender(), text);
  }
}
