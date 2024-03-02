package owo.aydendevy.Mods.ModLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import owo.aydendevy.Events.EventManager;
import owo.aydendevy.DevyClient;

public class Mod {

    public boolean isEnabled = true;

    protected final Minecraft mc;
    protected final FontRenderer font;
    protected final DevyClient client;
    public Mod(){
        this.mc = Minecraft.getMinecraft();
        this.font = this.mc.fontRendererObj;
        this.client = DevyClient.getInstance();

        setEnabled(isEnabled);
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        if(isEnabled){
            EventManager.register(this);
        }else{
            EventManager.unregister(this);
        }
    }
    public boolean isEnabled(){
        return isEnabled;
    }
}
