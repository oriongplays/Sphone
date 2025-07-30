package com.dev.sphone.mod.common.packets.server.radio;

import com.dev.sphone.mod.common.radio.RadioMuteManager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Packet sent from the client to toggle radio mute state.
 */
public class PacketRadioMuteToggle implements IMessage {

    private boolean muted;

    public PacketRadioMuteToggle() {}

    public PacketRadioMuteToggle(boolean muted) {
        this.muted = muted;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.muted = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.muted);
    }

    public static class Handler implements IMessageHandler<PacketRadioMuteToggle, IMessage> {
        @Override
        @SideOnly(Side.SERVER)
        public IMessage onMessage(PacketRadioMuteToggle message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServerWorld().addScheduledTask(() ->
                RadioMuteManager.setMuted(player.getUniqueID(), message.muted)
            );
            return null;
        }
    }
}