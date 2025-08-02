package com.dev.sphone.mod.client.gui.phone.apps.bank;

import com.dev.sphone.api.loaders.AppDetails;
import com.dev.sphone.api.loaders.AppType;
import com.dev.sphone.mod.client.gui.phone.GuiBase;
import com.dev.sphone.mod.utils.UtilsServer;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import fr.aym.acsguis.utils.GuiTextureSprite;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Home page for the bank application.
 */
@AppDetails(type = AppType.DEFAULT)
public class GuiBank extends GuiBase {

    public GuiBank(GuiScreen parent) {
        super(parent);
    }

    @Override
    public void GuiInit() {
        super.GuiInit();

        // Transparent background
        getStyleCustomizer().setBackgroundColor(0x00000000);

        add(getRoot());

        GuiLabel city = new GuiLabel("Olá cliente!");
        city.setCssId("city");
        getRoot().add(city);

        GuiLabel date = new GuiLabel(UtilsServer.getCurrentDateFormat("dd/MM/yyyy", null, 0));
        date.setCssId("date");
        getRoot().add(date);

        GuiPanel logo = new GuiPanel();
        logo.setCssId("weatherIcon");
        logo.getStyle().getCustomizer().setTexture(
                new GuiTextureSprite(new ResourceLocation("sphone:textures/ui/icons/bank-logo.png"))
        );
        getRoot().add(logo);

        GuiLabel saldo = new GuiLabel("Saldo");
        saldo.setCssId("forecastLabel");
        saldo.addClickListener((x, y, button) ->
                Minecraft.getMinecraft().player.sendChatMessage("/bal")
        );
        getRoot().add(saldo);

        GuiLabel transferencia = new GuiLabel("Transferencia");
        transferencia.setCssId("forecastIcon");
        transferencia.addClickListener((x, y, button) -> {
            Minecraft mc = Minecraft.getMinecraft();
            GuiScreen current = this.getGuiScreen();
            mc.displayGuiScreen(null);
            mc.displayGuiScreen(new GuiBankTransfer(current).getGuiScreen());
        });
        getRoot().add(transferencia);
    }

    @Override
    public List<ResourceLocation> getCssStyles() {
        List<ResourceLocation> styles = new ArrayList<>();
        styles.add(super.getCssStyles().get(0));
        styles.add(new ResourceLocation("sphone:css/bank.css"));
        return styles;
    }
}
