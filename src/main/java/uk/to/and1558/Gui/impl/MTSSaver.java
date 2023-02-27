package uk.to.and1558.Gui.impl;

import uk.to.and1558.Mods.ModLoader.ModInstances;
import uk.to.and1558.and1558;

public class MTSSaver {
    public static void save(int which, boolean val){
        switch (which) {
            case 0: {
                if(val == false) {
                    and1558.getIO.saveConfig(true, "Keystrokes");
                }else {
                    and1558.getIO.saveConfig(false, "Keystrokes");
                }
                break;
            }
            case 1:{
                if(val == false){
                    and1558.getIO.saveConfig(true, "bps");
                }else{
                    and1558.getIO.saveConfig(false, "bps");
                }
                break;
            }
            case 2:{
                if(val == false){
                    and1558.getIO.saveConfig(true, "ping");
                }else{
                    and1558.getIO.saveConfig(false, "ping");
                }
                break;
            }
            case 3:{
                if(val == false){
                    and1558.getIO.saveConfig(true, "lowfire");
                }else{
                    and1558.getIO.saveConfig(false, "lowfire");
                }
                break;
            }
            case 4:{
                if(val == false){
                    and1558.getIO.saveConfig(true, "oldanimations");
                }else{
                    val = false;
                    and1558.getIO.saveConfig(false, "oldanimations");
                }
                break;
            }
            case 5:{
                if(val == false){
                    and1558.getIO.saveConfig(true, "itemPhys");
                }else{
                    and1558.getIO.saveConfig(false, "itemPhys");
                }
            }
            case 6:{
                if(val == false){
                    and1558.getIO.saveConfig(true, "perspective");
                }else{
                    and1558.getIO.saveConfig(false, "perspective");
                }
                break;
            }
            case 7:{
                if(val == false){
                    and1558.getIO.saveConfig(true, "sprinttoggle");
                }else{
                    and1558.getIO.saveConfig(false, "sprinttoggle");
                }
                break;
            }
            case 8:{
                if(val == false){
                    and1558.getIO.saveConfig(true, "armorview");
                }else{
                    and1558.getIO.saveConfig(false, "armorview");
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
