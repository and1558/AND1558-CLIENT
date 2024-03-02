package owo.aydendevy.mixins.client;

import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import owo.aydendevy.Gui.ModifiedPauseScreen;
import owo.aydendevy.DevyClient;

@Mixin(GuiIngameMenu.class)
public class MixinIngameMenu extends GuiScreen {
    int i = -16;
    @Inject(method = "actionPerformed", at = @At(value = "TAIL", target = "Lnet/minecraft/client/gui/GuiMainMenu;<init>()V"))
    private void resetUpdateChecker(CallbackInfo ci){
        DevyClient.getInstance().setUpdateChecked(true);
    }
    @Inject(method = "actionPerformed", at = @At(value = "TAIL", target = "Lnet/minecraft/client/gui/GuiMultiplayer;<init>(Lnet/minecraft/client/gui/GuiScreen;)V"))
    private void resetUpdateCheckerMultiplayer(CallbackInfo ci){
        DevyClient.getInstance().setUpdateChecked(true);
    }
    @Inject(method = "initGui", at = @At(value = "RETURN"))
    private void initGui(CallbackInfo ci){ this.mc.displayGuiScreen(new ModifiedPauseScreen());}
}
