package uk.to.and1558.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import uk.to.and1558.Gui.impl.GuiCheckBox;
import uk.to.and1558.Gui.impl.GuiUtils;
import uk.to.and1558.and1558;
import uk.to.and1558.club.aetherium.api.particle.SnowfallParticles;

import java.io.IOException;

public class MTSExtra extends GuiScreen {
    @Override
    public void initGui() {
        this.buttonList.add(new GuiCheckBox(0, 26, 40, and1558.options.darkMode));
        super.initGui();
    }
    //SliderElement slider = new SliderElement((int) 100, (int) 60, (int) 100, (int) 16, (float) 0, (float) 100, (float) 50);
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GuiUtils.drawRoundBG(this, true);
        drawCenteredString(fontRendererObj, "Extra Configurations", this.width / 2, 20,-1);
        drawString(fontRendererObj, "- Force Seasonal Background Dark Mode", 49, 46, -1);
        //slider.draw();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        //if(slider.mouseOverlap(mouseX, mouseY)) slider.interact(mouseX, mouseY);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case 0:{
                and1558.options.darkMode = !and1558.options.darkMode;
                and1558.options.save("darkMode", and1558.options.darkMode);
                and1558.options.load();
                break;
            }
        }
        super.actionPerformed(button);
    }
}
