package uk.to.and1558.mixins.client;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import uk.to.and1558.Mods.ModLoader.ModInstances;

@Mixin(RenderManager.class)
public class MixinRndrManager {
    @Shadow public World worldObj;
    @Shadow public GameSettings options;
    @Shadow public Entity livingPlayer;
    @Shadow public Entity pointedEntity;
    @Shadow private FontRenderer textRenderer;
    @Shadow public float playerViewY;
    @Shadow public float playerViewX;
    @Shadow public double viewerPosX;
    @Shadow public double viewerPosY;
    @Shadow public double viewerPosZ;
    /**
     * @author Mojang
     * @reason Perspective Mod
     */
    @Overwrite
    public void cacheActiveRenderInfo(World worldIn, FontRenderer textRendererIn, Entity livingPlayerIn, Entity pointedEntityIn, GameSettings optionsIn, float partialTicks)
    {
        this.worldObj = worldIn;
        this.options = optionsIn;
        this.livingPlayer = livingPlayerIn;
        this.pointedEntity = pointedEntityIn;
        this.textRenderer = textRendererIn;

        if (livingPlayerIn instanceof EntityLivingBase && ((EntityLivingBase)livingPlayerIn).isPlayerSleeping())
        {
            IBlockState iblockstate = worldIn.getBlockState(new BlockPos(livingPlayerIn));
            Block block = iblockstate.getBlock();

            if (block == Blocks.bed)
            {
                int i = ((EnumFacing)iblockstate.getValue(BlockBed.FACING)).getHorizontalIndex();
                this.playerViewY = (float)(i * 90 + 180);
                this.playerViewX = 0.0F;
            }
        }
        else
        {
            this.playerViewY = ModInstances.getPerspective().getCameraYaw() + (ModInstances.getPerspective().getCameraYaw() - ModInstances.getPerspective().getCameraYaw()) * partialTicks;
            this.playerViewX = ModInstances.getPerspective().getCameraPitch() + (ModInstances.getPerspective().getCameraPitch() - ModInstances.getPerspective().getCameraPitch()) * partialTicks;
        }

        if (optionsIn.thirdPersonView == 2)
        {
            this.playerViewY += 180.0F;
        }

        this.viewerPosX = livingPlayerIn.lastTickPosX + (livingPlayerIn.posX - livingPlayerIn.lastTickPosX) * (double)partialTicks;
        this.viewerPosY = livingPlayerIn.lastTickPosY + (livingPlayerIn.posY - livingPlayerIn.lastTickPosY) * (double)partialTicks;
        this.viewerPosZ = livingPlayerIn.lastTickPosZ + (livingPlayerIn.posZ - livingPlayerIn.lastTickPosZ) * (double)partialTicks;
    }

}
