package owo.aydendevy.Gui.impl;

import owo.aydendevy.DevyClient;
// Unused!
// Testing a new saving mechanism
public class MTSSaver {
    public static void save(int which, boolean val){
        switch (which) {
            case 0: {
                if(val == false) {
                    DevyClient.getIO.saveConfig(true, "Keystrokes");
                }else {
                    DevyClient.getIO.saveConfig(false, "Keystrokes");
                }
                break;
            }
            case 1:{
                if(val == false){
                    DevyClient.getIO.saveConfig(true, "bps");
                }else{
                    DevyClient.getIO.saveConfig(false, "bps");
                }
                break;
            }
            case 2:{
                if(val == false){
                    DevyClient.getIO.saveConfig(true, "ping");
                }else{
                    DevyClient.getIO.saveConfig(false, "ping");
                }
                break;
            }
            case 3:{
                if(val == false){
                    DevyClient.getIO.saveConfig(true, "lowfire");
                }else{
                    DevyClient.getIO.saveConfig(false, "lowfire");
                }
                break;
            }
            case 4:{
                if(val == false){
                    DevyClient.getIO.saveConfig(true, "oldanimations");
                }else{
                    val = false;
                    DevyClient.getIO.saveConfig(false, "oldanimations");
                }
                break;
            }
            case 5:{
                if(val == false){
                    DevyClient.getIO.saveConfig(true, "itemPhys");
                }else{
                    DevyClient.getIO.saveConfig(false, "itemPhys");
                }
            }
            case 6:{
                if(val == false){
                    DevyClient.getIO.saveConfig(true, "perspective");
                }else{
                    DevyClient.getIO.saveConfig(false, "perspective");
                }
                break;
            }
            case 7:{
                if(val == false){
                    DevyClient.getIO.saveConfig(true, "sprinttoggle");
                }else{
                    DevyClient.getIO.saveConfig(false, "sprinttoggle");
                }
                break;
            }
            case 8:{
                if(val == false){
                    DevyClient.getIO.saveConfig(true, "armorview");
                }else{
                    DevyClient.getIO.saveConfig(false, "armorview");
                }
                break;
            }
            default: {
                System.out.println("MOD WITH ID " + which + "COULD NOT BE FOUND");
                System.out.println("FAILED TO GET MOD ID");
                throw new NullPointerException("THERE IS NO MOD WITH THIS ID!");
            }
        }
    }
}
