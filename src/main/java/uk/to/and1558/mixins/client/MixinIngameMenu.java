package uk.to.and1558.mixins.client;

import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.to.and1558.Gui.ModifiedPauseScreen;
import uk.to.and1558.and1558;

@Mixin(GuiIngameMenu.class)
public class MixinIngameMenu extends GuiScreen {
    int i = -16;
    @Inject(method = "actionPerformed", at = @At(value = "TAIL", target = "Lnet/minecraft/client/gui/GuiMainMenu;<init>()V"))
    private void resetUpdateChecker(CallbackInfo ci){
        and1558.getInstance().setUpdateChecked(true);
    }
    @Inject(method = "actionPerformed", at = @At(value = "TAIL", target = "Lnet/minecraft/client/gui/GuiMultiplayer;<init>(Lnet/minecraft/client/gui/GuiScreen;)V"))
    private void resetUpdateCheckerMultiplayer(CallbackInfo ci){
        and1558.getInstance().setUpdateChecked(true);
    }
    @Inject(method = "initGui", at = @At(value = "RETURN"))
    private void initGui(CallbackInfo ci){ this.mc.displayGuiScreen(new ModifiedPauseScreen());}
}
