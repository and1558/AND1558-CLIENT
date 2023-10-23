package uk.to.and1558.Gui.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import uk.to.and1558.Renderer.GuiUtils;

public class RButton extends GuiButton {

    private int radius;
    private int backgroundcolor;

    public RButton(int id, int x, int y, int sx, int sy, int radius, String text) {
        super(id, x, y, sx, sy, text);
        this.radius = radius;
    }

    public RButton(int id, int x, int y, int radius, String text) {
        super(id, x, y, 200, 20, text);
        this.radius = radius;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRendererObj;

            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = this.getHoverState(this.hovered);

            //Transparent Rect
            //0x44000000
            //drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 0x44000000);
            //RoundUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 5, 0x44000000);
            if(!this.hovered)
                GuiUtils.drawRoundedRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, this.radius, 0x99000000);

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);



            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);


            this.mouseDragged(mc, mouseX, mouseY);
            int j = 14737632;

            if (!this.enabled)
            {
                j = 10526880;
            }
            else if (this.hovered)
            {
                drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 0x99000000);
                this.drawHollowRect(this.xPosition-1, this.yPosition-1, this.width+1, this.height+1, 0xFF00FF00);
                j = 16777120;
            }

            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);
            GlStateManager.disableBlend();
        }
    }

    public void drawHollowRect(int x, int y, int w, int h, int color) {
        this.drawHorizontalLine(x, x + w, y, color);
        this.drawHorizontalLine(x, x + w, y + h, color);

        this.drawVerticalLine(x, y + h, y, color);
        this.drawVerticalLine(x + w, y + h, y, color);
    }

    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int mouseX, int mouseY)
    {
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        return this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }

    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver()
    {
        return this.hovered;
    }

    public void drawButtonForegroundLayer(int mouseX, int mouseY)
    {
    }

    public void playPressSound(SoundHandler soundHandlerIn)
    {
        soundHandlerIn.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
    }

    public int getButtonWidth()
    {
        return this.width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }
}
