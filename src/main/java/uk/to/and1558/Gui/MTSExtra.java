package uk.to.and1558.Gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import uk.to.and1558.Gui.impl.GuiUtils;

public class MTSExtra extends GuiScreen {
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GuiUtils.drawRoundBG(this, false);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
