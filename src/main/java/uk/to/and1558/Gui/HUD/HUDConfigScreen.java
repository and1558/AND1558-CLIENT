package uk.to.and1558.Gui.HUD;

import java.awt.Color;
import java.io.Console;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

import org.lwjgl.input.Keyboard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import uk.to.and1558.Gui.impl.BlurUtils;
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
            BlurUtils.renderBlurredBackground(sc.getScaledWidth(),sc.getScaledHeight(), 0, 0, width, height, 0);
            selected = null;
        }
        super.drawDefaultBackground();
        if(mc.theWorld == null) super.drawDefaultBackground();
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

}