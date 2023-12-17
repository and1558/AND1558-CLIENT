package uk.to.and1558.Plugins;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class RenderRGB {
    public static void drawChromaString(String string, int x, int y, boolean shadow)
    {
        Minecraft mc = Minecraft.getMinecraft();

        int xTmp = x;
        for (char textChar : string.toCharArray())
        {
            long l = System.currentTimeMillis() - (xTmp * 10 - y * 10);
            int i = Color.HSBtoRGB(l % (int) 2000.0F / 2000.0F, 0.8F, 0.8F);
            String tmp = String.valueOf(textChar);
            mc.fontRendererObj.drawString(tmp, xTmp, y, i, shadow);
            xTmp += mc.fontRendererObj.getCharWidth(textChar);
        }
    }
    public static void drawCenteredChromaString(FontRenderer fontRendererIn, String string, int x, int y, boolean shadow)
    {
        Minecraft mc = Minecraft.getMinecraft();

        int xTmp = x - fontRendererIn.getStringWidth(string) / 2;
        for (char textChar : string.toCharArray())
        {
            long l = System.currentTimeMillis() - (xTmp * 10 - y * 10);
            int i = Color.HSBtoRGB(l % (int) 2000.0F / 2000.0F, 0.8F, 0.8F);
            String tmp = String.valueOf(textChar);
            mc.fontRendererObj.drawString(tmp, xTmp, y, i, shadow);
            xTmp += mc.fontRendererObj.getCharWidth(textChar);
        }
    }
    public static void drawCenteredChristmasString(FontRenderer fontRendererIn, String string, int x, int y, boolean shadow)
    {
        boolean colorToggle = true;
        Minecraft mc = Minecraft.getMinecraft();

        int xTmp = x - fontRendererIn.getStringWidth(string) / 2;
        for (char textChar : string.toCharArray())
        {
            long l = System.currentTimeMillis() - (xTmp * 10 - y * 10);
            int i = 0xFF0000;
            if(colorToggle){
                i = 0xFF2222;
                colorToggle = false;
            }else{
                i = 0xFFFFFF;
                colorToggle = true;
            }
            String tmp = String.valueOf(textChar);
            mc.fontRendererObj.drawString(tmp, xTmp, y, i, shadow);
            xTmp += mc.fontRendererObj.getCharWidth(textChar);
        }
    }
}