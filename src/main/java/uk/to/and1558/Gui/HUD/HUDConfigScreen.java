package uk.to.and1558.Gui.HUD;

import java.awt.Color;
import java.io.Console;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import uk.to.and1558.Gui.impl.BlurUtils;
import uk.to.and1558.VersionString;
import uk.to.and1558.and1558;

public class HUDConfigScreen extends GuiScreen {

    int i = 0;

    // ADDED FOR SMOOTH DRAGGING

    private float smX, smY;

    private boolean dragged = false;

    protected boolean hovered;


    private final HashMap<IRenderer, ScreenPosition> renderers = new HashMap<IRenderer, ScreenPosition>();

    private Optional<IRenderer> selectedRenderer = Optional.empty();

    private int prevX, prevY;

    @Override
    public void initGui() {

        // modified to add your own buttons <3
        and1558.getInstance().guiUtils.disableDefaultBlur = true;

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }
    public GuiScreen lastScreen = null;
    public HUDConfigScreen(HUDManager api, GuiScreen lastScreen) {

        Collection<IRenderer> registeredRenderers = api.getRegisteredRenderers();
        this.lastScreen = lastScreen;
        for (IRenderer ren : registeredRenderers) {
            if (!ren.isEnabled()) {
                continue;
            }

            ScreenPosition pos = ren.load();
            if (pos == null) {
                pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
            }

            adjustBounds(ren, pos);
            this.renderers.put(ren, pos);
        }

    }
    IRenderer selected = null;
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        if(!dragged){
            ScaledResolution sc = new ScaledResolution(mc);
            if(mc.theWorld != null) BlurUtils.renderBlurredBackground(sc.getScaledWidth(),sc.getScaledHeight(), 0, 0, width, height, 0);
            selected = null;
        }

        if(mc.theWorld == null) {
            super.drawDefaultBackground();
            renderOverlay(and1558.getInstance().partialTicks);
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(VersionString.Ver, 2, 2, -1);
        }
        else super.drawDefaultBackground();
        final float zBackup = this.zLevel;
        this.zLevel = 200;
        for (IRenderer renderer : renderers.keySet()) {
            if(renderer.getHeight() > 0 && renderer.getWidth() > 0) {
                ScreenPosition pos = renderers.get(renderer);
                float absoluteX = pos.getAbsoluteX();
                float absoluteY = pos.getAbsoluteY();
                this.hovered = mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth() && mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight();
                if (!dragged) {
                    this.drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), renderer.getWidth(), renderer.getHeight(), 0x88FFFFFF);
                }
                // START OF SMOOTH DRAGGING
                if (this.hovered) {

                    this.drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), renderer.getWidth(), renderer.getHeight(), 0x8800FF00);
                    Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + renderer.getWidth(), pos.getAbsoluteY() + renderer.getHeight(), 0x3300AA00);
                    if (dragged) {
                        if(selected == null){
                            selected = renderer;
                        }
                        if(selected == renderer) {
                            pos.setAbsolute(pos.getAbsoluteX() + mouseX - this.prevX, pos.getAbsoluteY() + mouseY - this.prevY);

                            adjustBounds(renderer, pos);

                            this.prevX = mouseX;
                            this.prevY = mouseY;
                        }
                    }
                }

                renderer.renderDummy(pos);
                // END OF SMOOTH DRAGGING
                selectedPos = new ScreenPosition(0, 0);
            }
        }
        this.smX = mouseX;
        this.smY = mouseY;

        this.zLevel = zBackup;

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void drawHollowRect(int x, int y, int w, int h, int color) {
        this.drawHorizontalLine(x, x + w, y, color);
        this.drawHorizontalLine(x, x + w, y + h, color);

        this.drawVerticalLine(x, y + h, y, color);
        this.drawVerticalLine(x + w, y + h, y, color);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            renderers.entrySet().forEach((entry) -> {
                entry.getKey().save(entry.getValue());
            });
            this.mc.displayGuiScreen(null);
        }else if (and1558.getInstance().MODPOSGUI.isPressed()) {
            renderers.entrySet().forEach((entry) -> {
                entry.getKey().save(entry.getValue());
            });
            this.mc.displayGuiScreen(null);
        }
    }
    ScreenPosition selectedPos = new ScreenPosition(0,0);
    @Override
    protected void mouseClickMove(int x, int y, int button, long time) {
        if (selectedRenderer.isPresent()) {
            moveSelectedRenderBy(x - prevX, y - prevY);
        }

        this.prevX = x;
        this.prevY = y;
    }

    private void moveSelectedRenderBy(int offsetX, int offsetY) {
        IRenderer renderer = selectedRenderer.get();
        ScreenPosition pos = renderers.get(renderer);

        pos.setAbsolute(pos.getAbsoluteX() + offsetX, pos.getAbsoluteY() + offsetY);

        if (pos.getAbsoluteX() == 0 << 1) {
            pos.setAbsolute(1, pos.getAbsoluteY());
        }

        if (pos.getAbsoluteY() == 0 << 1) {
            pos.setAbsolute(pos.getAbsoluteX(), 1);
        }

        adjustBounds(renderer, pos);
    }

    @Override
    public void onGuiClosed() {
        for (IRenderer renderer : renderers.keySet()) {
            renderer.save(renderers.get(renderer));
        }
        and1558.getInstance().guiUtils.disableDefaultBlur = false;
        //this.mc.displayGuiScreen(lastScreen);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    private void adjustBounds(IRenderer renderer, ScreenPosition pos) {

        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        selectedPos = pos;
        int screenWidth = res.getScaledWidth();
        int screenHeight = res.getScaledHeight();

        int absoluteX = Math.max(0, Math.min(pos.getAbsoluteX(), Math.max(screenWidth - renderer.getWidth(), 0)));
        int absoluteY = Math.max(0, Math.min(pos.getAbsoluteY(), Math.max(screenHeight - renderer.getHeight(), 0)));

        pos.setAbsolute(absoluteX, absoluteY);
    }

    @Override
    protected void mouseClicked(int x, int y, int button) throws IOException {
        this.prevX = x;
        this.prevY = y;

        // NEEDED FOR SMOOTH DRAGGING
        dragged = true;
        mouseHeld = true;

        loadMouseOver(x, y);
        super.mouseClicked(x, y, button);
    }
    boolean mouseHeld = false;
    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {

        // NEEDED FOR SMOOTH DRAGGING
        dragged = false;
        mouseHeld = false;
        super.mouseReleased(mouseX, mouseY, state);
    }

    private void loadMouseOver(int x, int y) {
        this.selectedRenderer = renderers.keySet().stream().filter(new MouseOverFinder(x, y)).findFirst();
    }

    private class MouseOverFinder implements Predicate<IRenderer> {

        private float mouseX, mouseY;

        public MouseOverFinder(int x, int y) {
            this.mouseX = x;
            this.mouseY = y;
        }

        @Override
        public boolean test(IRenderer renderer) {

            ScreenPosition pos = renderers.get(renderer);

            float absoluteX = pos.getAbsoluteX();
            float absoluteY = pos.getAbsoluteY();

            if (mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth()) {

                if (mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight()) {

                    return true;

                }

            }

            return false;
        }

    }

    private void renderOverlay(float partialTicks){
        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        this.mc.entityRenderer.setupOverlayRendering();
        GlStateManager.enableBlend();

        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

        this.renderTooltip(scaledresolution, partialTicks);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(icons);
        GlStateManager.enableBlend();

        GlStateManager.tryBlendFuncSeparate(775, 769, 1, 0);
        GlStateManager.enableAlpha();
        this.drawTexturedModalRect(i / 2 - 7, j / 2 - 7, 0, 0, 16, 16);

        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

        this.renderPlayerStats(scaledresolution);

        GlStateManager.disableBlend();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        int k1 = i / 2 - 91;

        this.renderExpBar(scaledresolution, k1);

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();
        GlStateManager.enableAlpha();
    }
    public void renderExpBar(ScaledResolution scaledRes, int x)
    {
        this.mc.getTextureManager().bindTexture(Gui.icons);
        int i = 7 + 2 * 2;
        int j = 182;
        int k = (int)(0 * (float)(j + 1));
        int l = scaledRes.getScaledHeight() - 32 + 4;
        this.drawTexturedModalRect(x, l, 0, 64, j, 5);

        int k1 = 8453920;
        String s = "" + 69;
        int l1 = (scaledRes.getScaledWidth() - this.fontRendererObj.getStringWidth(s)) / 2;
        int i1 = scaledRes.getScaledHeight() - 31 - 4;
        this.fontRendererObj.drawString(s, l1 + 1, i1, 0);
        this.fontRendererObj.drawString(s, l1 - 1, i1, 0);
        this.fontRendererObj.drawString(s, l1, i1 + 1, 0);
        this.fontRendererObj.drawString(s, l1, i1 - 1, 0);
        this.fontRendererObj.drawString(s, l1, i1, k1);
    }
    int playerHealth = 20;
    private void renderPlayerStats(ScaledResolution scaledRes)
    {
        this.playerHealth = i;
        int i1 = scaledRes.getScaledWidth() / 2 - 91;
        int k1 = scaledRes.getScaledHeight() - 39;
        float f = (float)0;
        float f1 = 20;
        int l1 = MathHelper.ceiling_float_int((f + f1) / 2.0F / 10.0F);
        int i2 = Math.max(10 - (l1 - 2), 3);
        int j2 = k1 - (l1 - 1) * i2 - 10;
        float f2 = f1;
        int k2 = 20;
        int l2 = -1;


        for (int i3 = 0; i3 < 10; ++i3)
        {
            if (k2 > 0)
            {
                int j3 = i1 + i3 * 8;

                if (i3 * 2 + 1 < k2)
                {
                    this.drawTexturedModalRect(j3, j2, 34, 9, 9, 9);
                }

                if (i3 * 2 + 1 == k2)
                {
                    this.drawTexturedModalRect(j3, j2, 25, 9, 9, 9);
                }

                if (i3 * 2 + 1 > k2)
                {
                    this.drawTexturedModalRect(j3, j2, 16, 9, 9, 9);
                }
            }
        }


        for (int i6 = MathHelper.ceiling_float_int((f + f1) / 2.0F) - 1; i6 >= 0; --i6)
        {
            int j6 = 16;

            int k3 = 0;

            int l3 = MathHelper.ceiling_float_int((float)(i6 + 1) / 10.0F) - 1;
            int j1 = scaledRes.getScaledWidth() / 2 + 91;
            int i4 = i1 + i6 % 10 * 8;
            int j4 = k1 - l3 * i2;

            if (i6 == l2)
            {
                j4 -= 2;
            }

            int k4 = 0;

            this.drawTexturedModalRect(i4, j4, 16 + k3 * 9, 9 * k4, 9, 9);

            if (f2 > 0.0F)
            {
                if (f2 == f1 && f1 % 2.0F == 1.0F)
                {
                    this.drawTexturedModalRect(i4, j4, j6 + 153, 9 * k4, 9, 9);
                }
                else
                {
                    this.drawTexturedModalRect(i4, j4, j6 + 144, 9 * k4, 9, 9);
                }

                f2 -= 2.0F;
            }
            else
            {
                if (i6 * 2 + 1 < i)
                {
                    this.drawTexturedModalRect(i4, j4, j6 + 36, 9 * k4, 9, 9);
                }

                if (i6 * 2 + 1 == i)
                {
                    this.drawTexturedModalRect(i4, j4, j6 + 45, 9 * k4, 9, 9);
                }
            }
            this.mc.mcProfiler.endStartSection("food");

            for (int k6 = 0; k6 < 10; ++k6)
            {
                int i7 = k1;
                int l7 = 16;
                int j8 = 0;

                int i9 = j1 - k6 * 8 - 9;
                this.drawTexturedModalRect(i9, i7, 16 + j8 * 9, 27, 9, 9);
                this.drawTexturedModalRect(i9, i7, l7 + 36, 27, 9, 9);

            }
        }
    }
    protected void renderTooltip(ScaledResolution sr, float partialTicks)
    {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/widgets.png"));
            int i = sr.getScaledWidth() / 2;
            float f = this.zLevel;
            this.zLevel = -90.0F;
            this.drawTexturedModalRect(i - 91, sr.getScaledHeight() - 22, 0, 0, 182, 22);
            this.zLevel = f;
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            RenderHelper.enableGUIStandardItemLighting();

            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
    }
}