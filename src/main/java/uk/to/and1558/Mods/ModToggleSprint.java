package uk.to.and1558.Mods;

import uk.to.and1558.Gui.HUD.ScreenPosition;
import uk.to.and1558.Mods.ModLoader.ModDraggable;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ModToggleSprint extends ModDraggable {
    private float bps;

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
        this.bps = (float) (mc.thePlayer.getDistance(mc.thePlayer.lastTickPosX, mc.thePlayer.posY, mc.thePlayer.lastTickPosZ) * 20.0F);
        /**
         * added bps counter just to not let player keep sprinting and possibly get banned on a server
         */
        if(this.isEnabled && bps > 0.9999 && this.mc.gameSettings.keyBindForward.isKeyDown() == true){
            this.mc.thePlayer.setSprinting(true);
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        super.renderDummy(pos);
    }
}
