package uk.to.and1558.Plugins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ShaderLoader {

    public static void loadShader(ResourceLocation location) {
        // get the Entity Renderer (aka mobs and players)
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