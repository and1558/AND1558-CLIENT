package uk.to.and1558.mixins.client;

import jdk.jfr.internal.tool.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.to.and1558.Gui.MainMenu;
import uk.to.and1558.and1558;

@Mixin(GuiMainMenu.class)
public class MixinMainMenu {
    @Inject(method = "initGui", at = @At("HEAD"))
    public void initMainMenu(CallbackInfo ci){
        Minecraft.getMinecraft().gameSettings.fullScreen = false;
        and1558.getInstance().getDiscordRPC().update("Main Menu", "Beta Update");
        and1558.getInstance().runSingleplayer();
        Minecraft.getMinecraft().displayGuiScreen(new MainMenu());
        and1558.INSTANCE.started = true;
    }
}
