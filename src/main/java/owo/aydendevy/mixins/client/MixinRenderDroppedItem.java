package owo.aydendevy.mixins.client;

import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.entity.item.EntityItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import owo.aydendevy.Mods.ModLoader.ModInstances;
import owo.aydendevy.Mods.itemPhysics.physics.ClientPhysic;

@Mixin(RenderEntityItem.class)
public abstract class MixinRenderDroppedItem {
    @Inject(method = "doRender", at = @At("HEAD"), cancellable = true)
    private void doRender(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        if (ModInstances.getItemPhysics().isEnabled()) {
            // dev 1.82 -> Randomizes the item rotation when landed on ground instead of all of them being the same angle when dropped
            // Thanks hyperium devs and CreativeMD
            ClientPhysic.doRender(entity, x, y, z);
            ci.cancel();
        }
    }
}
