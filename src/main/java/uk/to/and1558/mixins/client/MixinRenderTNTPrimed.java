package uk.to.and1558.mixins.client;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;
import net.minecraft.entity.item.EntityTNTPrimed;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.to.and1558.Mods.ModLoader.ModInstances;
import uk.to.and1558.Mods.TNTCounter;

@Mixin(RenderTNTPrimed.class)
public class MixinRenderTNTPrimed{
    private RenderManager renderTntPrimedManager;
    @Inject(method = "<init>", at = @At("RETURN"))
    private void getRenderManager(RenderManager renderManagerIn, CallbackInfo callbackInfo){
        renderTntPrimedManager = renderManagerIn;
    }

    @Inject(method = "doRender(Lnet/minecraft/entity/item/EntityTNTPrimed;DDDFF)V", at = @At("RETURN"))
    private void renderCountdown(EntityTNTPrimed entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci){
        TNTCounter mod = ModInstances.getTntCounter(); // change to however you get an instance of the mod
        mod.renderTag(renderTntPrimedManager, entity, x, y, z, partialTicks);
    }
}
