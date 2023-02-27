package uk.to.and1558.Gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import uk.to.and1558.Gui.HUD.HUDConfigScreen;
import uk.to.and1558.Gui.HUD.HUDManager;
import uk.to.and1558.Gui.impl.GuiUtils;
import uk.to.and1558.Gui.impl.RButton;
import uk.to.and1558.Mods.ModLoader.ModInstances;
import uk.to.and1558.Mods.RawMouseInput;

import java.io.IOException;

public class ModScreen extends GuiScreen {
    double x = 10;
    double y = 10;
    double x1 = 10;
    double y1 = 10;
    private HUDManager hudManager = HUDManager.getInstance();
    @Override
    public void initGui() {
        // General Mods
        this.buttonList.add(new RButton(0, this.width / 2 - 50, this.height / 2 - 34, 98, 16, 7, "Toggle Mods"));
        this.buttonList.add(new RButton(1, this.width / 2 - 50, this.height / 2 - 17, 98, 16, 7, "Change Mods Position"));
        // Raw mouse input
        this.buttonList.add(new RButton(2, this.width / 2 - 50, this.height / 2, 98, 16, 7, "Re-detect Mouse"));
        this.buttonList.add(new RButton(3, this.width / 2 - 50, this.height / 2 + 17, 98, 16, 7, "Raw Mouse Input : " + ModInstances.getRawInput().enabled));
        // Extra Gui Button
        this.buttonList.add(new RButton(4, this.width / 2 - 50, this.height / 2 + 34, 98, 16, 7, "Extra Options"));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GuiUtils.drawRoundBG(this);
        super.drawScreen(mouseX, mouseY, partialTicks);
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
                this.mc.displayGuiScreen(new ModScreen());
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
    public void updateScreen() {
        super.updateScreen();
    }
}
