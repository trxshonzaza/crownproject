package com.trxsh.crown.listener;

import com.trxsh.crown.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        Main.manager.check(e.getPlayer());

    }

}
