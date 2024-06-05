package owo.aydendevy.Mods;

import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.Chunk;
import org.lwjgl.opengl.GL11;
import owo.aydendevy.Gui.HUD.ScreenPosition;
import owo.aydendevy.Mods.ModLoader.ModDraggable;
import owo.aydendevy.Mods.ModLoader.ModInstances;
import owo.aydendevy.Plugins.OldDirections;
import owo.aydendevy.Plugins.OldScaleResolution;
import owo.aydendevy.VersionString;

public class ModOldDebug extends ModDraggable {
    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void render(ScreenPosition pos) {

    }
    public void renderDebugOverlay(){
        BlockPos blockpos = new BlockPos(this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().getEntityBoundingBox().minY, this.mc.getRenderViewEntity().posZ);
        int var22;
        int var23;
        OldScaleResolution var5 = new OldScaleResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        int var6 = var5.getScaledWidth();
        if (ModInstances.getOldDebug().isEnabled()) {
            this.mc.mcProfiler.startSection("debug");
            GL11.glPushMatrix();
            this.mc.fontRendererObj.drawStringWithShadow("Minecraft 1.8.9 ( " + this.mc.debug +" )" + " Using OLD F3", 2, 2, 16777215);
            this.mc.fontRendererObj.drawStringWithShadow(this.mc.renderGlobal.getDebugInfoRenders(), 2, 12, 16777215);
            this.mc.fontRendererObj.drawStringWithShadow(this.mc.renderGlobal.getDebugInfoEntities(), 2, 22, 16777215);
            this.mc.fontRendererObj.drawStringWithShadow("P: " + this.mc.effectRenderer.getStatistics() + ". T: " + this.mc.theWorld.getDebugLoadedEntities(), 2, 32, 16777215);
            this.mc.fontRendererObj.drawStringWithShadow(this.mc.theWorld.getProviderName(), 2, 42, 16777215);
            long var36 = Runtime.getRuntime().maxMemory();
            long var41 = Runtime.getRuntime().totalMemory();
            long var44 = Runtime.getRuntime().freeMemory();
            long var45 = var41 - var44;
            String var20 = "Used memory: " + var45 * 100L / var36 + "% (" + var45 / 1024L / 1024L + "MB) of " + var36 / 1024L / 1024L + "MB";
            this.mc.fontRendererObj.drawStringWithShadow(var20, var6 - this.mc.fontRendererObj.getStringWidth(var20) - 2, 2, 14737632);
            var20 = "Allocated memory: " + var41 * 100L / var36 + "% (" + var41 / 1024L / 1024L + "MB)";
            this.mc.fontRendererObj.drawStringWithShadow(var20, var6 - this.mc.fontRendererObj.getStringWidth(var20) - 2, 12, 14737632);
            var22 = MathHelper.floor_double(this.mc.thePlayer.posX);
            var23 = MathHelper.floor_double(this.mc.thePlayer.posY);
            int var24 = MathHelper.floor_double(this.mc.thePlayer.posZ);
            this.mc.fontRendererObj.drawStringWithShadow(String.format("x: %.5f (%d) // c: %d (%d)", new Object[]{Double.valueOf(this.mc.thePlayer.posX), Integer.valueOf(var22), Integer.valueOf(var22 >> 4), Integer.valueOf(var22 & 15)}), 2, 64, 14737632);
            this.mc.fontRendererObj.drawStringWithShadow(String.format("y: %.3f (feet pos, %.3f eyes pos)", new Object[]{Double.valueOf(this.mc.thePlayer.getEntityBoundingBox().minY), Double.valueOf(this.mc.thePlayer.posY)}), 2, 72, 14737632);
            this.mc.fontRendererObj.drawStringWithShadow(String.format("z: %.5f (%d) // c: %d (%d)", new Object[]{Double.valueOf(this.mc.thePlayer.posZ), Integer.valueOf(var24), Integer.valueOf(var24 >> 4), Integer.valueOf(var24 & 15)}), 2, 80, 14737632);
            int var25 = MathHelper.floor_double((double) (this.mc.thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            this.mc.fontRendererObj.drawStringWithShadow("f: " + var25 + " (" + OldDirections.directions[var25] + ") / " + MathHelper.wrapAngleTo180_float(this.mc.thePlayer.rotationYaw), 2, 88, 14737632);

            if (this.mc.theWorld != null && this.blockExists(var22, var23, var24)) {
                Chunk var26 = this.getChunkFromBlockCoords2(var22, var24);
                this.mc.fontRendererObj.drawStringWithShadow("lc: " + (var26.getTopFilledSegment() + 15) + " b: " + var26.getBiome(blockpos, this.mc.theWorld.getWorldChunkManager()).biomeName, 2, 96, 14737632);
            }

            this.mc.fontRendererObj.drawStringWithShadow(String.format("ws: %.3f, fs: %.3f, g: %b, fl: %d", new Object[]{Float.valueOf(this.mc.thePlayer.capabilities.getWalkSpeed()), Float.valueOf(this.mc.thePlayer.capabilities.getFlySpeed()), Boolean.valueOf(this.mc.thePlayer.onGround), Integer.valueOf(this.getHeightValue(var22, var24))}), 2, 104, 14737632);

            if (this.mc.entityRenderer != null && this.mc.entityRenderer.isShaderActive()) {
                this.mc.fontRendererObj.drawStringWithShadow(String.format("shader: %s", new Object[]{this.mc.entityRenderer.getShaderGroup().getShaderGroupName()}), 2, 112, 14737632);
                this.mc.fontRendererObj.drawStringWithShadow("Running " + VersionString.debugOverlayVer, 2, 120, 14737632);
            }else{
                this.mc.fontRendererObj.drawStringWithShadow("Running " + VersionString.debugOverlayVer, 2, 114, 14737632);
            }

            GL11.glPopMatrix();
            this.mc.mcProfiler.endSection();
        }
    }
    public int getHeightValue(int p_72976_1_, int p_72976_2_) {
        // TODO Auto-generated method stub
        if (p_72976_1_ >= -30000000 && p_72976_2_ >= -30000000 && p_72976_1_ < 30000000 && p_72976_2_ < 30000000)
        {
            if (!this.mc.theWorld.getChunkProvider().chunkExists(p_72976_1_ >> 4, p_72976_2_ >> 4))
            {
                return 0;
            }
            else
            {
                Chunk var3 = this.mc.theWorld.getChunkFromChunkCoords(p_72976_1_ >> 4, p_72976_2_ >> 4);
                return var3.getHeightValue(p_72976_1_ & 15, p_72976_2_ & 15);
            }
        }
        else
        {
            return 64;
        }
    }
    public Chunk getChunkFromBlockCoords2(int p_72938_1_, int p_72938_2_)
    {
        return this.mc.theWorld.getChunkFromChunkCoords(p_72938_1_ >> 4, p_72938_2_ >> 4);
    }
    public boolean blockExists(int p_72899_1_, int p_72899_2_, int p_72899_3_)
    {
        return p_72899_2_ >= 0 && p_72899_2_ < 256 ? this.mc.theWorld.getChunkProvider().chunkExists(p_72899_1_ >> 4, p_72899_3_ >> 4) : false;
    }
}
