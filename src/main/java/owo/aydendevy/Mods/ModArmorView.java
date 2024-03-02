package owo.aydendevy.Mods;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import owo.aydendevy.Gui.HUD.ScreenPosition;
import owo.aydendevy.Mods.ModLoader.ModDraggable;

public class ModArmorView extends ModDraggable {
	private ScreenPosition pos = ScreenPosition.fromRelativePosition(0.5, 0.5);

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 84;
	}

	@Override
	public void render(ScreenPosition pos) {
		// TODO Auto-generated method stub
		if(!(Minecraft.getMinecraft().gameSettings.showDebugInfo)) {
			for(int i = 0;i < mc.thePlayer.inventory.armorInventory.length; i++) {
				ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
				renderItemStack(pos, i, itemStack);
			}
			ItemStack is = mc.thePlayer.inventory.getCurrentItem();
			// Move the sword when a armor slot is empty
			if(is == null) {
				return;
			}else if(is.getItem().isDamageable()) {
				renderItemStack(pos, 4, is);
			}
			
		}
	}
	@Override
	public void renderDummy(ScreenPosition pos) {
		// TODO Auto-generated method stub
		renderItemStack(pos, 4, new ItemStack(Items.diamond_sword));
		renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
		renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
		renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
		renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));
	}
	private void renderItemStack(ScreenPosition pos, int i, ItemStack is) {
		// TODO Auto-generated method stub
		if(is == null) {
			return;
		}
		GL11.glPushMatrix();
		int yAdd = (-16 * i) + 48;
		if(is.getItem().isDamageable()) {
			double damage = ((is.getMaxDamage() - is.getItemDamage()) / (double) is.getMaxDamage()) * 100;
			font.drawString(String.format("%.2f%%", damage), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 22, -1);
		}
		RenderHelper.enableGUIStandardItemLighting();
		mc.getRenderItem().renderItemAndEffectIntoGUI(is, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + yAdd + 19);
		GL11.glPopMatrix();
	}
}
