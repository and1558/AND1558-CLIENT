package uk.to.and1558.mixins.client;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.to.and1558.Mods.ModLoader.ModInstances;
import uk.to.and1558.Mods.itemPhysics.physics.ClientPhysic;
import uk.to.and1558.and1558;

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
