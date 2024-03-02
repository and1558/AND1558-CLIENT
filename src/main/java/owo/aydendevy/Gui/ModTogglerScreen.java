package owo.aydendevy.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import owo.aydendevy.Gui.impl.GuiCheckBox;
import owo.aydendevy.Mods.ModLoader.ModInstances;
import owo.aydendevy.DevyClient;

import java.io.IOException;

public class ModTogglerScreen extends GuiScreen {
    GuiScreen lastGui;
    boolean check1,check2,check3,check4,check5,check6,check7,check8,check9,check10,check11,check12 = false; // Simplified to 1 straight line [dev-1.82]
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
        this.buttonList.add(new GuiCheckBox(9, 10, 190, check10));
        this.buttonList.add(new GuiCheckBox(11, 10, 210, check11));
        this.buttonList.add(new GuiCheckBox(12, 10, 230, check12));
        this.buttonList.add(new GuiButton(10,10,260, "Back"));
        super.initGui();
    }
    private void checkAll(){
        checkMods(0, ModInstances.getKeystrokes().isEnabled);
        checkMods(1, ModInstances.getSpeedCounter().isEnabled);
        checkMods(2, ModInstances.getPingCounter().isEnabled);
        checkMods(3, ModInstances.getLfire().isEnabled);
        checkMods(4, ModInstances.getOldanim().isEnabled);
        // dev-1.82 -> Actually checks every mod
        checkMods(5, ModInstances.getItemPhysics().isEnabled);
        checkMods(6, ModInstances.getPerspective().isEnabled);
        checkMods(7, ModInstances.getToggleSprint().isEnabled);
        checkMods(8, ModInstances.getArmorView().isEnabled);
        checkMods(9, ModInstances.getOldDebug().isEnabled);
        checkMods(10, ModInstances.getHPDisplay().isEnabled);
        checkMods(12, ModInstances.getComboCounter().isEnabled);
    }
    private void loadAll(){
        check1 = DevyClient.getIO.loadConfig("Keystrokes");
        check2 = DevyClient.getIO.loadConfig("bps");
        check3 = DevyClient.getIO.loadConfig("ping");
        check4 = DevyClient.getIO.loadConfig("lowfire");
        check5 = DevyClient.getIO.loadConfig("oldanimations");
        check6 = DevyClient.getIO.loadConfig("itemPhys");
        check7 = DevyClient.getIO.loadConfig("perspective");
        check8 = DevyClient.getIO.loadConfig("sprinttoggle");
        check9 = DevyClient.getIO.loadConfig("armorview");
        check10 = DevyClient.getIO.loadConfig("oldf3");
        check11 = DevyClient.getIO.loadConfig("hp");
        check12 = DevyClient.getIO.loadConfig("comboCounter");
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
        this.drawString(fontRendererObj, "- Perspective Mod (Hypixel Disabled)", 32, 136, -1); // Working
        this.drawString(fontRendererObj, "- Toggle Sprint (no toggle sneak)", 32, 156, -1); // Partially Working
        this.drawString(fontRendererObj, "- Armor View (Includes Swords)", 32, 176, -1); // Working
        // dev 1.92 -> Added OLD F3 to toggling list
        this.drawString(fontRendererObj, "- 1.7 Version of F3", 32, 196, -1); // Working
        // dev 1.82 -> Added HP to toggling list
        this.drawString(fontRendererObj, "- HP Display", 32, 216, -1); // Working
        this.drawString(fontRendererObj, "- Combo Display", 32, 236, -1); // Working
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        // Change on dev-1.82
        // Simplified the else if statement
        // Instead of if (true) logic; else if(false) logic;
        // Use logic = value ? false : true;
        switch (button.id) {
            case 0: {
                check1 = !check1;
                DevyClient.getIO.saveConfig(check1, "Keystrokes");
                ModInstances.getKeystrokes().isEnabled = check1;
                DevyClient.getInstance().sendNotif("Keystrokes Mod was " + (check1 ? "Enabled" : "Disabled"));
                break;
            }
            case 1:{
                check2 = !check2;
                DevyClient.getIO.saveConfig(check2, "bps");
                ModInstances.getSpeedCounter().isEnabled = check2;
                DevyClient.getInstance().sendNotif("Speed Counter Mod was " + (check2 ? "Enabled" : "Disabled"));
                break;
            }
            case 2:{
                check3 = !check3;
                DevyClient.getIO.saveConfig(check3, "ping");
                ModInstances.getPingCounter().isEnabled = check3;
                DevyClient.getInstance().sendNotif("Ping Counter Mod was " + (check3 ? "Enabled" : "Disabled"));
                break;
            }
            case 3:{
                check4 = !check4;
                DevyClient.getIO.saveConfig(check4, "lowfire");
                ModInstances.getLfire().isEnabled = check4;
                DevyClient.getInstance().sendNotif("Low Fire Mod was " + (check4 ? "Enabled" : "Disabled"));
                break;
            }
            case 4:{
                check5 = !check5;
                DevyClient.getIO.saveConfig(check5, "oldanimations");
                ModInstances.getOldanim().isEnabled = check5;
                DevyClient.getInstance().sendNotif("1.7 Animations Mod was " + (check5 ? "Enabled" : "Disabled"));
                break;
            }
            case 5:{
                check6 = !check6;
                DevyClient.getIO.saveConfig(check6, "itemPhys");
                ModInstances.getItemPhysics().isEnabled = check6;
                DevyClient.getInstance().sendNotif("Item Physics Mod was " + (check6 ? "Enabled" : "Disabled"));
                break;
            }
            case 6:{
                check7 = !check7;
                DevyClient.getIO.saveConfig(check7, "perspective");
                ModInstances.getPerspective().isEnabled = check7;
                DevyClient.getInstance().sendNotif("Perspective Mod was " + (check7 ? "Enabled" : "Disabled"));
                break;
            }
            case 7:{
                check8 = !check8;
                DevyClient.getIO.saveConfig(check8, "sprinttoggle");
                ModInstances.getToggleSprint().isEnabled = check8;
                DevyClient.getInstance().sendNotif("Sprint Toggle Mod was " + (check8 ? "Enabled" : "Disabled"));
                break;
            }
            case 8:{
                check9 = !check9;
                DevyClient.getIO.saveConfig(check9, "armorview");
                ModInstances.getArmorView().isEnabled = check9;
                DevyClient.getInstance().sendNotif("Armor View Mod was " + (check9 ? "Enabled" : "Disabled"));
                break;
            }
            case 9:{
                check10 = !check10;
                DevyClient.getIO.saveConfig(check10,"oldf3");
                ModInstances.getOldDebug().isEnabled = check10;
                DevyClient.getInstance().sendNotif("1.7 F3 Mod was " + (check10 ? "Enabled" : "Disabled"));
                break;
            }
            case 11:{
                check11 = !check11;
                DevyClient.getIO.saveConfig(check11,"hp");
                ModInstances.getHPDisplay().isEnabled = check11;
                DevyClient.getInstance().sendNotif("HP Meter Mod was " + (check11 ? "Enabled" : "Disabled"));
                break;
            }
            case 12:{
                check12 = !check12;
                DevyClient.getIO.saveConfig(check12, "comboCounter");
                ModInstances.getComboCounter().isEnabled = check12;
                DevyClient.getInstance().sendNotif("Combo Counter Mod was " + (check12 ? "Enabled" : "Disabled"));
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
        // Change on dev-1.82
        // Simplified the else if statement
        // See last change for more info
        if(which == 0) {
            check1 = isEnabled;
        }else if(which == 1) {
            check2 = isEnabled;
        }else if(which == 2) {
            check3 = isEnabled;
        }else if(which == 3) {
            check4 = isEnabled;
        }else if(which == 4) {
            check5 = isEnabled;
        }else if(which == 5) {
            check6 = isEnabled;
        }else if(which == 6) {
            check7 = isEnabled;
        }else if(which == 7) {
            check8 = isEnabled;
        }else if(which == 8) {
            check9 = isEnabled;
        }else if(which == 9) {
            check10 = isEnabled;
        }else if(which == 10){
            check11 = isEnabled;
        }else if(which == 11){
            check12 = isEnabled;
        }
    }
    private void checkMods(int which, boolean isEnabled) {
        // Change on dev-1.82
        // Simplified the else if statement
        // See last change for more info
        if(which == 0) {
            check1 = isEnabled ? true : false;
        }else if(which == 1) {
            check2 = isEnabled ? true : false;
        }else if(which == 2) {
            check3 = isEnabled ? true : false;
        }else if(which == 3) {
            check4 = isEnabled ? true : false;
        }else if(which == 4) {
            check5 = isEnabled ? true : false;
        }else if(which == 5) {
            check6 = isEnabled ? true : false;
        }else if(which == 6) {
            check7 = isEnabled ? true : false;
        }else if(which == 7) {
            check8 = isEnabled ? true : false;
        }else if(which == 8) {
            check9 = isEnabled ? true : false;
        }else if(which == 9) {
            check10 = isEnabled ? true : false;
        }else if(which == 9) {
            check11 = isEnabled ? true : false;
        }else if(which == 10){
            check12 = isEnabled ? true : false;
        }
    }
    // Updates screen at a specific amount of time, Regarding the FPS
    @Override
    public void updateScreen() {
        super.updateScreen();
    }
}
