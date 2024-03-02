package owo.aydendevy.mixins.client;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import owo.aydendevy.Mods.ModLoader.ModInstances;

// NHPC = NetHandlerPlayClient
@Mixin(NetHandlerPlayClient.class)
public class MixinNHPC {
    @Inject(method = "handleEntityStatus", at=@At("HEAD"))
    private void playerHit(S19PacketEntityStatus packetIn, CallbackInfo ci){
        ModInstances.getComboCounter().onEntityHit(packetIn);
    }
}
