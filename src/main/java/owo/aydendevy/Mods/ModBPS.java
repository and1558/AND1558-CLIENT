package owo.aydendevy.Mods;

import net.minecraft.client.Minecraft;
import owo.aydendevy.Gui.HUD.ScreenPosition;
import owo.aydendevy.Mods.ModLoader.ModDraggable;

public class ModBPS extends ModDraggable {

	private ScreenPosition pos;
	float bps; 

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return font.getStringWidth("Speed: <Edit Mode>");
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		// TODO Auto-generated method stub
		this.bps = (float) (mc.thePlayer.getDistance(mc.thePlayer.lastTickPosX, mc.thePlayer.posY, mc.thePlayer.lastTickPosZ) * 20.0F);
		if(!(Minecraft.getMinecraft().gameSettings.showDebugInfo)) {
			font.drawString("Speed: " + String.valueOf(bps), pos.getAbsoluteX(), pos.getAbsoluteY(), -1, true);
		}
	}
	@Override
	public void renderDummy(ScreenPosition pos) {
		// TODO Auto-generated method stub
		font.drawStringWithShadow("Speed: <Edit Mode>", pos.getAbsoluteX() , pos.getAbsoluteY(), -1);
	}
}
