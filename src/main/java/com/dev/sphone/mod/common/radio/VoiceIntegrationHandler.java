package com.dev.sphone.mod.common.radio;

import com.dev.sphone.api.voicemanager.voicechat.VoiceAddon;
import com.dev.sphone.mod.common.packets.server.HandlerTuneRadio;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.packets.MicrophonePacket;
import net.minecraft.entity.player.EntityPlayerMP;
import java.util.Set;

/**
 * Hooks into simple-voice-chat to cancel radio group audio when muted.
 */
public class VoiceIntegrationHandler {

    public static void registerEvents(EventRegistration registration) {
        registration.registerEvent(MicrophonePacketEvent.class, VoiceIntegrationHandler::onMicPacket, 100);
    }

    private static void onMicPacket(MicrophonePacketEvent event) {
        VoicechatConnection sender = event.getSenderConnection();
        if (sender == null || VoiceAddon.api == null) {
            return;
        }
        if (!(sender.getPlayer().getPlayer() instanceof EntityPlayerMP)) {
            return;
        }

        EntityPlayerMP player = (EntityPlayerMP) sender.getPlayer().getPlayer();
        int freq = HandlerTuneRadio.getPlayerFrequency(player);
        if (freq <= 0) {
            return;
        }
        if (RadioMuteManager.isMuted(player.getUniqueID())) {
            return;
        }

        MicrophonePacket mic = event.getPacket();
        de.maxhenkel.voicechat.api.packets.StaticSoundPacket packet = mic.staticSoundPacketBuilder()
                .opusEncodedData(mic.getOpusEncodedData())
                .build();

        Set<java.util.UUID> members = HandlerTuneRadio.RADIO_GROUPS.get(freq);
        if (members == null) {
            return;
        }
        for (java.util.UUID uuid : members) {
            if (uuid.equals(player.getUniqueID())) {
                continue;
            }
            VoicechatConnection conn = VoiceAddon.api.getConnectionOf(uuid);
            if (conn != null) {
                VoiceAddon.api.sendStaticSoundPacketTo(conn, packet);
            }
        }
    }
}