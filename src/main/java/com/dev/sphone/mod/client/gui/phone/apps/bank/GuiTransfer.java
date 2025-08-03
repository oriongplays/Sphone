package com.dev.sphone.mod.client.gui.phone.apps.bank;

import com.dev.sphone.mod.client.gui.phone.GuiBase;
import fr.aym.acsguis.component.textarea.GuiLabel;
import fr.aym.acsguis.component.textarea.GuiTextField;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class GuiTransfer extends GuiBase {

    public GuiTransfer(GuiScreen parent) {
        super(parent);
    }

    @Override
    public void GuiInit(){
        super.GuiInit();

        add(getRoot());

        GuiLabel AppTitle = new GuiLabel("Transferir");
        AppTitle.setCssId("app_title");
        getRoot().add(AppTitle);

        GuiTextField titre = new GuiTextField();
        titre.setCssClass("titre");
        titre.setHintText("Destinatário");
        titre.setMaxTextLength(20);
        getRoot().add(titre);

        GuiTextField note = new GuiTextField(){
            @Override
            public boolean allowLineBreak() {
                return false;
            }
        };

        note.setCssClass("note");
        note.setHintText("Valor");
        note.setMaxTextLength(20);
        getRoot().add(note);

        GuiLabel buttonEdit = new GuiLabel("+");
        buttonEdit.setCssId("button_add");
        buttonEdit.addClickListener((mouseX, mouseY, mouseButton) -> {
            String dest = titre.getText();
            String amount = note.getText();
            if (!dest.isEmpty() && !amount.isEmpty()) {
                Minecraft.getMinecraft().player.sendChatMessage("/pay \"" + dest + "\" \"" + amount + "\"");
            }
        });
        getRoot().add(buttonEdit);
    }

    public List<ResourceLocation> getCssStyles() {
        List<ResourceLocation> styles = new ArrayList<>();
        styles.add(super.getCssStyles().get(0));
        styles.add(new ResourceLocation("sphone:css/transfer.css"));
        return styles;
    }
}