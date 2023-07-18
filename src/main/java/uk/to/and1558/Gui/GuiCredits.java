package uk.to.and1558.Gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import uk.to.and1558.Plugins.ClientAnimations.Animation;
import uk.to.and1558.Plugins.ClientAnimations.Easing;

public class GuiCredits extends GuiScreen{
    int x = 50;
    // dev-1.82. Replaced animations logic to a cleaner and easier one
    // Animation list
    private Animation animation = new Animation(1000l,510,0, Easing.EASE_OUT_QUINT);
    private Animation animation1 = new Animation(1500l,510,0, Easing.EASE_OUT_QUINT);
    private Animation animation2 = new Animation(2000l,510,0, Easing.EASE_OUT_QUINT);
    private Animation animation3 = new Animation(2500l,510,0, Easing.EASE_OUT_QUINT);
    private Animation animation4 = new Animation(3000l,510,0, Easing.EASE_OUT_QUINT);
    private Animation animation5 = new Animation(3500l,510,0, Easing.EASE_OUT_QUINT);
    private Animation animation6 = new Animation(4000l,510,0, Easing.EASE_OUT_QUINT);
    private Animation animation7 = new Animation(4500l,510,0, Easing.EASE_OUT_QUINT);
    private Animation animation8 = new Animation(5000l,510,0, Easing.EASE_OUT_QUINT);
    private Animation animation9 = new Animation(5500l, 510,0, Easing.EASE_OUT_QUINT);
    private Animation animation10 = new Animation(6000l,510,0, Easing.EASE_OUT_QUINT);
    private Animation animation11 = new Animation(6500l,510,0, Easing.EASE_OUT_QUINT);
    private Animation animation12 = new Animation(7000l,510,0, Easing.EASE_OUT_QUINT);
    private Animation animation13 = new Animation(7500l,510,0, Easing.EASE_OUT_QUINT);

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(0, this.width / 2 - x, this.height / 2 + 68, 98, 20, "Exit to Main Menu"));
    }
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.mc.fontRendererObj,"AND1558 Client Credits", this.width / 2 - 2, (int) animation.getValue() + 20, -1);
        this.drawCenteredString(fontRendererObj, "§f§lDevelopers:", this.width / 2 - 2, (int) animation1.getValue() + 70, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Mojang Developers", this.width / 2 - 2, (int) animation2.getValue() + 90, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Eric Golde (also made the splash background art)", this.width / 2 - 2, (int) animation3.getValue() + 100, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Si1kn", this.width / 2 - 2, (int) animation4.getValue() + 110, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Hyperium Client Devs", this.width / 2 - 2, (int) animation5.getValue() + 120, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Maxytreal123", this.width / 2 - 2, (int) animation6.getValue() + 130, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "BananikXenos", this.width / 2 - 2, (int) animation7.getValue() + 140, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Simulatan and Sixfalls (Animations with Easings)", this.width / 2 - 2, (int) animation8.getValue() + 150, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "BlueBloxCraft", this.width / 2 - 2, (int) animation9.getValue() + 160, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "asbyth", this.width / 2 - 2, (int) animation10.getValue() + 170, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Gavin" ,this.width / 2 - 2, (int) animation11.getValue() + 180, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Canelex[Perspective Mod v3] and DjTheRedstoner[Perspective Mod v4]" ,this.width / 2 - 2, (int) animation12.getValue() + 190, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Open Source Mods", this.width / 2 - 2, (int) animation13.getValue() + 200, 0);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        switch (button.id) {
            case 0: {
                this.mc.displayGuiScreen(new MainMenu());
                break;
            }
        }
        super.actionPerformed(button);
    }

}
