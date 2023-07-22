package uk.to.and1558.Gui;

import net.minecraft.client.gui.GuiScreen;
import uk.to.and1558.Gui.impl.GuiUtils;
import uk.to.and1558.and1558;

import java.io.IOException;

public class MTSExtra extends GuiScreen {

    @Override
    public void initGui() {

        super.initGui();
    }
    //SliderElement slider = new SliderElement((int) 100, (int) 60, (int) 100, (int) 16, (float) 0, (float) 100, (float) 50);
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GuiUtils.drawRoundBG(this, false);
        drawCenteredString(fontRendererObj, "Extra Configurations", this.width / 2, 20,-1);
        //slider.draw();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        //if(slider.mouseOverlap(mouseX, mouseY)) slider.interact(mouseX, mouseY);
    }
}
