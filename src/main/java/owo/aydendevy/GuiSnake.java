package owo.aydendevy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class GuiSnake extends GuiScreen {
    int moveSpeed = 10;
    int growth = 1;
    boolean isSnakeDead = false;
    int foodX = 0,foodY = 0;
    int gameWindowSize = 128;
    int distanceFromEdge = 128;
    boolean isFoodAvailable = false;
    ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    @Override
    public void initGui() {

        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        distanceFromEdge = this.mc.displayHeight / 10;
        DevyClient.drawClientBackground(DevyClient.options.darkMode, this.width, this.height);
        // Render game Window
        Gui.drawRect(distanceFromEdge, distanceFromEdge,  distanceFromEdge + gameWindowSize * mc.gameSettings.guiScale, distanceFromEdge + gameWindowSize * mc.gameSettings.guiScale, 0x87000000);
        // drawFood(distanceFromEdge + 10,distanceFromEdge + 10);
        spawnFood();
        drawFood(foodX,foodY);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    void drawFood(int x, int y){
        Gui.drawRect(x,y, x + 10,y + 10, 0xFFFFFFFF);
    }
    void spawnFood(){
        
        Random rand = new Random();
        foodX = distanceFromEdge + rand.nextInt(gameWindowSize * mc.gameSettings.guiScale);
        foodY = distanceFromEdge + rand.nextInt(gameWindowSize * mc.gameSettings.guiScale);
        isFoodAvailable = true;
    }
}

