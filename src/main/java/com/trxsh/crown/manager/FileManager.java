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

    public static File lives = new File("lives.mgr");
    public static File deaths = new File("deaths.mgr");
    public static File crowns = new File("crowns.mgr");
    public static File data = new File("data.mgr");
    public static String separator = System.getProperty("line.separator");
    public static Map<UUID, Integer> livesFileList = new HashMap<UUID, Integer>();


    public static void writeLives() {

        try {

            FileWriter writer = new FileWriter(lives);
            StringBuilder sb = new StringBuilder();

            for(UUID id : Main.lifeManager.playerLives.keySet()) {

                int lives = Main.lifeManager.getPlayerLives(id);

                sb.append(id + "~" + lives + separator);

            }

            writer.write(sb.toString());
            writer.close();

        }catch(IOException e) { }

    }

    public static Map<UUID, Integer> readLives() {

        Map<UUID, Integer> toReplace = new HashMap<UUID, Integer>();

        try {

            for(String str : Files.readAllLines(lives.toPath())) {

                UUID uuid = UUID.fromString(str.split("~")[0]);
                int lives = Integer.parseInt(str.split("~")[1]);

                toReplace.put(uuid, lives);

            }

            livesFileList = toReplace;

        }catch(IOException e) { }

        return toReplace;

    }

    public static void writeCrowns() {

        try {

            FileWriter writer = new FileWriter(crowns);

            writer.write(Integer.toString(CrownHandler.droppedCrowns));

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
            StringBuilder sb = new StringBuilder();

            for(OfflinePlayer p : DeathManager.deadPlayers.keySet()) {

                sb.append(p.getUniqueId() + separator);

            }

            writer.write(sb.toString());
            writer.close();

        }catch(IOException e) { }

    }

    public static void readDeaths() {

        try {

            for(String str : Files.readAllLines(deaths.toPath())) {

                UUID uuid = UUID.fromString(str);

                DeathManager.deadPlayers.put(Bukkit.getOfflinePlayer(uuid), PlayerData.getPlayerData(uuid));

            }

        }catch(IOException e) { }

    }

    public static void writePlayerData() {

        try {

            FileWriter writer = new FileWriter(data);
            StringBuilder sb = new StringBuilder();

            for(PlayerData data : Main.instance.data) {

                sb.append(data.getID() + separator);

            }

            writer.write(sb.toString());
            writer.close();

        }catch(IOException e) { }

    }

    public static void readPlayerData() {

        try {

            for(String str : Files.readAllLines(data.toPath())) {

                UUID id = UUID.fromString(str);

                Main.instance.data.clear();
                PlayerData.addPlayerData(id);

            }

        }catch(IOException e) { }

    }

}
