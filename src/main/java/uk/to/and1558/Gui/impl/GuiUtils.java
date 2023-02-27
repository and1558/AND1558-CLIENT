package uk.to.and1558.Gui.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class GuiUtils {
    static double x = 10;
    static double y = 10;
    public static void drawRoundBG(GuiScreen screen){
        if(Minecraft.getMinecraft().theWorld == null){
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("and1558/images/bg.jpg"));
            Gui.drawModalRectWithCustomSizedTexture(-21 + Mouse.getX() / 90, Mouse.getY() * -1 / 90, 0.0f, 0.0f, screen.width + 20, screen.height + 20, (float)(screen.width + 21), (float)(screen.height + 20));
        }
        GlStateManager.enableBlend();
        uk.to.and1558.Renderer.GuiUtils.drawRoundedRect(x, y, screen.width - 10, screen.height - 10, 7, 0x87000000);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
