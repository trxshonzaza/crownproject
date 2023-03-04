package com.trxsh.crown.listener;

import com.trxsh.crown.handler.CrownHandler;
import com.trxsh.crown.manager.LivesManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Warden
                || e.getEntity() instanceof EnderDragon
                || e.getEntity() instanceof Wither) {

            if (e.getEntity().getKiller() instanceof Player) {
                CrownHandler.dropCrown(e.getEntity().getLocation().add(0,.5D, 0), e.getEntity().getKiller());
            }

        }

        if(e.getEntity() instanceof Player) {

            LivesManager.instance.removeLife((Player)e.getEntity());
            e.getEntity().sendMessage("You lost a life! "  + ChatColor.RED + ChatColor.BOLD + "be careful...");

        }
    }

}
