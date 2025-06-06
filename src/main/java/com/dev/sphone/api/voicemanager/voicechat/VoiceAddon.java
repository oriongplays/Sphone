package com.dev.sphone.api.voicemanager.voicechat;

import com.dev.sphone.SPhone;
import com.dev.sphone.mod.common.packets.client.PacketOpenPhone;
import de.maxhenkel.voicechat.Voicechat;
import de.maxhenkel.voicechat.api.*;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.VoicechatServerStartedEvent;
import de.maxhenkel.voicechat.net.NetManager;
import de.maxhenkel.voicechat.net.RemoveGroupPacket;
import de.maxhenkel.voicechat.voice.common.PlayerState;
import de.maxhenkel.voicechat.voice.server.PlayerStateManager;
import de.maxhenkel.voicechat.voice.server.Server;
import de.maxhenkel.voicechat.voice.server.ServerGroupManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

@ForgeVoicechatPlugin
public class VoiceAddon implements VoicechatPlugin {

    public static VoicechatServerApi api;
    private static final Map<String, Group> GroupMap = new HashMap<>();

    @Override
    public String getPluginId() {
        return "VoiceAddon";
    }

    @Override
    public void initialize(VoicechatApi api) {
        System.out.println("SPhone VoiceChat Addon Initialized");
    }

    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(VoicechatServerStartedEvent.class, this::onServerStarted, 100);
    }

    public void onServerStarted(VoicechatServerStartedEvent e) {
        api = e.getVoicechat();
        
        if (api == null) {
    System.out.println("VoiceAddon.api is NULL - Cannot create group!");
    return;
}
    }
    

    public static void createGroup(String name, Boolean persistent, Group.Type type) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            password.append((char) (Math.random() * 26 + 97));
        }

        Group g = api.groupBuilder()
                .setName(name)
                .setPersistent(persistent)
                .setType(type)
                .setPassword(password.toString())
                .build();
        GroupMap.put(name, g);
    }

    public static void addToGroup(String name, EntityPlayer player) {
        if (GroupMap.containsKey(name)) {
            VoicechatConnection connection = api.getConnectionOf(player.getUniqueID());
            if (connection != null) {
                Group g = GroupMap.get(name);
                connection.setGroup(g);
            }
        }
    }

    public static void removeFromActualGroup(EntityPlayer player) {
        VoicechatConnection connection = api.getConnectionOf(player.getUniqueID());
        if (connection != null) {
            String groupName = getGroup(player);
            if (groupName != null) {
                // Remove apenas o próprio player do grupo
                connection.setGroup(null);

                // Agora verifica se ficou vazio!
                List<EntityPlayer> players = getPlayersInGroup(groupName);
                if (players.isEmpty()) {
                    // NINGUÉM no grupo, pode deletar
                    Group group = GroupMap.get(groupName);

                    removeGroup(groupName);

                    if (group != null) {
                        Server server = Voicechat.SERVER.getServer();
                        if (server != null) {
                            ServerGroupManager groupManager = server.getGroupManager();
                            groupManager.removeGroup(group.getId());
                            broadcastRemoveGroup(server, group.getId());
                        }
                        api.removeGroup(group.getId());
                    }
                }
            }
        }
    }

    // NOVO: Conta quantos players ainda estão no grupo
    public static List<EntityPlayer> getPlayersInGroup(String groupName) {
        List<EntityPlayer> players = new ArrayList<>();
        if (api == null) return players;

        Group group = GroupMap.get(groupName);
        if (group == null) return players;

        Server server = Voicechat.SERVER.getServer();
        if (server == null) return players;
        PlayerStateManager manager = server.getPlayerStateManager();

        for (PlayerState state : manager.getStates()) {
            if (state.hasGroup() && state.getGroup().equals(group.getId())) {
                VoicechatConnection conn = api.getConnectionOf(state.getUuid());
                if (conn != null && conn.getPlayer() != null && conn.getPlayer().getPlayer() instanceof EntityPlayer) {
                    players.add((EntityPlayer) conn.getPlayer().getPlayer());
                }
            }
        }
        return players;
    }

    private static void broadcastRemoveGroup(Server server, UUID group) {
        RemoveGroupPacket packet = new RemoveGroupPacket(group);
        server.getServer().getPlayerList().getPlayers().forEach(p -> NetManager.sendToClient(p, packet));
    }

    public static String getGroup(EntityPlayer player) {
        VoicechatConnection connection = api.getConnectionOf(player.getUniqueID());
        if (connection == null) {
            return null;
        } else {
            if (connection.getGroup() == null) {
                return null;
            }
            return connection.getGroup().getName();
        }
    }

    public static void removeGroup(String name) {
        GroupMap.remove(name);
    }

    public static boolean groupExists(String name) {
        return GroupMap.containsKey(name);
    }

    public static boolean isInGroup(EntityPlayer player) {
        VoicechatConnection connection = api.getConnectionOf(player.getUniqueID());
        if (connection == null) {
            return false;
        } else {
            return connection.getGroup() != null;
        }
    }
}
