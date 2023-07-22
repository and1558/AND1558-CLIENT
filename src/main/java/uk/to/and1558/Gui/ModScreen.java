package uk.to.and1558.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import uk.to.and1558.Gui.HUD.HUDManager;
import uk.to.and1558.Gui.impl.GuiUtils;
import uk.to.and1558.Gui.impl.RButton;
import uk.to.and1558.Mods.ModLoader.ModInstances;
import uk.to.and1558.Mods.RawMouseInput;
import uk.to.and1558.Plugins.ClientAnimations.Animation;
import uk.to.and1558.Plugins.ClientAnimations.Easing;
import uk.to.and1558.and1558;

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
        and1558.getInstance().guiUtils.disableDefaultBlur = false;
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
                break;
            }
        }
        super.actionPerformed(button);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }
}
