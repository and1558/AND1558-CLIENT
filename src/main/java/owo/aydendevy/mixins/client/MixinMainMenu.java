package owo.aydendevy.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import owo.aydendevy.Gui.MainMenu;
import owo.aydendevy.DevyClient;

@Mixin(GuiMainMenu.class)
public class MixinMainMenu {
    @Inject(method = "initGui", at = @At("HEAD"))
    public void initMainMenu(CallbackInfo ci){
        Minecraft.getMinecraft().gameSettings.fullScreen = false;
        DevyClient.getInstance().getDiscordRPC().update("Main Menu", "Beta Update");
        DevyClient.getInstance().runSingleplayer();
        Minecraft.getMinecraft().displayGuiScreen(new MainMenu());
        DevyClient.INSTANCE.started = true;
    }
}
