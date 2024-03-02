package owo.aydendevy.Plugins;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class OldScaleResolution
{
    /* Credit to Mojang
     *
     * Old Scale Resolution For
     * OLD F3 MODS
     * more probably in the future
     *
     */
    private int scaledWidth;
    private int scaledHeight;
    private double scaledWidthD;
    private double scaledHeightD;
    private int scaleFactor;
    private static final String __OBFID = "CL_00000666";

    public OldScaleResolution(Minecraft p_i46324_1_, int p_i46324_2_, int p_i46324_3_)
    {
        this.scaledWidth = p_i46324_2_;
        this.scaledHeight = p_i46324_3_;
        this.scaleFactor = 1;
        boolean var4 = p_i46324_1_.isUnicode();
        int var5 = p_i46324_1_.gameSettings.guiScale;

        if (var5 == 0)
        {
            var5 = 1000;
        }

        while (this.scaleFactor < var5 && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240)
        {
            ++this.scaleFactor;
        }

        if (var4 && this.scaleFactor % 2 != 0 && this.scaleFactor != 1)
        {
            --this.scaleFactor;
        }

        this.scaledWidthD = (double)this.scaledWidth / (double)this.scaleFactor;
        this.scaledHeightD = (double)this.scaledHeight / (double)this.scaleFactor;
        this.scaledWidth = MathHelper.ceiling_double_int(this.scaledWidthD);
        this.scaledHeight = MathHelper.ceiling_double_int(this.scaledHeightD);
    }

    public int getScaledWidth()
    {
        return this.scaledWidth;
    }
}
