package uk.to.and1558.mixins.client;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.to.and1558.Events.impl.RenderEvent;
import uk.to.and1558.Mods.ModLoader.ModInstances;
import uk.to.and1558.Plugins.AnimationHandler;
import uk.to.and1558.VersionString;

@Mixin(GuiIngame.class)
public class MixinIngame {
    protected final Minecraft mc = Minecraft.getMinecraft();
    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void renderGameOverlayR(float partialTicks, CallbackInfo ci){
        if(!mc.gameSettings.showDebugInfo) {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(VersionString.Ver, 2, 2, -1);
            if (mc.inGameHasFocus && mc.thePlayer != null && mc.theWorld != null) {
                // Dev-1.82 -> Prevent some HUD randomly not appearing across minecraft restarts
                new RenderEvent().call();
            }
        }
    }

    /**
     * @author Technerder
     */
    private void attemptSwing() {
        if (this.mc.thePlayer.getItemInUseCount() > 0) {
            final boolean mouseDown = this.mc.gameSettings.keyBindAttack.isKeyDown() && this.mc.gameSettings.keyBindUseItem.isKeyDown();
            if (mouseDown && this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && ModInstances.getOldanim().isEnabled) {
                this.swingItem(this.mc.thePlayer);
            }else if(mouseDown){
                this.swingItem(this.mc.thePlayer);
            }
        }
    }

    /**
     * @author Technerder
     * @param entityplayersp Get the client-side Player
     */
    private void swingItem(EntityPlayerSP entityplayersp) {
        final int swingAnimationEnd = entityplayersp.isPotionActive(Potion.digSpeed) ? (6 - (1 + entityplayersp.getActivePotionEffect(Potion.digSpeed).getAmplifier())) : (entityplayersp.isPotionActive(Potion.digSlowdown) ? (6 + (1 + entityplayersp.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2) : 6);
        if (!entityplayersp.isSwingInProgress || entityplayersp.swingProgressInt >= swingAnimationEnd / 2 || entityplayersp.swingProgressInt < 0) {
            entityplayersp.swingProgressInt = -1;
            entityplayersp.isSwingInProgress = true;
        }
    }
    @Inject(method = "updateTick", at = @At(value = "HEAD"))
    private void playOldAnim(CallbackInfo ci){
        if(this.mc.thePlayer != null){
            attemptSwing();
            new AnimationHandler().updateSwingProgress();
        }
    }
}
