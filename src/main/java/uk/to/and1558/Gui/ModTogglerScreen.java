package uk.to.and1558.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import uk.to.and1558.Gui.impl.GuiCheckBox;
import uk.to.and1558.Maxytreal123.Configuration;
import uk.to.and1558.Maxytreal123.ConfigurationAPI;
import uk.to.and1558.Mods.ModLoader.ModInstances;
import uk.to.and1558.and1558;

import java.io.File;
import java.io.IOException;

public class ModTogglerScreen extends GuiScreen {
    GuiScreen lastGui;
    boolean check1 = false;
    boolean check2 = false;
    boolean check3 = false;
    boolean check4 = false;
    boolean check5 = false;
    boolean check6 = false;
    boolean check7 = false;
    boolean check8 = false;
    boolean check9 = false;
    boolean check10 = false;
    public ModTogglerScreen(GuiScreen p_i1046_1_)
    {
        this.lastGui = p_i1046_1_;
    }
    public ModTogglerScreen(){

    }
    //Initialize the gui, like rendering the button etc etc
    @Override
    public void initGui() {
        checkAll();
        loadAll();
        this.buttonList.add(new GuiCheckBox(0, 10, 10, check1));
        this.buttonList.add(new GuiCheckBox(1, 10, 30, check2));
        this.buttonList.add(new GuiCheckBox(2, 10, 50, check3));
        this.buttonList.add(new GuiCheckBox(3, 10, 70, check4));
        this.buttonList.add(new GuiCheckBox(4, 10, 90, check5));
        this.buttonList.add(new GuiCheckBox(5, 10, 110, check6));
        this.buttonList.add(new GuiCheckBox(6, 10, 130, check7));
        this.buttonList.add(new GuiCheckBox(7, 10, 150, check8));
        this.buttonList.add(new GuiCheckBox(8, 10, 170, check9));
        this.buttonList.add(new GuiButton(10,10,210, "Back"));
        super.initGui();
    }
    private void checkAll(){
        checkMods(0, ModInstances.getKeystrokes().isEnabled);
        checkMods(1, ModInstances.getSpeedCounter().isEnabled);
        checkMods(2, ModInstances.getPingCounter().isEnabled);
        checkMods(3, ModInstances.getLfire().isEnabled);
        checkMods(4, ModInstances.getOldanim().isEnabled);
        checkMods(8, ModInstances.getArmorView().isEnabled);
    }
    private void loadAll(){
        check1 = and1558.getIO.loadConfig("Keystrokes");
        check2 = and1558.getIO.loadConfig("bps");
        check3 = and1558.getIO.loadConfig("ping");
        check4 = and1558.getIO.loadConfig("lowfire");
        check5 = and1558.getIO.loadConfig("oldanimations");
        check6 = and1558.getIO.loadConfig("itemPhys");
        check7 = false;
        check8 = and1558.getIO.loadConfig("sprinttoggle");
        check9 = and1558.getIO.loadConfig("armorview");
    }

    //Re-draws/draws screen, Depends on FPS
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawString(fontRendererObj, "- Keystrokes", 32, 16, -1); // Done
        this.drawString(fontRendererObj, "- Speed", 32, 36, -1); // Done, Renamed
        this.drawString(fontRendererObj, "- Ping", 32, 56, -1); // Done
        this.drawString(fontRendererObj, "- LowFire", 32, 76, -1); // Done
        this.drawString(fontRendererObj, "- Old Animations (1.7 Additions) (Partially Working)", 32, 96, -1); // Partially Working
        this.drawString(fontRendererObj, "- Item Physics [Now Toggleable!]", 32, 116, -1); // Broken
        this.drawString(fontRendererObj, "- Perspective Mod (Hypixel Disabled)", 32, 136, -1); // Janky
        this.drawString(fontRendererObj, "- Toggle Sprint (no toggle sneak)", 32, 156, -1); // Partially Working
        this.drawString(fontRendererObj, "- Armor View (Includes Swords)", 32, 176, -1); //
        super.drawScreen(mouseX, mouseY, partialTicks);
    }@Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        switch (button.id) {
            case 0: {
                if(check1 == false) {
                    check1 = true;
                    and1558.getIO.saveConfig(true, "Keystrokes");
                    ModInstances.getKeystrokes().isEnabled = true;
                }else {
                    check1 = false;
                    and1558.getIO.saveConfig(false, "Keystrokes");
                    ModInstances.getKeystrokes().isEnabled = false;
                }
                break;
            }
            case 1:{
                if(check2 == false){
                    check2 = true;
                    and1558.getIO.saveConfig(true, "bps");
                    ModInstances.getSpeedCounter().isEnabled = true;
                }else{
                    check2 = false;
                    and1558.getIO.saveConfig(false, "bps");
                    ModInstances.getSpeedCounter().isEnabled = false;
                }
                break;
            }
            case 2:{
                if(check3 == false){
                    check3 = true;
                    and1558.getIO.saveConfig(true, "ping");
                    ModInstances.getPingCounter().isEnabled = true;
                }else{
                    check3 = false;
                    and1558.getIO.saveConfig(false, "ping");
                    ModInstances.getPingCounter().isEnabled = false;
                }
                break;
            }
            case 3:{
                if(check4 == false){
                    check4 = true;
                    and1558.getIO.saveConfig(true, "lowfire");
                    ModInstances.getLfire().isEnabled = true;
                }else{
                    check4 = false;
                    and1558.getIO.saveConfig(false, "lowfire");
                    ModInstances.getLfire().isEnabled = false;
                }
                break;
            }
            case 4:{
                if(check5 == false){
                    check5 = true;
                    and1558.getIO.saveConfig(true, "oldanimations");
                    ModInstances.getOldanim().isEnabled = true;
                }else{
                    check5 = false;
                    and1558.getIO.saveConfig(false, "oldanimations");
                    ModInstances.getOldanim().isEnabled = false;
                }
                break;
            }
            case 5:{
                if(check6 == false){
                    check6 = true;
                    and1558.getIO.saveConfig(true, "itemPhys");
                    ModInstances.getItemPhysics().isEnabled = true;
                }else{
                    check6 = false;
                    and1558.getIO.saveConfig(false, "itemPhys");
                    ModInstances.getItemPhysics().isEnabled = false;
                }
            }
            case 6:{
                if(check7 == false){
                    check7 = true;
                    and1558.getIO.saveConfig(true, "perspective");
                    ModInstances.getToggleSprint().isEnabled = true;
                }else{
                    check7 = false;
                    and1558.getIO.saveConfig(false, "perspective");
                    ModInstances.getToggleSprint().isEnabled = false;
                }
                break;
            }
            case 7:{
                if(check8 == false){
                    check8 = true;
                    and1558.getIO.saveConfig(true, "sprinttoggle");
                    ModInstances.getToggleSprint().isEnabled = true;
                }else{
                    check8 = false;
                    and1558.getIO.saveConfig(false, "sprinttoggle");
                    ModInstances.getToggleSprint().isEnabled = false;
                }
                break;
            }
            case 8:{
                if(check9 == false){
                    check9 = true;
                    and1558.getIO.saveConfig(true, "armorview");
                    ModInstances.getArmorView().isEnabled = true;
                }else{
                    check9 = false;
                    and1558.getIO.saveConfig(false, "armorview");
                    ModInstances.getArmorView().isEnabled = false;
                }
                break;
            }
            case 10:{
                this.mc.displayGuiScreen(lastGui);
                break;
            }
        }
        super.actionPerformed(button);
    }
    public void setEnable(int which, boolean isEnabled) {
        // Below is for enabled
        if(which == 0 && isEnabled) {
            check1 = true;
        }else if(which == 1 && isEnabled) {
            check2 = true;
        }else if(which == 2 && isEnabled) {
            check3 = true;
        }else if(which == 3 && isEnabled) {
            check4 = true;
        }else if(which == 4 && isEnabled) {
            check5 = true;
        }else if(which == 5 && isEnabled) {
            check6 = true;
        }else if(which == 6 && isEnabled) {
            check7 = true;
        }else if(which == 7 && isEnabled) {
            check8 = true;
        }else if(which == 8 && isEnabled) {
            check9 = true;
        }else if(which == 9 && isEnabled) {
            check10 = true;
        }else // Below is for disabled
            if(which == 0 && !isEnabled) {
                check1 = false;
            }else if(which == 1 && !isEnabled) {
                check2 = false;
            }else if(which == 2 && !isEnabled) {
                check3 = false;
            }else if(which == 3 && !isEnabled) {
                check4 = false;
            }else if(which == 4 && !isEnabled) {
                check5 = false;
            }else if(which == 5 && !isEnabled) {
                check6 = false;
            }else if(which == 6 && !isEnabled) {
                check7 = false;
            }else if(which == 7 && !isEnabled) {
                check8 = false;
            }else if(which == 8 && !isEnabled) {
                check9 = false;
            }else if(which == 9 && !isEnabled) {
                check10 = false;
            }
    }
    private void checkMods(int which, boolean isEnabled) {
        if(which == 0 && isEnabled) {
            check1 = true;
        }else if(which == 1 && isEnabled) {
            check2 = true;
        }else if(which == 2 && isEnabled) {
            check3 = true;
        }else if(which == 3 && isEnabled) {
            check4 = true;
        }else if(which == 4 && isEnabled) {
            check5 = true;
        }else if(which == 5 && isEnabled) {
            check6 = true;
        }else if(which == 6 && isEnabled) {
            check7 = true;
        }else if(which == 7 && isEnabled) {
            check8 = true;
        }else if(which == 8 && isEnabled) {
            check9 = true;
        }else if(which == 9 && isEnabled) {
            check10 = true;
        }else // Below is for disabled
            if(which == 0 && !isEnabled) {
                check1 = false;
            }else if(which == 1 && !isEnabled) {
                check2 = false;
            }else if(which == 2 && !isEnabled) {
                check3 = false;
            }else if(which == 3 && !isEnabled) {
                check4 = false;
            }else if(which == 4 && !isEnabled) {
                check5 = false;
            }else if(which == 5 && !isEnabled) {
                check6 = false;
            }else if(which == 6 && !isEnabled) {
                check7 = false;
            }else if(which == 7 && !isEnabled) {
                check8 = false;
            }else if(which == 8 && !isEnabled) {
                check9 = false;
            }else if(which == 9 && !isEnabled) {
                check10 = false;
            }
    }
    // Updates screen at a specific amount of time, Regarding the FPS
    @Override
    public void updateScreen() {
        super.updateScreen();
    }
}
