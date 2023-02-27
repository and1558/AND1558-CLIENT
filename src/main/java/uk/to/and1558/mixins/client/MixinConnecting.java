package uk.to.and1558.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.to.and1558.and1558;

@Mixin(GuiConnecting.class)
public class MixinConnecting {
    @Inject(method = "connect", at = @At("HEAD"))
    private void connecting(CallbackInfo ci){
        and1558.getInstance().getDiscordRPC().update("Playing multiplayer", "Playing on " + Minecraft.getMinecraft().getCurrentServerData().serverIP, "IGN: " + Minecraft.getMinecraft().getSession().getUsername());
        and1558.getInstance().runUpdateCheck();
    }
}
