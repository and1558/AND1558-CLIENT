package owo.aydendevy.Mods;

import owo.aydendevy.Gui.HUD.ScreenPosition;
import owo.aydendevy.Mods.ModLoader.ModDraggable;

public class DisableAchievements extends ModDraggable {
    public boolean initialized = false;
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void render(ScreenPosition pos) {
        if(this.isEnabled() && !initialized){
            this.mc.gameSettings.showInventoryAchievementHint = false;
            initialized = true;
        }else if(!initialized){
            this.mc.gameSettings.showInventoryAchievementHint = true;
            initialized = true;
        }else{
            initialized = false;
        }
    }
}
