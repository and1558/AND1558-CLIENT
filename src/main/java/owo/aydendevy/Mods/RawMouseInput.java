package owo.aydendevy.Mods;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Mouse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MouseHelper;
import owo.aydendevy.Gui.HUD.ScreenPosition;
import owo.aydendevy.Mods.ModLoader.ModDraggable;
import owo.aydendevy.Mods.ModLoader.ModInstances;
import owo.aydendevy.DevyClient;

import java.lang.reflect.Constructor;

public class RawMouseInput extends ModDraggable {
    public static Controller[] controllers;
    public static Mouse mouse;
    public static int dx = 0;
    public static int dy = 0;

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
    @SuppressWarnings("unchecked")
    public static ControllerEnvironment createDefaultEnvironment() throws ReflectiveOperationException {
        // Find constructor (class is package private, so we can't access it directly)
        Constructor<ControllerEnvironment> constructor = (Constructor<ControllerEnvironment>)
                Class.forName("net.java.games.input.DefaultControllerEnvironment").getDeclaredConstructors()[0];

        // Constructor is package private, so we have to deactivate access control checks
        constructor.setAccessible(true);
        // Create object with default constructor
        return constructor.newInstance();
    }
    @Override
    public void render(ScreenPosition pos) {

    }
    public static void init(){
        controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        startThread();
    }
    public static void getMouse() {
        for (int i = 0; i < controllers.length && mouse == null; i++) {
            if (controllers[i].getType() == Controller.Type.MOUSE) {
                controllers[i].poll();
                if (((Mouse) controllers[i]).getX().getPollData() != 0.0 || ((Mouse) controllers[i]).getY().getPollData() != 0.0) {
                    mouse = (Mouse) controllers[i];
                }
            }
        }
    }
    public boolean enabled = false;
    /**
     * Toggle the raw input
     */
    public static void toggleRawInput() {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        float saveYaw = player.rotationYaw;
        float savePitch = player.rotationPitch;

        if (Minecraft.getMinecraft().mouseHelper instanceof RawMouseHelper) {
            Minecraft.getMinecraft().mouseHelper = new MouseHelper();
            //Minecraft.getMinecraft().mouseHelper.grabMouseCursor();
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Toggled OFF"));
            ModInstances.getRawInput().enabled = false;
            DevyClient.getIO.saveConfig(false, "rminput");
        } else {
            Minecraft.getMinecraft().mouseHelper = new RawMouseHelper();
            //Minecraft.getMinecraft().mouseHelper.grabMouseCursor();
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Toggled ON"));
            ModInstances.getRawInput().enabled = true;
            DevyClient.getIO.saveConfig(true, "rminput");
        }
        player.rotationYaw = saveYaw;
        player.rotationPitch = savePitch;
    }
    public static void turnOnRMInput(){
        Minecraft.getMinecraft().mouseHelper = new RawMouseHelper();
        //Minecraft.getMinecraft().mouseHelper.grabMouseCursor();
        //Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Toggled ON"));
        ModInstances.getRawInput().enabled = true;
    }

    public static void rescan() {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Rescanning input devices..."));
        RawMouseInput.getMouse();
        if (RawMouseInput.mouse != null) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Mouse Found."));
        }
    }

    public static void startThread() {
        Thread inputThread = new Thread(() -> {
            while(true){
                if (mouse != null && Minecraft.getMinecraft().currentScreen == null) {
                    mouse.poll();
                    dx += (int)mouse.getX().getPollData();
                    dy += (int)mouse.getY().getPollData();
                } else if (mouse != null) {
                    mouse.poll();
                } else {
                    getMouse();
                }

                try {
                    Thread.sleep(1);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        inputThread.setName("inputThread");
        inputThread.start();
    }
}
