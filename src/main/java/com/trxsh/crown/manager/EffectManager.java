package com.trxsh.crown.manager;

import com.trxsh.crown.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class EffectManager {

    public static boolean grantEffects(List<PotionEffect> effects, Player player) {

        Bukkit.getScheduler().runTask(Main.instance, new Runnable() {
            @Override
            public void run() {
                for(PotionEffect e : effects) {
                    player.addPotionEffect(e);
                }
            }
        });

        return true;

    }

    public static boolean removeEffects(List<PotionEffectType> effects, Player player) {

        Bukkit.getScheduler().runTask(Main.instance, new Runnable() {
            @Override
            public void run() {
                for(PotionEffectType e : effects) {
                    player.removePotionEffect(e);
                }
            }
        });

        return true;

    }

    public static boolean removeAllEffects(Player player) {

        Bukkit.getScheduler().runTask(Main.instance, new Runnable() {
            @Override
            public void run() {
                for(PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }
            }
        });

        return true;

    }

    public static List<PotionEffect> getPlayerPotionEffects(Player player) {
        return (List<PotionEffect>) player.getActivePotionEffects().stream();
    }

}
