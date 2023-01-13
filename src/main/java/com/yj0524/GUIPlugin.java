package com.yj0524;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class GUIPlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("testadminonly").setExecutor(new subCommand());
        Bukkit.getPluginManager().registerEvents(this, this);
        System.out.println("[GUIPlugin] 플러그인이 활성화되었습니다.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[GUIPlugin] 플러그인이 비활성화되었습니다.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("menu" )) {
            // Custom Item Code
            ItemStack spawnItem = new ItemStack(Material.GRASS);
            ItemMeta spawnItemMeta = spawnItem.getItemMeta();

            spawnItemMeta.setDisplayName("§r§a스폰으로 이동하기");
            spawnItemMeta.setLore(Arrays.asList("§r스폰으로 이동합니다."));
            spawnItem.setItemMeta(spawnItemMeta);

            // GUI Code
            Player p = (Player) sender;
            Inventory inv = Bukkit.createInventory(null, 27, "Menu");
            // Inventory
            // [0 ][1 ][2 ][3 ][4 ][5 ][6 ][7 ][8 ]
            // [9 ][10][11][12][13][14][15][16][17]
            // [18][19][20][21][22][23][24][25][26]
            inv.setItem(13, new ItemStack(spawnItem));

            p.openInventory(inv);
        }

        return false;
    }

    @EventHandler
    public void InventoryCreateEvent(InventoryClickEvent e) {
        // GUI Code
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getClickedInventory();

        // if (!e.getClickedInventory().getType().name().equals("Menu")) return;

        // Grass Click
        if (e.getCurrentItem().getType() == Material.GRASS) {
            e.setCancelled(true);
            p.teleport(Bukkit.getWorld("world").getSpawnLocation());
            p.sendMessage("스폰으로 정상적으로 이동되었습니다.");
            System.out.println(p.getName() + "이(가) /menu 명령어를 사용하여 스폰으로 이동하였습니다.");
            p.closeInventory();
        }
    }
}
