package com.dev.sphone.mod.client.gui.phone.apps.bank;

import com.dev.sphone.mod.client.gui.phone.GuiBase;
import fr.aym.acsguis.component.textarea.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Screen displayed after a successful transfer.
 */
public class GuiBankSuccess extends GuiBase {

    public GuiBankSuccess(GuiScreen parent) {
        super(parent);
    }

    @Override
    public void GuiInit() {
        super.GuiInit();

        getStyleCustomizer().setBackgroundColor(0x00000000);

        add(getRoot());

        GuiLabel message = new GuiLabel("Transferência realizada com sucesso!");
        message.setCssId("message");
        getRoot().add(message);
    }

    @Override
    public List<ResourceLocation> getCssStyles() {
        List<ResourceLocation> styles = new ArrayList<>();
        styles.add(super.getCssStyles().get(0));
        styles.add(new ResourceLocation("sphone:css/bank_success.css"));
        return styles;
    }
}