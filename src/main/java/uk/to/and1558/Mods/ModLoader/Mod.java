package uk.to.and1558.Mods.ModLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import uk.to.and1558.Events.EventManager;
import uk.to.and1558.and1558;

public class Mod {

    public boolean isEnabled = true;

    protected final Minecraft mc;
    protected final FontRenderer font;
    protected final and1558 client;
    public Mod(){
        this.mc = Minecraft.getMinecraft();
        this.font = this.mc.fontRendererObj;
        this.client = and1558.getInstance();

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
