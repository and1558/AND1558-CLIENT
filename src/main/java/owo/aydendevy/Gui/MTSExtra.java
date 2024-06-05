package owo.aydendevy.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import owo.aydendevy.Gui.impl.GuiCheckBox;
import owo.aydendevy.Gui.impl.GuiUtils;
import owo.aydendevy.DevyClient;
import owo.aydendevy.Renderer.GLUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class MTSExtra extends GuiScreen {
    GuiCheckBox CblurEffect,CblurEffectGui;
    GuiButton openCBackground;
    @Override
    public void initGui() {
        this.buttonList.add(new GuiCheckBox(0, 26, 60, DevyClient.options.darkMode));
        this.buttonList.add(new GuiCheckBox(6, 26, 80, DevyClient.options.customBackground));
        this.buttonList.add(openCBackground = new GuiButton(7, 46, 80, 20, 20, "..."));
        this.buttonList.add(new GuiCheckBox(1, 26, 140, DevyClient.options.snowParticles));
        this.buttonList.add(new GuiCheckBox(2, 26, 160, DevyClient.options.snowParticlesGUI));
        this.buttonList.add(CblurEffect = new GuiCheckBox(3, 26, 220, DevyClient.options.blurEffect));
        this.buttonList.add(CblurEffectGui = new GuiCheckBox(4, 26, 240, DevyClient.options.blurEffectGUI));
        this.buttonList.add(new GuiCheckBox(5, 26, 260, DevyClient.options.mcBlurMethod));
        if(DevyClient.options.mcBlurMethod){
            CblurEffect.enabled = false;
            CblurEffectGui.enabled = false;
        }else{
            CblurEffect.enabled = true;
            CblurEffectGui.enabled = true;
        }
        super.initGui();
    }
    //SliderElement slider = new SliderElement((int) 100, (int) 60, (int) 100, (int) 16, (float) 0, (float) 100, (float) 50);
    String debugPath = "";
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GuiUtils.drawRoundBG(this, true);
        //drawCenteredString(fontRendererObj, "Extra Configurations", this.width / 2, 20,-1);
        drawCenteredString(fontRendererObj, debugPath, this.width / 2, 20,-1);
        drawString(fontRendererObj, "-------Client Background-------", 26, 40, -1);
        drawString(fontRendererObj, "- Force Seasonal Background Dark Mode", 49, 66, -1);
        drawString(fontRendererObj, "- Set Custom Background", 69, 86, -1);
        drawString(fontRendererObj, "-------Seasons Specific-------", 26, 120, -1);
        drawString(fontRendererObj, "- Snow Particles", 49, 146, -1);
        drawString(fontRendererObj, "- Snow Particles on Vanilla GUIs", 49, 166, -1);
        drawString(fontRendererObj, "----------GUI Effects---------", 26, 200, -1);
        drawString(fontRendererObj, "- Background Blur", 49, 226, -1);
        drawString(fontRendererObj, "- Background Blur on Vanilla GUIs", 49, 246, -1);
        drawString(fontRendererObj, "- Use Minecraft Default Blur Shader (faster on low-end PCs)", 49, 266, -1);
        //slider.draw();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        //if(slider.mouseOverlap(mouseX, mouseY)) slider.interact(mouseX, mouseY);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    private boolean executeOnce = false;
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case 0:{
                DevyClient.options.darkMode = !DevyClient.options.darkMode;
                DevyClient.options.save();
                DevyClient.options.load();
                break;
            }
            case 1:{
                DevyClient.options.snowParticles = !DevyClient.options.snowParticles;
                DevyClient.options.save();
                DevyClient.options.load();
                break;
            }
            case 2:{
                DevyClient.options.snowParticlesGUI = !DevyClient.options.snowParticlesGUI;
                DevyClient.options.save();
                DevyClient.options.load();
                break;
            }
            case 3:{
                DevyClient.options.blurEffect = !DevyClient.options.blurEffect;
                DevyClient.options.save();
                DevyClient.options.load();
                break;
            }case 4:{
                DevyClient.options.blurEffectGUI = !DevyClient.options.blurEffectGUI;
                DevyClient.options.save();
                DevyClient.options.load();
                break;
            }case 5:{
                DevyClient.options.mcBlurMethod = !DevyClient.options.mcBlurMethod;
                if(DevyClient.options.mcBlurMethod){
                    CblurEffect.enabled = false;
                    CblurEffectGui.enabled = false;
                }else{
                    CblurEffect.enabled = true;
                    CblurEffectGui.enabled = true;
                }
                DevyClient.options.save();
                DevyClient.options.load();
                break;
            } case 6:{
                DevyClient.options.customBackground = !DevyClient.options.customBackground;
                if(DevyClient.options.customBackground){
                    openCBackground.enabled = true;
                }else{
                    openCBackground.enabled = false;
                }
                DevyClient.options.save();
                DevyClient.options.load();
                break;
            } case 7:{
                JFileChooser fileChooser = new JFileChooser();
                File selectedFile = null;
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                fileChooser.setFileFilter(new FileNameExtensionFilter("PNG File","png"));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPG File","jpg"));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG File","jpeg"));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    debugPath = selectedFile.getPath();
                    DevyClient.options.customBackgroundPath = debugPath;
                    DevyClient.getInstance().glimg = new GLUtils();
                    DevyClient.options.save();
                    DevyClient.options.load();
                } else {
                    //confirmExit();
                    break;
                }
            }
        }
        super.actionPerformed(button);
    }
}
