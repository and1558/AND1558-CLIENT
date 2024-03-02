package owo.aydendevy.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import owo.aydendevy.Gui.HUD.HUDManager;
import owo.aydendevy.Gui.impl.GuiUtils;
import owo.aydendevy.Gui.impl.RButton;
import owo.aydendevy.Mods.ModLoader.ModInstances;
import owo.aydendevy.Mods.RawMouseInput;
import owo.aydendevy.DevyClient;

import java.io.IOException;

public class ModScreen extends GuiScreen {
    private HUDManager hudManager = HUDManager.getInstance();
    @Override
    public void initGui() {
        // Moved to loadButtons()
        loadButtons();
        super.initGui();
    }
    private void loadButtons(){
        // dev-1.82 -> Fix button Alignment
        // General Mods
        this.buttonList.add(new RButton(0, this.width / 2 - 60, this.height / 2 - 34, 120, 16, 7, "Toggle Mods"));
        this.buttonList.add(new RButton(1, this.width / 2 - 60, this.height / 2 - 17, 120, 16, 7, "Change Mods Position"));
        // Raw mouse input
        this.buttonList.add(new RButton(2, this.width / 2 - 60, this.height / 2, 120, 16, 7, "Re-detect Mouse"));
        this.buttonList.add(new RButton(3, this.width / 2 - 60, this.height / 2 + 17, 120, 16, 7, "Raw Mouse Input : " + (ModInstances.getRawInput().enabled ? "ON" : "OFF")));
        // Extra Gui Button
        this.buttonList.add(new RButton(4, this.width / 2 - 60, this.height / 2 + 34, 120, 16, 7, "Extra Options"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        //GuiUtils.drawRoundBG(this,true);
        // dev 1.82. Makes the box smaller
        GuiUtils.drawRoundBGMediumSize(this, true);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onGuiClosed() {
        DevyClient.getInstance().guiUtils.disableDefaultBlur = false;
        super.onGuiClosed();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case 0:{
                this.mc.displayGuiScreen(new ModTogglerScreen(this));
                break;
            }
            case 1:{
                hudManager.openConfigScreen(this);
                break;
            }
            case 2:{
                RawMouseInput.rescan();
                break;
            }
            case 3:{
                RawMouseInput.toggleRawInput();
                this.buttonList.clear();
                loadButtons();
                break;
            }
            case 4:{
                this.mc.displayGuiScreen(new MTSExtra());
                //and1558.getInstance().sendNotif("This is a test for the Notification System! UwU");
                //and1558.getInstance().sendNotif("LONGLONGLONGLONGLONGLONGLONGLONGLONGLONGLONGLONGLONGLONGLONGLONGLONGLONG");
                break;
            }
        }
        super.actionPerformed(button);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }
}
