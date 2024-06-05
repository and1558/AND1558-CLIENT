package owo.aydendevy.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import owo.aydendevy.Mods.ModLoader.ModInstances;
import owo.aydendevy.Plugins.OldDirections;
import owo.aydendevy.Plugins.OldScaleResolution;

@Mixin(GuiOverlayDebug.class)
public class MixinDebugOverlay {
    @Shadow protected void renderDebugInfoLeft() {}
    @Shadow protected void renderDebugInfoRight(ScaledResolution p_175239_1_) {}
    @Shadow private void renderLagometer() {}
    Minecraft mc = Minecraft.getMinecraft();
    /**
     * @author Mojang
     */
    @Overwrite
    public void renderDebugInfo(ScaledResolution scaledResolution){
        if(ModInstances.getOldDebug().isEnabled) {
            ModInstances.getOldDebug().renderDebugOverlay();
        }else if(ModInstances.getOldDebug().isEnabled == false){
            GlStateManager.pushMatrix();
            this.renderDebugInfoLeft();
            this.renderDebugInfoRight(scaledResolution);
            GlStateManager.popMatrix();

            if (this.mc.gameSettings.showLagometer)
            {
                this.renderLagometer();
            }

        }
    }

}

