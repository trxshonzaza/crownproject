package com.trxsh.crown.listener;

import com.trxsh.crown.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PotionListener implements Listener {

    @EventHandler
    public void onPotionSplash(PotionSplashEvent e) {

        for(LivingEntity en : e.getAffectedEntities()) {

            if(en instanceof Player) {

                if(!PlayerManager.hasCrownedPlayer((Player)en)) {

                    PlayerManager.addPotionedPlayer((Player)en);

                }

            }

        }

    }

    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent e) {

        if(e.getItem().getType() == Material.POTION) {

            Player p = e.getPlayer();

            if(!PlayerManager.hasCrownedPlayer(p)) {

                PlayerManager.addPotionedPlayer(p);

            }

        }

    }

}
