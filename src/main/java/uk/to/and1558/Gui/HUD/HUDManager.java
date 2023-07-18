package uk.to.and1558.Gui.HUD;

import net.minecraft.client.*;
import com.google.common.collect.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import uk.to.and1558.Events.EventManager;
import uk.to.and1558.Events.EventTarget;
import uk.to.and1558.Events.impl.RenderEvent;
import uk.to.and1558.Gui.ModScreen;

import java.util.*;

public class HUDManager
{
    private static HUDManager instance;
    private Set<IRenderer> registeredRenderers;
    private Minecraft mc;
    
    static {
        HUDManager.instance = null;
    }
    
    private HUDManager() {
        this.registeredRenderers = Sets.newHashSet();
        this.mc = Minecraft.getMinecraft();
    }
    
    public static HUDManager getInstance() {
        if (HUDManager.instance != null) {
            return HUDManager.instance;
        }
        EventManager.register(HUDManager.instance = new HUDManager());
        return HUDManager.instance;
    }
    
    public void register(final IRenderer... renderers) {
        for (final IRenderer render : renderers) {
            this.registeredRenderers.add(render);
        }
    }
    
    public void unregister(final IRenderer... renderers) {
        for (final IRenderer render : renderers) {
            this.registeredRenderers.remove(render);
        }
    }
    
    public Collection<IRenderer> getRegisteredRenderers() {
        return (Collection<IRenderer>)Sets.newHashSet((Iterable)this.registeredRenderers);
    }
    
    public void openMenuScreen() {
        this.mc.displayGuiScreen(new ModScreen());
    }
    public void openConfigScreen(GuiScreen lastScreen) {
        this.mc.displayGuiScreen(new HUDConfigScreen(this, lastScreen));
    }
    
    @EventTarget
    public void onRender(final RenderEvent e) {
        if (this.mc.currentScreen == null || this.mc.currentScreen instanceof GuiContainer || this.mc.currentScreen instanceof GuiChat) {
            for (final IRenderer renderer : this.registeredRenderers) {
                this.callRenderer(renderer);
            }
        }
    }
    
    private void callRenderer(final IRenderer renderer) {
        if (!renderer.isEnabled()) {
            return;
        }
        ScreenPosition pos = renderer.load();
        if (pos == null) {
            pos = ScreenPosition.fromRelativePosition(1.5, 1.5);
        }
        // Disable HUD Mod from being visible when another menu opens
        if(mc.currentScreen == null)
            renderer.render(pos);
    }
}
