package uk.to.and1558.Mods;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import uk.to.and1558.Gui.HUD.ScreenPosition;
import uk.to.and1558.Mods.ModLoader.ModDraggable;
import uk.to.and1558.Plugins.RenderRGB;

public class ModKeystrokes extends ModDraggable
{
	private ScreenPosition pos;
	private KeystrokesMode mode;
	private List<Long> clicks;
	private boolean wasPressed;
	private long lastPressed;
	private List<Long> clicks2;
	private boolean wasPressed2;
	private long lastPressed2;

	public ModKeystrokes() {
		this.pos = ScreenPosition.fromRelativePosition(0.4, -0.3);
		this.mode = KeystrokesMode.WASD_JUMP_MOUSE;
		this.clicks = new ArrayList<Long>();
		this.clicks2 = new ArrayList<Long>();
	}

	public void setMode(final KeystrokesMode mode) {
		this.mode = mode;
	}

	@Override
	public int getWidth() {
		return this.mode.getWidth();
	}

	@Override
	public int getHeight() {
		return this.mode.getHeight();
	}

	@Override
	public void render(final ScreenPosition pos) {
		final boolean lpressed = Mouse.isButtonDown(0);
		final boolean rpressed = Mouse.isButtonDown(1);
		if (lpressed != this.wasPressed) {
			this.lastPressed = System.currentTimeMillis() + 10L;
			if (this.wasPressed = lpressed) {
				this.clicks.add(this.lastPressed);
			}
		}
		if (rpressed != this.wasPressed2) {
			this.lastPressed2 = System.currentTimeMillis() + 10L;
			if (this.wasPressed2 = rpressed) {
				this.clicks2.add(this.lastPressed2);
			}
		}
		GL11.glPushMatrix();
		Key[] keys;
		if(!(Minecraft.getMinecraft().gameSettings.showDebugInfo)) {
			for (int length = (keys = this.mode.getKeys()).length, i = 0; i < length; ++i) {
				final Key key = keys[i];
				final int textWidth = this.font.getStringWidth(key.getName());
				Gui.drawRect(pos.getAbsoluteX() + key.getX(), pos.getAbsoluteY() + key.getY(), pos.getAbsoluteX() + key.getX() + key.getWidth(), pos.getAbsoluteY() + key.getY() + key.getHeight(), key.isDown() ? new Color(255, 255, 255, 102).getRGB() : new Color(0, 0, 0, 150).getRGB());
				RenderRGB.drawChromaString(key.getName(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2, pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4, key.isDown());
				if (key.cps) {
					GlStateManager.pushMatrix();
					GlStateManager.scale(0.5f, 0.5f, 0.5f);
					GlStateManager.translate(pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2.0f, pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 + 4.0f, 1.0f);
					if (key.getName().matches(Key.LMB.getName())) {
						RenderRGB.drawChromaString("CPS: " + this.getCPS(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2, pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 + 4, key.isDown());
					}
					if (key.getName().matches(Key.RMB.getName())) {
						RenderRGB.drawChromaString("CPS: " + this.getCPS2(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2, pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 + 4, key.isDown());
					}
					GlStateManager.popMatrix();
				}
			}
		}
		GL11.glPopMatrix();
	}

	@Override
	public void renderDummy(final ScreenPosition pos) {
		GL11.glPushMatrix();
		Key[] keys;
		for (int length = (keys = this.mode.getKeys()).length, i = 0; i < length; ++i) {
			final Key key = keys[i];
			final int textWidth = this.font.getStringWidth(key.getName());
			Gui.drawRect(pos.getAbsoluteX() + key.getX(), pos.getAbsoluteY() + key.getY(), pos.getAbsoluteX() + key.getX() + key.getWidth(), pos.getAbsoluteY() + key.getY() + key.getHeight(), key.isDown() ? new Color(255, 255, 255, 102).getRGB() : new Color(0, 0, 0, 102).getRGB());
			RenderRGB.drawChromaString(key.getName(), pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2, pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4, false);
		}
		GL11.glPopMatrix();
	}

	private int getCPS() {
		final long time = System.currentTimeMillis();
		this.clicks.removeIf(aLong -> aLong + 1000L < time);
		return this.clicks.size();
	}

	private int getCPS2() {
		final long time2 = System.currentTimeMillis();
		this.clicks2.removeIf(aLong2 -> aLong2 + 1000L < time2);
		return this.clicks2.size();
	}

	public enum KeystrokesMode
	{
		WASD("WASD", 0, new Key[] { Key.W, Key.A, Key.S, Key.D }), 
		WASD_MOUSE("WASD_MOUSE", 1, new Key[] { Key.W, Key.A, Key.S, Key.A, Key.LMB, Key.RMB }),
		WASD_JUMP("WASD_JUMP", 2, new Key[] { Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, new Key("------", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 41, 58, 18, false) }),
		WASD_JUMP_MOUSE("WASD_JUMP_MOUSE", 3, new Key[] { Key.W, Key.A, Key.S, Key.LMB, Key.RMB, Key.D, new Key("------", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 61, 58, 18, false) });

		private final Key[] keys;
		private int width;
		private int height;

		private KeystrokesMode(final String name, final int ordinal, final Key... keysIn) {
			this.width = 0;
			this.height = 0;
			this.keys = keysIn;
			Key[] keys;
			for (int length = (keys = this.keys).length, i = 0; i < length; ++i) {
				final Key key = keys[i];
				this.width = Math.max(this.width, key.getX() + key.getWidth());
				this.height = Math.max(this.height, key.getY() + key.getHeight());
			}
		}

		public int getHeight() {
			return this.height;
		}

		public int getWidth() {
			return this.width;
		}

		public Key[] getKeys() {
			return this.keys;
		}
	}

	private static class Key
	{
		private static final Key W;
		private static final Key A;
		private static final Key S;
		private static final Key D;
		private static final Key LMB;
		private static final Key RMB;
		private final String name;
		private final KeyBinding keyBind;
		private final int x;
		private final int y;
		private final int width;
		private final int height;
		private final boolean cps;

		static {
			W = new Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18, false);
			A = new Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18, false);
			S = new Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18, false);
			D = new Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18, false);
			LMB = new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 1, 41, 28, 18, true);
			RMB = new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 31, 41, 28, 18, true);
		}

		public Key(final String name, final KeyBinding keyBind, final int x, final int y, final int width, final int height, final boolean cps) {
			this.name = name;
			this.keyBind = keyBind;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.cps = cps;
		}

		public boolean isDown() {
			return this.keyBind.isKeyDown();
		}

		public int getHeight() {
			return this.height;
		}

		public String getName() {
			return this.name;
		}

		public int getWidth() {
			return this.width;
		}

		public int getX() {
			return this.x;
		}

		public int getY() {
			return this.y;
		}
	}
}
