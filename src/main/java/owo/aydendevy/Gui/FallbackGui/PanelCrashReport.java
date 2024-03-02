package owo.aydendevy.Gui.FallbackGui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import owo.aydendevy.Gui.ModTogglerScreen;

public class PanelCrashReport extends GuiScreen
{
    public static String crashinfo = null;
    public PanelCrashReport(String info){
        crashinfo = info;
    }
    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 140, this.height / 4 - 60 + 60 + 92, "Goto Mod Toggler Menu"));
        if(crashinfo == null) {
            crashinfo = "Unable to get Banned Mods info";
        }
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id){
            case 1:
                this.mc.displayGuiScreen(new ModTogglerScreen());
                break;
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Banned mods on Banned server!", this.width / 2, this.height / 4 - 60 + 20, 16777215);
        this.drawString(this.fontRendererObj, "You are currently using a banned mod in a server that banned them.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 0, 10526880);
        this.drawString(this.fontRendererObj, "To close this menu, please disable the banned mod that is listed below", this.width / 2 - 140, this.height / 4 - 60 + 60 + 18, 10526880);
        this.drawString(this.fontRendererObj, "We know this might be an inconvenience", this.width / 2 - 140, this.height / 4 - 60 + 60 + 27, 10526880);
        this.drawString(this.fontRendererObj, "but we have to stay abide by server rules", this.width / 2 - 140, this.height / 4 - 60 + 60 + 36, 10526880);
        this.drawString(this.fontRendererObj, "To prevent false anticheat bans, we displayed this menu.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 54, 10526880);
        this.drawString(this.fontRendererObj, "Thank you and have fun!", this.width / 2 - 140, this.height / 4 - 60 + 60 + 63, 10526880);
        this.drawString(this.fontRendererObj, "Banned mods: " + crashinfo, this.width / 2 - 140, this.height / 4 - 60 + 60 + 72, 10526880);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onGuiClosed() {

    }
}
