package uk.to.and1558.mixins.client;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.to.and1558.Mods.ModLoader.ModInstances;

@Mixin(EntityPlayer.class)
public class MixinPlayerEntity {
    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At("HEAD"))
    private void playerAttacked(Entity targetEntity, CallbackInfo ci){
        ModInstances.getComboCounter().onAttack(targetEntity);
    }
}
