package owo.aydendevy.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import owo.aydendevy.Plugins.ClientAnimations.Animation;
import owo.aydendevy.Plugins.ClientAnimations.Easing;

import java.io.IOException;

public class ChangelogScreen extends GuiScreen {
    private GuiScreen lastScreen;
    ChangelogScreen(GuiScreen lastScreen){
        this.lastScreen = lastScreen;
    }
    Animation animation = null;
    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height - 27, "Back"));
        animation = new Animation(1f, -200, 1, Easing.EASE_OUT_QUINT);
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case 0:{
                this.mc.displayGuiScreen(lastScreen);
                break;
            }
        }
        super.actionPerformed(button);
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        DrawChangelogs.get(this, this.fontRendererObj, (int) animation.getValue());

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }
}
