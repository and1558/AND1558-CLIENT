package uk.to.and1558.Plugins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import uk.to.and1558.Gui.impl.BlurUtils;
import uk.to.and1558.and1558;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ShaderLoader {
    // dev 1.82 -> Changed from using minecrafts default blur shader to BlurUtils code.
    public static void blurBackground() {
        if(and1558.getInstance().guiUtils.disableDefaultBlur) return;
        ScaledResolution sc = new ScaledResolution(Minecraft.getMinecraft());
        BlurUtils.renderBlurredBackground(sc.getScaledWidth(), sc.getScaledHeight(), 0, 0, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight, 0);
    }
}