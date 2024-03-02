package owo.aydendevy.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import owo.aydendevy.DevyClient;

@Mixin(GuiConnecting.class)
public class MixinConnecting {
    @Inject(method = "connect", at = @At("HEAD"))
    private void connecting(CallbackInfo ci){
        DevyClient.getInstance().getDiscordRPC().update("Playing multiplayer", "Playing on " + Minecraft.getMinecraft().getCurrentServerData().serverIP, "IGN: " + Minecraft.getMinecraft().getSession().getUsername());
        DevyClient.getInstance().runUpdateCheck();
    }
}
