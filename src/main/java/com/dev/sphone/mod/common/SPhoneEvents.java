package com.dev.sphone.mod.common;

import com.dev.sphone.mod.common.register.ItemsRegister;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import com.dev.sphone.api.voicemanager.voicechat.VoiceAddon;

public class SPhoneEvents {

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.world.isRemote) return; // Só servidor
        EntityPlayer player = event.player;

        // Agora está correto! Pegando o nome do grupo do player
        String groupName = VoiceAddon.getGroup(player);

        if (groupName != null && !groupName.isEmpty()) {
            if (groupName.startsWith("radio_")) {
                // Para grupo radio: checa rádio na hotbar
                boolean hasRadioInHotbar = false;
                for (int i = 0; i < 9; i++) {
                    ItemStack stack = player.inventory.getStackInSlot(i);
                    if (stack != null && !stack.isEmpty() && stack.getItem() == ItemsRegister.RADIO) {
                        hasRadioInHotbar = true;
                        break;
                    }
                }
                if (!hasRadioInHotbar) {
                    VoiceAddon.removeFromActualGroup(player);
                }
            } else {
                // Para outros grupos: só pode permanecer se o celular estiver na mão principal ou offhand
                boolean holdingPhone =
                        (!player.getHeldItemMainhand().isEmpty() && player.getHeldItemMainhand().getItem() == ItemsRegister.ITEM_PHONE) ||
                        (!player.getHeldItemOffhand().isEmpty() && player.getHeldItemOffhand().getItem() == ItemsRegister.ITEM_PHONE);
                if (!holdingPhone) {
                    VoiceAddon.removeFromActualGroup(player);
                }
            }
        }
    }
}
