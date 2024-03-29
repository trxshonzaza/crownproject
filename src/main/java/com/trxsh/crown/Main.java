package com.trxsh.crown;

import com.trxsh.crown.crafting.RecipeManager;
import com.trxsh.crown.data.PlayerData;
import com.trxsh.crown.executor.LifeCheckExecutor;
import com.trxsh.crown.executor.LifeWithdrawExecutor;
import com.trxsh.crown.executor.ListExecutor;
import com.trxsh.crown.executor.TexturesExecutor;
import com.trxsh.crown.handler.CrownHandler;
import com.trxsh.crown.listener.*;
import com.trxsh.crown.manager.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    public static Main instance = null;
    public static CrownManager manager = null;
    public static LivesManager lifeManager = null;

    public static String url = "https://www.dropbox.com/s/m1ordkty7rd9yai/golden-crowns.zip?dl=1";
    public static String optional = "https://www.dropbox.com/s/m51bzz6uuwevg68/vanilla%20tweaks%20%2B%20crown%20textures.zip?dl=1";

    public static List<PlayerData> data = new ArrayList<PlayerData>();

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Crown] enabling crown plugin");

        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new PotionListener(), this);
        Bukkit.getPluginManager().registerEvents(new MovementListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new ItemUseListener(), this);
        Bukkit.getPluginManager().registerEvents(new ResourcePackListener(), this);

        Bukkit.getPluginManager().registerEvents(new InventoryManager(), this);

        Bukkit.getPluginCommand("lists").setExecutor(new ListExecutor());
        Bukkit.getPluginCommand("lives").setExecutor(new LifeCheckExecutor());
        Bukkit.getPluginCommand("withdraw").setExecutor(new LifeWithdrawExecutor());
        Bukkit.getPluginCommand("textures").setExecutor(new TexturesExecutor());

        /* early init crown handler stack */
        CrownHandler.getCrownStack();

        for(Player p : Bukkit.getOnlinePlayers()) {

            PlayerData.addPlayerData(p.getUniqueId());

        }

        instance = this;
        manager = new CrownManager();
        lifeManager = new LivesManager();

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Crown] loading data");


        if(FileManager.data.exists()) {

            FileManager.readPlayerData();

        }

        if(FileManager.crowns.exists()) {

            FileManager.readCrowns();

        }

        if(FileManager.deaths.exists()) {

            FileManager.readDeaths();

        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Crown] loaded data!");

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Crown] adding recipes");

        RecipeManager.registerRecipes();

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Crown] added recipes!");

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[Crown] enabled crown plugin!");

    }

    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Crown] disabling crown plugin");

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Crown] saving data");

        FileManager.writePlayerData();
        FileManager.writeCrowns();
        FileManager.writeLives();
        FileManager.writeDeaths();

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Crown] saved data!");

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Crown] disabled crown plugin!");

    }
}
