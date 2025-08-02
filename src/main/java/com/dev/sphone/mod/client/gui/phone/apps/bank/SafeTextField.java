package com.dev.sphone.mod.client.gui.phone.apps.bank;

import fr.aym.acsguis.component.textarea.GuiTextField;
import fr.aym.acsguis.utils.ComponentRenderContext;

/**
 * Text field that prevents crashes when the internal line cache is empty.
 */
public class SafeTextField extends GuiTextField {
    @Override
    public void drawForeground(int mouseX, int mouseY, float partialTicks, ComponentRenderContext context) {
        if (getCachedTextLines().isEmpty()) {
            super.setText("");
        }
        super.drawForeground(mouseX, mouseY, partialTicks, context);
    }
}