package com.yj0524;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class subCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.isOp()) {
                p.sendMessage("§aTest Complete.");
            }
            else {
                p.sendMessage("§cYou do not have Permission.");
            }
        }

        return false;
    }
}
