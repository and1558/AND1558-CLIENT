package uk.to.and1558.Gui;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import uk.to.and1558.Gui.FallbackGui.PanelCrashReport;
import uk.to.and1558.Gui.fun.GuiSnake;
import uk.to.and1558.Gui.impl.UnicodeFontRenderer;
import uk.to.and1558.Mods.ModLoader.ModInstances;
import uk.to.and1558.Gui.impl.RButton;
import uk.to.and1558.Plugins.ClientAnimations.Animation;
import uk.to.and1558.Plugins.ClientAnimations.Easing;
import uk.to.and1558.Plugins.RenderRGB;
import uk.to.and1558.VersionString;
import uk.to.and1558.and1558;
import uk.to.and1558.club.aetherium.api.particle.SnowfallParticles;

import static org.lwjgl.opengl.GL11.glColor4f;

public class MainMenu extends GuiScreen
{
    public MainMenu(){
        mainMenu = this;
    }
    public RButton multiplayer;
    int posX = -400;
    // Animations Variable
    private Animation animation,opacityTransition,logoAnim,clientTextAnim;

    @Override
    public void initGui() {
        and1558.getInstance().init2();
        posX = -400;
        // dev 1.82 -> Removes offensive text
        and1558.getInstance().getDiscordRPC().update("Main Menu", "Waiting for input"); //Idle, Main Menu
        this.buttonList.add(new RButton(1, this.width / 2 - 50, this.height / 2 - 17, 98, 16, 7, "Singleplayer")); //Singleplayer
        this.buttonList.add(multiplayer = new RButton(2, this.width / 2 - 50, this.height / 2, 98, 16, 7, "Multiplayer")); //Multiplayer
        this.buttonList.add(new RButton(10, this.width / 2 - 50, this.height / 2 + 17, 98, 16, 7, "Changelog"));
        this.buttonList.add(new RButton(3, this.width / 2 - 50, this.height / 2 + 34, 98, 16, 7, "Settings"));//Settings
        this.buttonList.add(new RButton(4, this.width / 2 - 50, this.height / 2 + 51, 98, 16, 7, "Old Main Menu"));
        this.buttonList.add(new RButton(5, this.width / 2 - 50, this.height / 2 + 68, 98, 16, 7, "Mod Options"));
        this.buttonList.add(new RButton(8, this.width / 2 - 50, this.height / 2 + 85, 98, 16, 7, "Credits"));
        this.buttonList.add(new RButton(9, this.width - 136, 6, 110, 16, 7, EnumChatFormatting.BOLD + "Change Session"));
        this.buttonList.add(new RButton(7, this.width - 23, 6, 17, 17, 7, EnumChatFormatting.BOLD + ""));
        // dev 1.82. Added Animations for the UI when opened
        animation = new Animation(2f,-200,0, Easing.EASE_OUT_QUINT);
        opacityTransition = new Animation(5f, 0.0f, 1.0f, Easing.EASE_OUT_QUINT);
        logoAnim = new Animation(2f, -50, 30, Easing.EASE_OUT_QUINT);
        clientTextAnim = new Animation(2f, this.width, 0, Easing.EASE_OUT_QUINT);
        super.initGui();
    }
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        // dev 1.82 -> Changed to a smoother animation
        if(animation != null)
            posX = (int) animation.getValue();

        and1558.getInstance().runSingleplayer();
        // DECEMBER UPDATE!1
        and1558.drawClientBackground(and1558.options.darkMode, this.width, this.height);
        final String s1 = "Copyright " + EnumChatFormatting.RESET+ EnumChatFormatting.RED + EnumChatFormatting.BOLD + "Mojang AB" + EnumChatFormatting.RESET + ", DO NOT REDISTRIBUTE";
        this.drawString(fontRendererObj, s1, this.width - this.fontRendererObj.getStringWidth(s1) - 2, this.height - 10, -1);
        DrawChangelogs.getLess(this, this.fontRendererObj, posX);

        // dev 1.82. Added Animations for client text name
        this.drawCenteredString(this.mc.fontRendererObj, EnumChatFormatting.BOLD + "AND1558 " + EnumChatFormatting.RESET + "Client", this.width / 2 - 2 - (int) clientTextAnim.getValue(), this.height / 2 - 30, -1);

        super.drawScreen(mouseX, mouseY, partialTicks);
        mc.getTextureManager().bindTexture(new ResourceLocation("and1558/images/menu/exit.png"));
        Gui.drawModalRectWithCustomSizedTexture(width - 22, 7, 0, 0, 16, 16, 16, 16);
        GL11.glEnable(GL11.GL_BLEND);
        glColor4f(1.0F,1.0F,1.0F,opacityTransition.getValue());
        // Draws Minecraft Logo
        int i = 274;
        int j = this.width / 2 - i / 2;
        int k = (int) logoAnim.getValue();
        this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/title/minecraft.png"));
        //GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        this.drawTexturedModalRect(j + 0, k + 0, 0, 0, 155, 44);
        this.drawTexturedModalRect(j + 155, k + 0, 0, 45, 155, 44);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) (this.width / 2 + 90), 70.0F, 0.0F);
        GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
        float f = 1.8F - MathHelper.abs(MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.5F);
        // dev 1.82 -> Changed text from "Finally Mixin!" to "Welcome {username}"
        if (Calendar.getInstance().get(Calendar.MONTH) == Calendar.DECEMBER) {
            f = f * 140.0F / (float) (this.fontRendererObj.getStringWidth("Happy Holiday " + mc.getSession().getUsername() + "!") + 32);
            GlStateManager.scale(f, f, f);
            RenderRGB.drawCenteredChristmasString(this.fontRendererObj, "Happy Holiday " + mc.getSession().getUsername() + "!", 0, -8, true);
            GlStateManager.popMatrix();
        }else{
            f = f * 100.0F / (float) (this.fontRendererObj.getStringWidth("Welcome " + mc.getSession().getUsername() + "!") + 32);
            GlStateManager.scale(f, f, f);
            this.drawCenteredString(this.fontRendererObj, "Welcome " + mc.getSession().getUsername() + "!", 0, -8, -256);
            GlStateManager.popMatrix();
        }
        GL11.glDisable(GL11.GL_BLEND);
        // dev 1.82 -> Fixed yellowed background when using Fast Render on Optifine
        glColor4f(1,1,1,1);
    }

    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        switch (button.id) {
            case 1: {
                this.mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            }
            case 2: {
                this.mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            }
            case 3: {
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;
            }
            case 4: {
                this.mc.displayGuiScreen(new GuiYesNoModded("Old Main Menu is bugged in this Mixin Build, Press yes to continue", 1, this));
                //this.mc.displayGuiScreen(new TestingGui());
                break;
            }
            case 5:{
                this.mc.displayGuiScreen(new ModScreen());
                break;
            }
            case 6:{
                and1558.getInstance().logger.info("Checking Access");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                and1558.getInstance().logger.error("BLOCKED MENU IS ACCESSED, CLOSING!!!");
                and1558.getInstance().logger.error("CLOSING FAILED, AN UNKNOWN CODE HAS BEEN EXECUTED!!!");
                and1558.getInstance().logger.info("Running crash screen!");
                PanelCrashReport.crashinfo = "Realms TEST is disabled, and an unknown code is executed";
                this.mc.displayGuiScreen(new PanelCrashReport("ACCESSING BANNED MENU"));
                break;
            }
            case 7: {
                this.mc.shutdown();
                break;
            }
            case 8: {
                this.mc.displayGuiScreen(new GuiSnake());
                break;
            }
            case 9:{
                this.mc.displayGuiScreen(new GuiSelectSession());
                break;
            }
            case 10:{
                this.mc.displayGuiScreen(new ChangelogScreen(this));
                break;
            }
        }
        super.actionPerformed(button);
    }


    @Override
    public void updateScreen() {
        if(and1558.INSTANCE.started) {
            // dev 1.82 -> Logo opacity transition moved to drawScreen()
            // dev 1.82 -> Changelog animation moved to drawScreen()
        }else {

        }
        super.updateScreen();
    }
    public static MainMenu mainMenu;
}
