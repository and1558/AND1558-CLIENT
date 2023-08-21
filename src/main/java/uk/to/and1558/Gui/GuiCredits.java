package uk.to.and1558.Gui;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import uk.to.and1558.Plugins.ClientAnimations.Animation;
import uk.to.and1558.Plugins.ClientAnimations.Easing;

public class GuiCredits extends GuiScreen{

    // dev-1.82. Replaced animationYs logic to a cleaner and easier one
    // Animation list
    // dev-1.82 pt2. Redid the Animation
    // THIS IS A FUCKING MESS AAAAAAAAAAAA -AND1558
    private Animation animationY = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationY1  = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationY2 = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationY3 = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationY4 = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationY5 = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationY6 = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationY7 = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationY8 = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationY9 = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationY10 = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationY11 = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationY12 = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationY13 = new Animation(1l,0,510,Easing.EASE_IN_OUT_QUART);
    private Animation animationX = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    private Animation animationX1  = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    private Animation animationX2 = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    private Animation animationX3 = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    private Animation animationX4 = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    private Animation animationX5 = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    private Animation animationX6 = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    private Animation animationX7 = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    private Animation animationX8 = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    private Animation animationX9 = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    private Animation animationX10 = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    private Animation animationX11 = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    private Animation animationX12 = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    private Animation animationX13 = new Animation(1l,0,0,Easing.EASE_IN_OUT_QUART);
    boolean introPlayed = false;
    @Override
    public void initGui() {
        super.initGui();
        if(!introPlayed) playIntro();
        introPlayed = true;
    }
    Animation buttonX = new Animation(1f, 50, 300, Easing.EASE_IN_OUT_QUART);
    boolean animationSet = false;
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        // dev 1.82 -> Move button away on intro then move it back into the original position
        if (animationY13.getValue() < 180) {
            if(!animationSet){
                buttonX = new Animation(1f, 300, 50, Easing.EASE_IN_OUT_QUART);
            }
            animationSet = true;
        }
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - (int) buttonX.getValue(), this.height / 2 + 68, 98, 20, "Exit to Main Menu"));
        this.drawCenteredString(this.mc.fontRendererObj,"AND1558 Client Credits", this.width / 2 - 2 + (int) animationX.getValue(), (int) animationY.getValue() + 20, -1);
        this.drawCenteredString(fontRendererObj, "§f§lDevelopers:", this.width / 2 - 2 + (int) animationX1.getValue(), (int) animationY1.getValue() + 70, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Mojang Developers", this.width / 2 - 2 + (int) animationX2.getValue(), (int) animationY2.getValue() + 90, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Eric Golde", this.width / 2 - 2 + (int) animationX3.getValue(), (int) animationY3.getValue() + 100, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Si1kn", this.width / 2 - 2 + (int) animationX4.getValue(), (int) animationY4.getValue() + 110, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Hyperium Client Devs, CreativeMD", this.width / 2 - 2 + (int) animationX5.getValue(), (int) animationY5.getValue() + 120, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Maxytreal123", this.width / 2 - 2 + (int) animationX6.getValue(), (int) animationY6.getValue() + 130, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "BananikXenos", this.width / 2 - 2 + (int) animationX7.getValue(), (int) animationY7.getValue() + 140, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Simulatan and Sixfalls (Animations with Easings)", this.width / 2 - 2 + (int) animationX8.getValue(), (int) animationY8.getValue() + 150, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "BlueBloxCraft", this.width / 2 - 2 + (int) animationX9.getValue(), (int) animationY9.getValue() + 160, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "asbyth", this.width / 2 - 2 + (int) animationX10.getValue(), (int) animationY10.getValue() + 170, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Gavin" ,this.width / 2 - 2 + (int) animationX11.getValue(), (int) animationY11.getValue() + 180, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Canelex[Perspective Mod v3] and DjTheRedstoner[Perspective Mod v4]" ,this.width / 2 - 2 + (int) animationX12.getValue(), (int) animationY12.getValue() + 190, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Open Source Mods", this.width / 2 - 2 + (int) animationX13.getValue(), (int) animationY13.getValue() + 200, 0);
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
    public void playIntro(){
        new Thread(() -> {
            try {
                Thread.sleep(500);
                animationY = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationY1 = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationY2 = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationY3 = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationY4 = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationY5 = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationY6 = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationY7 = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationY8 = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationY9 = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationY10 = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationY11 = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationY12 = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationY13 = new Animation(1000l, 510, 0, Easing.EASE_OUT_QUINT);
                Thread.sleep(500);
                playAnim();
            }
            catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }).start();
    }
    public void playAnim(){
        new Thread(()->{
            try {
                // Stage 1 -> go right non-loop
                animationX = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX1 = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX2 = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX3 = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX4 = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX5 = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX6 = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX7 = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX8 = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX9 = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX10 = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX11 = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX12 = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX13 = new Animation(1000l, 0, 20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                // Stage 2 -> go right non-loop
                animationX = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX1 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX2 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX3 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX4 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX5 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX6 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX7 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX8 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX9 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX10 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX11 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX12 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                animationX13 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                Thread.sleep(50);
                while(true) {
                    animationX = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX1 = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX2 = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX3 = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX4 = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX5 = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX6 = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX7 = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX8 = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX9 = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX10 = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX11 = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX12 = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX13 = new Animation(1000l, -20, 20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    // ====================================
                    animationX = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX1 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX2 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX3 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX4 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX5 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX6 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX7 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX8 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX9 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX10 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX11 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX12 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                    animationX13 = new Animation(1000l, 20, -20, Easing.EASE_OUT_QUINT);
                    Thread.sleep(50);
                }
            }
            catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }).start();
    }
}
