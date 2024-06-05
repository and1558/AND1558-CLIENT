package owo.aydendevy.Gui.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import owo.aydendevy.Plugins.ClientAnimations.Animation;
import owo.aydendevy.DevyClient;

public class GuiUtils {
    public boolean disableDefaultBlur = false;
    static double x = 10;
    static double y = 10;
    // Draw a GUI with a black rounded background over the world or the default main menu background
    // dev 1.82 -> with or without blur
    public static void drawRoundBG(GuiScreen screen, boolean blurred){
        if(Minecraft.getMinecraft().theWorld == null){
            DevyClient.drawClientBackground(DevyClient.options.darkMode, screen.width, screen.height);
        }
        // Checks if blur is enabled or not [dev 1.82]
        if(blurred){
            DevyClient.guiUtils.disableDefaultBlur = true;
            ScaledResolution sc = new ScaledResolution(Minecraft.getMinecraft());
            BlurUtils.renderBlurredBackground(sc.getScaledWidth(), sc.getScaledHeight(), (int) boxSizeX.getValue(), (int) boxSizeY.getValue(), (int) boxSizeX1.getValue() - 10, (int) boxSizeY1.getValue() - 10, 7);
        }else if(!blurred){
            DevyClient.guiUtils.disableDefaultBlur = false;
        }
        GlStateManager.enableBlend();
        //owo.aydendevy.Renderer.GuiUtils.drawRoundedRect(x, y, screen.width - 10, screen.height - 10, 7, 0x87000000);
        owo.aydendevy.Renderer.GuiUtils.drawRoundedRect(boxSizeX.getValue(), boxSizeY.getValue(), boxSizeX1.getValue(), boxSizeY1.getValue(), 7, 0x87000000);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
    public static Animation boxSizeX, boxSizeY, boxSizeX1,
            boxSizeY1, boxSizeXM, boxSizeYM, boxSizeX1M, boxSizeY1M;
    // dev 1.82. Added a medium sized box
    public static void drawRoundBGMediumSize(GuiScreen screen, boolean blurred){
        if(Minecraft.getMinecraft().theWorld == null){
            DevyClient.drawClientBackground(DevyClient.options.darkMode, screen.width, screen.height);
        }
        if(blurred){
            DevyClient.guiUtils.disableDefaultBlur = true;
            ScaledResolution sc = new ScaledResolution(Minecraft.getMinecraft());
            BlurUtils.renderBlurredBackground(sc.getScaledWidth(), sc.getScaledHeight(), (int) boxSizeXM.getValue(), (int) boxSizeYM.getValue(), (int) boxSizeX1M.getValue() - 150, (int) boxSizeY1M.getValue() - 50, 7);
        }else if(!blurred){
            DevyClient.guiUtils.disableDefaultBlur = false;
        }
        GlStateManager.enableBlend();
        owo.aydendevy.Renderer.GuiUtils.drawRoundedRect(boxSizeXM.getValue(), boxSizeYM.getValue(), boxSizeX1M.getValue(), boxSizeY1M.getValue(), 7, 0x87000000);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
