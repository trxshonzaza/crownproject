package com.trxsh.crown.manager;

import com.trxsh.crown.Main;
import com.trxsh.crown.data.PlayerData;
import com.trxsh.crown.handler.CrownHandler;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerProfile;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.util.*;

public class FileManager {

    public static File lives = new File("plugins/TrxshProd/config/lives.mgr");
    public static File deaths = new File("plugins/TrxshProd/config/deaths.mgr");
    public static File crowns = new File("plugins/TrxshProd/config/crowns.mgr");
    public static File data = new File("plugins/TrxshProd/config/data.mgr");
    public static String separator = System.getProperty("line.separator");

    public static void createConfigDirectories() {

        File file = new File("plugins/TrxshProd/config");

        if(file.exists()) {

            file.mkdirs();

        }

    }

    public static void writeLives() {

        try {

            FileWriter writer = new FileWriter(lives);

            for(Player p : LivesManager.playerLives.keySet()) {

                int lives = LivesManager.playerLives.get(p);

                writer.write(p.getUniqueId().toString() + "~" + lives + separator);

            }

            writer.close();

        }catch(IOException e) { }

    }

    public static void readLives() {

        try {

            FileReader reader = new FileReader(lives);

            Map<Player, Integer> toReplace = new HashMap<Player, Integer>();

            for(String str : Files.readAllLines(lives.toPath(), StandardCharsets.UTF_8)) {

                UUID uuid = UUID.fromString(str.split("~")[0]);
                int lives = Integer.parseInt(str.split("~")[1]);

                Player player = Bukkit.getPlayer(uuid);

                toReplace.put(player, lives);

            }

            reader.close();

            LivesManager.playerLives = toReplace;

        }catch(IOException e) { }

    }

    public static void writeCrowns() {

        try {

            FileWriter writer = new FileWriter(crowns);

            writer.write(CrownHandler.droppedCrowns);

            writer.close();

        }catch(IOException e) { }

    }

    public static void readCrowns() {

        try {

            Scanner scanner = new Scanner(crowns.toPath());

            if(scanner.hasNextInt()) {

                CrownHandler.droppedCrowns = scanner.nextInt();

            }

            scanner.close();

        }catch(IOException e) { }

    }

    public static void writeDeaths() {

        try {

            FileWriter writer = new FileWriter(deaths);

            for(OfflinePlayer p : DeathManager.deadPlayers.keySet()) {

                writer.write(p.getUniqueId() + separator);

            }

            writer.close();

        }catch(IOException e) { }

    }

    public static void readDeaths() {

        try {

            FileReader reader = new FileReader(deaths);

            Map<OfflinePlayer, PlayerData> toReplace = new HashMap<OfflinePlayer, PlayerData>();

            for(String str : Files.readAllLines(deaths.toPath(), StandardCharsets.UTF_8)) {

                UUID uuid = UUID.fromString(str);

                toReplace.put(Bukkit.getOfflinePlayer(uuid), PlayerData.getPlayerData(uuid));

            }

            DeathManager.deadPlayers = toReplace;

            reader.close();

        }catch(IOException e) { }

    }

    public static void writePlayerData() {

        try {


            FileWriter writer = new FileWriter(data);

            for(PlayerData data : Main.instance.data) {

                writer.write(data.getID() + separator);

            }

            writer.close();

        }catch(IOException e) { }

    }

    public static void readPlayerData() {

        try {

            FileReader reader = new FileReader(data);

            for(String str : Files.readAllLines(data.toPath(), StandardCharsets.UTF_8)) {

                UUID id = UUID.fromString(str);

                Main.instance.data.clear();
                PlayerData.addPlayerData(id);

            }

            reader.close();

        }catch(IOException e) { }

    }



}
