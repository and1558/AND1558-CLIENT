package owo.aydendevy.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class GuiYesNoModded extends GuiScreen {
    String information = "information.message.text";
    int action = 0;
    GuiScreen prevScreen;
    GuiYesNoModded(String info, int act, GuiScreen prev){
        this.information = info;
        this.action = act;
        this.prevScreen = prev;
    }

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width /2 - 90, 150, 50, 20, "Yes"));
        this.buttonList.add(new GuiButton(1, this.width /2 + 50, 150, 50, 20, "No"));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(fontRendererObj, "Warning", this.width /2 ,10, -1);
        this.drawCenteredString(fontRendererObj, information, this.width /2 ,50, -1);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case 0:{
                if(action == 1){
                    this.mc.displayGuiScreen(new OldMainMenu());
                }else{
                    this.mc.displayGuiScreen(prevScreen);
                }
                break;
            }
            case 1:{
                this.mc.displayGuiScreen(prevScreen);
                break;
            }
        }
        super.actionPerformed(button);
    }
}
