package uk.to.and1558.Gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

public class GuiCredits extends GuiScreen{
    int x = 50;
    int y = 510;
    int y1 = 510;
    int y2 = 510;
    int y3 = 510;
    int y4 = 510;
    int y5 = 510;
    int y6 = 510;
    int y7 = 510;
    int y8 = 510;
    int y9 = 510;
    int y10 = 510;
    int y11 = 510;
    int y12 = 510;
    int speed = 32;
    int speed1 = 32;
    int speed2 = 32;
    int speed3 = 32;
    int speed4 = 32;
    int speed5 = 32;
    int speed6 = 32;
    int speed7 = 32;
    int speed8 = 32;
    int speed9 = 32;
    int speed10 = 32;
    int speed11 = 32;
    int speed12 = 32;
    //int y = 180;
    //int ymin = -250;
    //int ymax = 10;

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(0, this.width / 2 - x, this.height / 2 + 68, 98, 20, "Exit to Main Menu"));
    }
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
		/*if(y > 10)
			y--;
		if(y < 400) {
			if(y > 200) {
				if(x < 270) {
					x += 3;
					this.buttonList.clear();
					this.buttonList.add(new GuiButton(0, this.width / 2 - x, this.height / 2 + 68, 98, 20, "Exit to Main Menu"));
					System.out.println(y + " = Y");
					System.out.println(x + " = X");
				}
			}
		}
		if(y < 180) {
			if(x > 50) {
				x -= 3;
				this.buttonList.clear();
				this.buttonList.add(new GuiButton(0, this.width / 2 - x, this.height / 2 + 68, 98, 20, "Exit to Main Menu"));
			}
		}*/
        if(y > 14) {
            speed--;
            y -= speed;
        }
        if(y1 > 14){
            speed1--;
            y1 -= speed1;
        }
        if(y2 > 14){
            speed2--;
            y2 -= speed2;
        }
        if(y3 > 14){
            speed3--;
            y3 -= speed3;
        }
        if(y4 > 14){
            speed4--;
            y4 -= speed4;
        }
        if(y5 > 14){
            speed5--;
            y5 -= speed5;
        }
        if(y6 > 14){
            speed6--;
            y6 -= speed6;
        }
        if(y7 > 14){
            speed7--;
            y7 -= speed7;
        }
        if(y8 > 14){
            speed8--;
            y8 -= speed8;
        }
        if(y9 > 14){
            speed9--;
            y9 -= speed9;
        }
        if(y10 > 14){
            speed10--;
            y10 -= speed10;
        }if(y11 > 14){
            speed11--;
            y11 -= speed11;
        }
        if(y12 > 14){
            speed12--;
            y12 -= speed12;
        }
        this.drawCenteredString(this.mc.fontRendererObj,"AND1558 Client Credits", this.width / 2 - 2, y, -1);
        this.drawCenteredString(fontRendererObj, "§f§lDevelopers:", this.width / 2 - 2, y1 + 50, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Mojang Developers", this.width / 2 - 2, y2 + 70, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Eric Golde (also made the splash background art)", this.width / 2 - 2, y3 + 80, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Si1kn", this.width / 2 - 2, y4 + 90, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Hyperium Client Devs", this.width / 2 - 2, y5 + 100, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Maxytreal123", this.width / 2 - 2, y6 + 110, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "BananikXenos", this.width / 2 - 2, y7 + 120, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Simulatan", this.width / 2 - 2, y8 + 130, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "BlueBloxCraft", this.width / 2 - 2, y9 + 140, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "asbyth", this.width / 2 - 2, y10 + 150, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Gavin[#4385]<-DISCORD" ,this.width / 2 - 2, y10 + 160, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Canelex[Perspective Mod v3] and DjTheRedstoner[Perspective Mod v4]" ,this.width / 2 - 2, y11 + 170, 0);
        this.drawCenteredString(fontRendererObj, EnumChatFormatting.WHITE + "Open Source Mods", this.width / 2 - 2, y12 + 180, 0);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        switch (button.id) {
            case 0: {
                this.mc.displayGuiScreen(new MainMenu());
                break;
            }
        }
        super.actionPerformed(button);
    }

}
