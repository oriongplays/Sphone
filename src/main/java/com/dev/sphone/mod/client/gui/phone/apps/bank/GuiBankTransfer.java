package com.dev.sphone.mod.client.gui.phone.apps.bank;

import com.dev.sphone.mod.client.gui.phone.GuiBase;
import com.dev.sphone.mod.utils.UtilsServer;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import fr.aym.acsguis.utils.GuiTextureSprite;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import fr.aym.acsguis.event.listeners.IKeyboardListener;
import com.dev.sphone.mod.client.gui.phone.apps.bank.GuiBankSuccess;
import fr.aym.acsguis.component.textarea.GuiTextField;

import java.util.ArrayList;
import java.util.List;

/**
 * Transfer screen for the bank application.
 */
public class GuiBankTransfer extends GuiBase {

    public GuiBankTransfer(GuiScreen parent) {
        super(parent);
    }

    @Override
    public void GuiInit() {
        super.GuiInit();

        getStyleCustomizer().setBackgroundColor(0x00000000);

        add(getRoot());

        GuiLabel title = new GuiLabel("Transferencia");
        title.setCssId("city");
        getRoot().add(title);

        GuiLabel date = new GuiLabel(UtilsServer.getCurrentDateFormat("dd/MM/yyyy", null, 0));
        date.setCssId("date");
        getRoot().add(date);

        GuiPanel logo = new GuiPanel();
        logo.setCssId("weatherIcon");
        logo.getStyle().getCustomizer().setTexture(
                new GuiTextureSprite(new ResourceLocation("sphone:textures/ui/icons/bank-logo.png"))
        );
        getRoot().add(logo);

        GuiTextField recipient = new GuiTextField();
        recipient.setCssClass("bank_recipient");
        recipient.setText("");
        recipient.setFocused(true);
        getRoot().add(recipient);

        GuiTextField amount = new GuiTextField();
        amount.setCssClass("bank_amount");
        amount.setText("");
        getRoot().add(amount);

        Runnable transferAction = () -> {
            String dest = recipient.getText().trim();
            String value = amount.getText().trim();
            if (!dest.isEmpty() && !value.isEmpty()) {
                Minecraft mc = Minecraft.getMinecraft();
                mc.player.sendChatMessage("/pay " + dest + " " + value);
                GuiScreen current = this.getGuiScreen();
                mc.displayGuiScreen(null);
                mc.displayGuiScreen(new GuiBankSuccess(current).getGuiScreen());
            }
        };

        recipient.addKeyboardListener(new IKeyboardListener() {
            @Override
            public void onKeyTyped(char c, int keyCode) {
                if (keyCode == 28) {
                    amount.setFocused(true);
                }
            }
        });

        amount.addKeyboardListener(new IKeyboardListener() {
            @Override
            public void onKeyTyped(char c, int keyCode) {
                if (keyCode == 28) {
                    transferAction.run();
                }
            }
        });

        GuiLabel confirm = new GuiLabel("Confirmar");
        confirm.setCssClass("bank_confirm");
        confirm.addClickListener((x, y, button) -> transferAction.run());
        getRoot().add(confirm);
    }

    @Override
    public List<ResourceLocation> getCssStyles() {
        List<ResourceLocation> styles = new ArrayList<>();
        styles.add(super.getCssStyles().get(0));
        styles.add(new ResourceLocation("sphone:css/bank_transfer.css"));
        return styles;
    }
}
