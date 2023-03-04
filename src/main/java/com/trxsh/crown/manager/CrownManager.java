package com.trxsh.crown.manager;

import com.trxsh.crown.handler.CrownHandler;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class CrownManager {

    public void check(Player p) {

        PlayerInventory inv = p.getInventory();

        ItemStack helm = inv.getHelmet();

        if(PlayerManager.hasCrownedPlayer(p)) {

            EffectManager.grantEffects(Arrays.asList(new PotionEffect[] {
                  new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 2),
                  new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 2),
                  new PotionEffect(PotionEffectType.SPEED, 200, 1),
            }),p);

        } else if(!PlayerManager.hasPotionedPlayer(p)) {

            EffectManager.removeEffects(Arrays.asList(new PotionEffectType[] {
                    PotionEffectType.INCREASE_DAMAGE,
                    PotionEffectType.DAMAGE_RESISTANCE,
                    PotionEffectType.SPEED,
            }),p);

        }

        //TODO: use this if needed in the future
        /*if(PlayerManager.hasPotionedPlayer(p)) {

            //Bukkit.broadcastMessage(p.getName() + " is now a potioned player");

        }*/

        if(helm == null) {

            if(PlayerManager.hasCrownedPlayer(p)) {

                PlayerManager.removeCrownedPlayer(p);

            }

            return;

        }

        try {

            if(helm.isSimilar(CrownHandler.crownStack)) {

                if(!PlayerManager.hasCrownedPlayer(p)) {

                    PlayerManager.addCrownedPlayer(p);
                    return;

                }

            } else {

                if(PlayerManager.hasCrownedPlayer(p)) {

                    PlayerManager.removeCrownedPlayer(p);
                    return;

                }

            }

        }catch(Exception e) {



        }

        if(p.getActivePotionEffects().size() == 0 && PlayerManager.hasPotionedPlayer(p)) {

            PlayerManager.removePotionedPlayer(p);
            return;

        }

        if(PlayerManager.hasPotionedPlayer(p) && PlayerManager.hasCrownedPlayer(p)) {

            PlayerManager.removePotionedPlayer(p);
            PlayerManager.removeCrownedPlayer(p);
            return;

        }

    }

}
