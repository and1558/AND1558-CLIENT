package owo.aydendevy.Mods;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import owo.aydendevy.Events.EventTarget;
import owo.aydendevy.Events.KeyEvent;
import owo.aydendevy.Gui.HUD.ScreenPosition;
import owo.aydendevy.Mods.ModLoader.ModDraggable;
import owo.aydendevy.Mods.ModLoader.ModInstances;
import owo.aydendevy.DevyClient;

public class PerspectiveMod extends ModDraggable {
    private boolean returnOnRelease;
    public boolean perspectiveToggled;
    private float cameraYaw;
    private float cameraPitch;
    private int previousPerspective;

    public PerspectiveMod() {
        this.returnOnRelease = true;
        this.perspectiveToggled = false;
        this.cameraYaw = 0.0f;
        this.cameraPitch = 0.0f;
        this.previousPerspective = 0;
    }
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
    @EventTarget
    public void KeyboardEvent(KeyEvent e){
        /**if (this.isEnabled) {
            if (and1558.getInstance().PRSPCTVMOD.isKeyDown()) {
                if (Keyboard.getEventKeyState()) {
                    this.perspectiveToggled = !this.perspectiveToggled;
                    this.cameraYaw = this.mc.thePlayer.rotationYaw;
                    this.cameraPitch = this.mc.thePlayer.rotationPitch;
                    if (this.perspectiveToggled) {
                        this.previousPerspective = this.mc.gameSettings.thirdPersonView;
                        this.mc.gameSettings.thirdPersonView = 1;
                    }
                    else {
                        this.mc.gameSettings.thirdPersonView = this.previousPerspective;
                    }
                }
                else if (this.returnOnRelease) {
                    this.perspectiveToggled = false;
                    this.mc.gameSettings.thirdPersonView = this.previousPerspective;
                }
            }
            if (e.getKey() == this.mc.gameSettings.keyBindTogglePerspective.getKeyCode()) {
                this.perspectiveToggled = false;
            }
        }**/
    }
    public void startPerspective(boolean down){
        // Thanks to Canelex||DjTheRedstoner
        if (this.isEnabled) {
            if (down) {
                this.perspectiveToggled = !this.perspectiveToggled;
                this.cameraYaw = this.mc.thePlayer.rotationYaw;
                this.cameraPitch = this.mc.thePlayer.rotationPitch;
                if (this.perspectiveToggled) {
                    this.previousPerspective = this.mc.gameSettings.thirdPersonView;
                    this.mc.gameSettings.thirdPersonView = 1;
                }
                else {
                    this.mc.gameSettings.thirdPersonView = this.previousPerspective;
                }
                mc.renderGlobal.setDisplayListEntitiesDirty();
            } else if (this.returnOnRelease) {
                this.perspectiveToggled = false;
                this.mc.gameSettings.thirdPersonView = this.previousPerspective;
            }
            if (this.mc.gameSettings.keyBindTogglePerspective.isPressed()) {
                this.perspectiveToggled = false;
            }
        }
    }
    public float getCameraYaw() {
        return this.perspectiveToggled ? this.cameraYaw : this.mc.thePlayer.rotationYaw;
    }

    public float getCameraPitch() {
        return this.perspectiveToggled ? this.cameraPitch : this.mc.thePlayer.rotationPitch;
    }
    public boolean overrideMouse() {
        if (this.mc.inGameHasFocus && Display.isActive()) {
            if (!this.perspectiveToggled) {
                return true;
            }
            this.mc.mouseHelper.mouseXYChange();
            final float f1 = this.mc.gameSettings.mouseSensitivity * 0.6f + 0.2f;
            final float f2 = f1 * f1 * f1 * 8.0f;
            final float f3 = this.mc.mouseHelper.deltaX * f2;
            final float f4 = this.mc.mouseHelper.deltaY * f2;
            this.cameraYaw += f3 * 0.15f;
            this.cameraPitch += f4 * 0.15f;
            if (this.cameraPitch > 90.0f) {
                this.cameraPitch = 90.0f;
            }
            if (this.cameraPitch < -90.0f) {
                this.cameraPitch = -90.0f;
            }
        }
        return false;
    }

    @Override
    public void render(ScreenPosition pos) {
        if(Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel") && ModInstances.getPerspective().isEnabled){
            //this.mc.displayGuiScreen(new PanelCrashReport("Perspective mod"));
            ModInstances.getPerspective().isEnabled = false;
            DevyClient.getIO.saveConfig(false, "perspective");
            new Thread(() ->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                DevyClient.getInstance().sendNotif("Perspective Mod has been disabled because it is banned in this server!");
            }).start();
        }
    }
}
