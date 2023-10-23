package uk.to.and1558.mixins.client;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.to.and1558.Events.impl.RenderEvent;
import uk.to.and1558.Gui.impl.BlurUtils;
import uk.to.and1558.Mods.ModLoader.ModInstances;
import uk.to.and1558.Plugins.AnimationHandler;
import uk.to.and1558.VersionString;

import java.util.Collection;
import java.util.List;

import static net.minecraft.client.gui.Gui.drawRect;

@Mixin(GuiIngame.class)
public class MixinIngame {
    // IntellIJ told to add "and1558$" to any class that is not getting overwritten or modified or is new/unique
    @Unique
    protected final Minecraft and1558$mc = Minecraft.getMinecraft();
    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void renderGameOverlayR(float partialTicks, CallbackInfo ci){
        if(!and1558$mc.gameSettings.showDebugInfo) {
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(VersionString.Ver, 2, 2, -1);
        }
        // Dev-1.82 -> Prevent some HUD randomly not appearing across minecraft restarts [Attempt 5]
        // This one single bug really demotivate me
        new RenderEvent().call();
    }

    /**
     * @author Technerder
     */
    @Unique
    private void and1558$attemptSwing() {
        if (this.and1558$mc.thePlayer.getItemInUseCount() > 0) {
            final boolean mouseDown = this.and1558$mc.gameSettings.keyBindAttack.isKeyDown() && this.and1558$mc.gameSettings.keyBindUseItem.isKeyDown();
            if (mouseDown && this.and1558$mc.objectMouseOver != null && this.and1558$mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && ModInstances.getOldanim().isEnabled) {
                this.and1558$swingItem(this.and1558$mc.thePlayer);
            }else if(mouseDown){
                this.and1558$swingItem(this.and1558$mc.thePlayer);
            }
        }
    }

    /**
     * @author Technerder
     * @param entityplayersp Get the client-side Player
     */
    @Unique
    private void and1558$swingItem(EntityPlayerSP entityplayersp) {
        final int swingAnimationEnd = entityplayersp.isPotionActive(Potion.digSpeed) ? (6 - (1 + entityplayersp.getActivePotionEffect(Potion.digSpeed).getAmplifier())) : (entityplayersp.isPotionActive(Potion.digSlowdown) ? (6 + (1 + entityplayersp.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2) : 6);
        if (!entityplayersp.isSwingInProgress || entityplayersp.swingProgressInt >= swingAnimationEnd / 2 || entityplayersp.swingProgressInt < 0) {
            entityplayersp.swingProgressInt = -1;
            entityplayersp.isSwingInProgress = true;
        }
    }
    @Inject(method = "updateTick", at = @At(value = "HEAD"))
    private void playOldAnim(CallbackInfo ci){
        if(this.and1558$mc.thePlayer != null){
            and1558$attemptSwing();
            new AnimationHandler().updateSwingProgress();
        }
    }
}
