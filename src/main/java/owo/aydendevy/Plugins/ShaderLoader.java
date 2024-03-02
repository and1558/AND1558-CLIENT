package owo.aydendevy.Plugins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.ResourceLocation;
import owo.aydendevy.Gui.impl.BlurUtils;
import owo.aydendevy.DevyClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ShaderLoader {
    // dev 1.82 -> Changed from using minecrafts default blur shader to BlurUtils code.
    public static void blurBackground() {
        if(DevyClient.getInstance().guiUtils.disableDefaultBlur) return;
        ScaledResolution sc = new ScaledResolution(Minecraft.getMinecraft());
        BlurUtils.renderBlurredBackground(sc.getScaledWidth(), sc.getScaledHeight(), 0, 0, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight, 0);
    }
    public static void loadLegacyBlurShader(ResourceLocation location){
        EntityRenderer renderer = Minecraft.getMinecraft().entityRenderer;

        try {/*Use a for outside development use*/
            // A Method provides information about, and access to, a single method on a class or interface.
            // EntityRenderer thing  // Get Method    // Method name (a = loadShader)
            Method method = EntityRenderer.class.getDeclaredMethod("loadShader", ResourceLocation.class); //ResourceLocation.class = name of args
            method.setAccessible(true);

            method.invoke(renderer, location);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}