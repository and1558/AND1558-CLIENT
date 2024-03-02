package owo.aydendevy.mixins.client;

import net.minecraft.client.gui.GuiMultiplayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import owo.aydendevy.DevyClient;

@Mixin(GuiMultiplayer.class)
public class MixinMultiplayerMenu {
    @Inject(method = "initGui", at = @At("HEAD"))
    public void initMultiplayerGui(CallbackInfo ci){
        DevyClient.getInstance().getDiscordRPC().update("Multiplayer Screen","Looking for servers to play on");
    }
}
