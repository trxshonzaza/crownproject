package com.trxsh.crown.data;

import com.trxsh.crown.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerProfile;

import java.util.UUID;

public class PlayerData {

    public String name = "";

    public UUID id = null;

    public Player player = null;

    public PlayerData(String name, UUID uuid, Player correspondingPlayer) {

        this.name = name;
        this.id = uuid;
        this.player = correspondingPlayer;

    }

    public static void addPlayerData(UUID id) {

        Player p = Bukkit.getPlayer(id);

        if(p == null) {

            OfflinePlayer offline = Bukkit.getOfflinePlayer(id);

            Main.instance.data.add(new PlayerData(offline.getName(), offline.getUniqueId(), offline.getPlayer()));

            return;

        }

        Main.instance.data.add(new PlayerData(p.getName(), p.getUniqueId(), p));

    }

    public String getName() {

        return name;

    }

    public UUID getID() {

        return id;

    }

    public Player getPlayer() {

        return player;

    }

    public static Player getPlayer(UUID id) {

        for(PlayerData data : Main.instance.data) {

            if(data.id.equals(id)) {

                return data.getPlayer();

            }

        }

        return null;

    }

    public static PlayerData getPlayerData(UUID id) {

        for(PlayerData data : Main.instance.data) {

            if(data.id.equals(id)) {

                return data;

            }

        }

        return null;

    }

}
