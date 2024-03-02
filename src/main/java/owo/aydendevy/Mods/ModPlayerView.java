package owo.aydendevy.Mods;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import owo.aydendevy.Gui.HUD.ScreenPosition;
import owo.aydendevy.Mods.ModLoader.ModDraggable;

public class ModPlayerView extends ModDraggable {
	/* Thanks to BlueBloxCraft for this :D
	   Note: i modified some of it */
	private ScreenPosition pos;
	@Override
	public int getWidth() {
		return 20;
	}

	@Override
	public int getHeight() {
		return 50;
	}
	float bps; 
	@Override
	public void render(ScreenPosition pos) {
		// Below this is the old PaperDoll Code
		/*GL11.glColor4f(1, 1, 1, 1);
    	if(mc.thePlayer.isSneaking() || mc.thePlayer.isSprinting() || mc.thePlayer.isEating()) {
        GuiInventory.drawEntityOnScreen(pos.getAbsoluteX()  + 10, pos.getAbsoluteY() + 45, 20, yawhead, mc.thePlayer.rotationPitch, mc.thePlayer);
    	}*/
		// New PaperDoll Code
		EntityLivingBase ent = Minecraft.getMinecraft().thePlayer;
		ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());

		// enable rendering (transform)
		GlStateManager.enableColorMaterial();
		GlStateManager.pushMatrix();
		GlStateManager.translate(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 45, 50.0F);
		GlStateManager.scale(20, 20, 20);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		GL11.glColor4f(1, 1, 1, 1);
		// store og values
		float f = ent.renderYawOffset;
		float f1 = ent.rotationYaw;
		float f2 = ent.rotationPitch;
		float f3 = ent.prevRotationYawHead;
		float f4 = ent.rotationYawHead;
		GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-((float) Math.atan((double) (15 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		// 25.740044 and -25.740044
		// assign temp values for rendering
		// render the player
		RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
		rendermanager.setPlayerViewY(180.0F);
		rendermanager.setRenderShadow(false);
		rendermanager.renderEntityWithPosYaw(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		rendermanager.setRenderShadow(true);

		// reassign values
		ent.renderYawOffset = f;
		ent.rotationYaw = f1;
		ent.rotationPitch = f2;
		ent.prevRotationYawHead = f3;
		ent.rotationYawHead = f4;

		// return back to normal rendering
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.disableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);

	}
	@Override
	public void renderDummy(ScreenPosition pos) {
		EntityLivingBase ent = Minecraft.getMinecraft().thePlayer;
		ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());

		// enable rendering (transform)
		GlStateManager.enableColorMaterial();
		GlStateManager.pushMatrix();
		GlStateManager.translate(pos.getAbsoluteX() + 10, pos.getAbsoluteY() + 45, 50.0F);
		GlStateManager.scale(20, 20, 20);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
		GL11.glColor4f(1, 1, 1, 1);
		// store og values
		float f = ent.renderYawOffset;
		float f1 = ent.rotationYaw;
		float f2 = ent.rotationPitch;
		float f3 = ent.prevRotationYawHead;
		float f4 = ent.rotationYawHead;

		GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-((float) Math.atan((double) (15 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);

		// assign temp values for rendering
		ent.renderYawOffset = (float) Math.atan(30 / 40.0F) * -20.0F;
		ent.rotationYaw = (float) Math.atan(30 / 40.0F) * -40.0F;
		if (pos.getAbsoluteX() < resolution.getScaledWidth() / 2) {
			ent.renderYawOffset = (float) Math.atan(30 / 40.0F) * 20.0F;
			ent.rotationYaw = (float) Math.atan(30 / 40.0F) * 40.0F;
		}
		ent.rotationPitch = 0;
		ent.rotationYawHead = ent.rotationYaw;
		ent.prevRotationYawHead = ent.rotationYaw;

		// render the player
		RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
		rendermanager.setPlayerViewY(180.0F);
		rendermanager.setRenderShadow(false);
		rendermanager.renderEntityWithPosYaw(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		rendermanager.setRenderShadow(true);

		// reassign values
		ent.renderYawOffset = f;
		ent.rotationYaw = f1;
		ent.rotationPitch = f2;
		ent.prevRotationYawHead = f3;
		ent.rotationYawHead = f4;

		// return back to normal rendering
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GlStateManager.disableTexture2D();
		GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
}
