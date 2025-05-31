package com.dev.sphone.mod.client.gui.phone.apps.plusplusgame;

import com.dev.sphone.api.loaders.AppDetails;
import com.dev.sphone.api.loaders.AppType;
import com.dev.sphone.mod.client.gui.phone.GuiBase;
import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

@AppDetails(type = AppType.DOWNLOADABLE)
public class GuiPlusPlusGame extends GuiBase {

    public GuiPlusPlusGame(GuiScreen parent) {
        super(parent);
    }

    int points = 0;
    GuiLabel counter;

    @Override
    public void GuiInit() {
        super.GuiInit();

        counter = new GuiLabel(I18n.format("sphone.plusplusgame.points") + " 0");
        counter.setCssId("counter");

        GuiPanel gamePanel = new GuiPanel();
        gamePanel.setCssClass("game_panel");
        getRoot().add(gamePanel);

        // Adiciona o primeiro botão após o primeiro tick da GUI (para garantir layout pronto)
        gamePanel.addTickListener(() -> {
            if (gamePanel.getChildComponents().isEmpty()) {
                drawRandomButton(gamePanel);
            }
        });

        getRoot().add(counter);
        add(this.getRoot());
    }

    public void drawRandomButton(GuiPanel backframe) {
        GuiPanel button = new GuiPanel();
        button.setCssClass("button");

        int wmax = Math.max(1, (int)(backframe.getWidth() - 50));
        int hmax = Math.max(1, (int)(backframe.getHeight() - 50));
        int x = (int) (Math.random() * wmax);
        int y = (int) (Math.random() * hmax);

        getStyle().setOffsetX(x);
        getStyle().setOffsetY(y);

        button.addClickListener((mouseX, mouseY, mouseButton) -> {
            points++;
            backframe.remove(button);
            // Troca de background color pelo Customizer:
            String color = String.format("#%06X", (int)(Math.random() * 0xFFFFFF));
            backframe.getStyle().getCustomizer().setBackgroundColor(
                Integer.parseUnsignedInt(color.substring(1), 16) | 0xFF000000
            );
            counter.setText(I18n.format("sphone.plusplusgame.points") + " " + points);
            drawRandomButton(backframe);
        });

        backframe.add(button);
    }

    @Override
    public List<ResourceLocation> getCssStyles() {
        List<ResourceLocation> styles = new ArrayList<>();
        styles.add(super.getCssStyles().get(0));
        styles.add(new ResourceLocation("sphone:css/plusplusgame.css"));
        return styles;
    }
}
