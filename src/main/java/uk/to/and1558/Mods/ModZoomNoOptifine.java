package uk.to.and1558.Mods;

import org.lwjgl.input.Keyboard;
import uk.to.and1558.Gui.HUD.ScreenPosition;
import uk.to.and1558.Mods.ModLoader.ModDraggable;

public class ModZoomNoOptifine extends ModDraggable {
    private float lastFov = 90.0F;
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
    public boolean onZoom = false;
    boolean saved;
    boolean stop;
    @Override
    public void render(ScreenPosition pos) {
        /**if(Keyboard.isKeyDown(Keyboard.KEY_C)){
            if(!saved){
                this.lastFov = this.mc.gameSettings.fovSetting;
                saved = true;
            }
            this.onZoom = true;
            this.mc.gameSettings.fovSetting = 25;
            this.mc.gameSettings.smoothCamera = true;
            stop = false;
        }else if (!stop){
            this.onZoom = false;
            this.mc.gameSettings.fovSetting = lastFov;
            this.mc.gameSettings.smoothCamera = false;
            saved = false;
            stop = true;
        }**/
    }
}
