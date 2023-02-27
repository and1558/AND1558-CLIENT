package uk.to.and1558.mixins.client;

import net.minecraft.client.gui.GuiMultiplayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.to.and1558.and1558;

@Mixin(GuiMultiplayer.class)
public class MixinMultiplayerMenu {
    @Inject(method = "initGui", at = @At("HEAD"))
    public void initMultiplayerGui(CallbackInfo ci){
        and1558.getInstance().getDiscordRPC().update("Multiplayer Screen","Looking for servers to play on");
    }
}
