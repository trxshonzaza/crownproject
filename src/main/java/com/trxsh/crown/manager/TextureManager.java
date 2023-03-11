package com.trxsh.crown.manager;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.trxsh.crown.Main;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class TextureManager {

    public static void loadTexture(Player p) throws InvocationTargetException {

        PacketContainer packet = new ProtocolLibrary().getProtocolManager().createPacket(PacketType.Play.Server.RESOURCE_PACK_SEND);

        packet.getStrings().write(0, Main.url).write(1, "sha-1 sum");

        ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);

    }

    public static void loadOptionalTexture(Player p) throws InvocationTargetException {

        PacketContainer packet = new ProtocolLibrary().getProtocolManager().createPacket(PacketType.Play.Server.RESOURCE_PACK_SEND);

        packet.getStrings().write(0, Main.optional).write(1, "sha-1 sum");

        ProtocolLibrary.getProtocolManager().sendServerPacket(p, packet);

    }

}
