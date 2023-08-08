package uk.to.and1558.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class GuiSelectSession extends GuiScreen {
    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 2 - 30, "Microsoft"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 2 - 4, "Offline"));
        super.initGui();
        //this.mc.displayGuiScreen(new AltManagerGui());
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Which type of session you want to login?", this.width / 2, 15, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case 0:{
                this.mc.displayGuiScreen(new MSAuthGUI());
                break;
            }
            case 1:{
                this.mc.displayGuiScreen(new OfflineAuthGUI());
                break;
            }
        }
        super.actionPerformed(button);
    }
}
