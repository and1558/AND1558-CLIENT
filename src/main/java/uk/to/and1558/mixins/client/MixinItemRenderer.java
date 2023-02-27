package uk.to.and1558.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import uk.to.and1558.Mods.ModLoader.ModInstances;
import uk.to.and1558.Plugins.AnimationHandler;
import uk.to.and1558.and1558;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer{
    // Shadowed stuff
    @Shadow private float prevEquippedProgress;
    @Shadow private float equippedProgress;
    @Shadow private ItemStack itemToRender;
    // Not Shadowed stuff

    /**
     * isModified = true
     * @author Mojang
     * @Modified By: AND1558
     * @reason Adding Low Fire
     */
    @Overwrite
    private void renderFireInFirstPerson(float p_78442_1_) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
        GlStateManager.depthFunc(519);
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        float f = 1.0F;

        for (int i = 0; i < 2; ++i) {
            GlStateManager.pushMatrix();
            TextureAtlasSprite textureatlassprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("minecraft:blocks/fire_layer_1");
            Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            float f1 = textureatlassprite.getMinU();
            float f2 = textureatlassprite.getMaxU();
            float f3 = textureatlassprite.getMinV();
            float f4 = textureatlassprite.getMaxV();
            float f5 = (0.0F - f) / 2.0F;
            float f6 = f5 + f;
            float f7 = 0.0F - f / 2.0F;
            float f8 = f7 + f;
            float f9 = -0.5F;
            if (ModInstances.getLfire().isEnabled()) {
                GlStateManager.translate((float) (-(i * 2 - 1)) * 0.24F, -0.5F, 0.0F);
            } else {
                GlStateManager.translate((float) (-(i * 2 - 1)) * 0.24F, -0.3F, 0.0F);
            }
            GlStateManager.rotate((float) (i * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
            worldrenderer.pos((double) f5, (double) f7, (double) f9).tex((double) f2, (double) f4).endVertex();
            worldrenderer.pos((double) f6, (double) f7, (double) f9).tex((double) f1, (double) f4).endVertex();
            worldrenderer.pos((double) f6, (double) f8, (double) f9).tex((double) f1, (double) f3).endVertex();
            worldrenderer.pos((double) f5, (double) f8, (double) f9).tex((double) f2, (double) f3).endVertex();
            tessellator.draw();
            GlStateManager.popMatrix();
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.depthFunc(515);
    }

    /**
     * @author Mojang
     * @reason Old Animations Helper
     */
    @Overwrite
    private void transformFirstPersonItem(float equipProgress, float swingProgress)
    {
        GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
        GlStateManager.translate(0.0F, equipProgress * -0.6F, 0.0F);
        GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
        float f = MathHelper.sin(swingProgress * swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI);
        GlStateManager.rotate(f * -20.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f1 * -20.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(0.4F, 0.4F, 0.4F);
    }
    /**
     * @author Mojang
     * @reason: Changed for old 1.7 Animations
     */
    @Overwrite
    public void renderItemInFirstPerson(float partialTicks)
    {
        float f = 1.0F - (this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * partialTicks);
        AbstractClientPlayer abstractclientplayer = this.mc.thePlayer;
        float f1 = abstractclientplayer.getSwingProgress(partialTicks);
        float f2 = abstractclientplayer.prevRotationPitch + (abstractclientplayer.rotationPitch - abstractclientplayer.prevRotationPitch) * partialTicks;
        float f3 = abstractclientplayer.prevRotationYaw + (abstractclientplayer.rotationYaw - abstractclientplayer.prevRotationYaw) * partialTicks;
        this.func_178101_a(f2, f3);
        this.func_178109_a(abstractclientplayer);
        this.func_178110_a((EntityPlayerSP)abstractclientplayer, partialTicks);
        GlStateManager.enableRescaleNormal();
        GlStateManager.pushMatrix();

        if (this.itemToRender != null)
        {
            if (this.itemToRender.getItem() == Items.filled_map)
            {
                this.renderItemMap(abstractclientplayer, f2, f, f1);
            }
            else if (abstractclientplayer.getItemInUseCount() > 0)
            {
                EnumAction enumaction = this.itemToRender.getItemUseAction();
                if(ModInstances.getOldanim().isEnabled) {
                    if (enumaction.equals(EnumAction.NONE)) {
                        this.transformFirstPersonItem(f, 0.0F);
                    } else if (enumaction.equals(EnumAction.EAT) || enumaction.equals(EnumAction.DRINK)) {
                        this.func_178104_a(abstractclientplayer, partialTicks);
                        this.transformFirstPersonItem(f, f1);
                    } else if (enumaction.equals(EnumAction.BLOCK)) {
                        this.transformFirstPersonItem(f, f1);
                        this.func_178103_d();
                    } else if (enumaction.equals(EnumAction.BOW)) {
                        this.transformFirstPersonItem(f, f1);
                        this.func_178098_a(partialTicks, abstractclientplayer);
                    }
                }else {
                    if (enumaction.equals(EnumAction.NONE)) {
                        this.transformFirstPersonItem(f, 0.0F);
                    } else if (enumaction.equals(EnumAction.EAT) || enumaction.equals(EnumAction.DRINK)) {
                        this.func_178104_a(abstractclientplayer, partialTicks);
                        this.transformFirstPersonItem(f, 0.0f);
                    } else if (enumaction.equals(EnumAction.BLOCK)) {
                        this.transformFirstPersonItem(f, 0.0f);
                        this.func_178103_d();
                    } else if (enumaction.equals(EnumAction.BOW)) {
                        this.transformFirstPersonItem(f, 0.0f);
                        this.func_178098_a(partialTicks, abstractclientplayer);
                    }
                }
            }
            else
            {
                this.func_178105_d(f1);
                this.transformFirstPersonItem(f, f1);
            }

            this.renderItem(abstractclientplayer, this.itemToRender, ItemCameraTransforms.TransformType.FIRST_PERSON);
        }
        else if (!abstractclientplayer.isInvisible())
        {
            this.func_178095_a(abstractclientplayer, f, f1);
        }

        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
    }
    @Shadow private void func_178104_a(AbstractClientPlayer clientPlayer, float p_178104_2_) {}
    @Shadow private void func_178103_d() {}
    @Shadow private void func_178101_a(float angle, float p_178101_2_) {}
    @Shadow private void func_178109_a(AbstractClientPlayer clientPlayer) {}
    @Shadow private void func_178110_a(EntityPlayerSP entityplayerspIn, float partialTicks) {}
    @Shadow private void renderItemMap(AbstractClientPlayer clientPlayer, float p_178097_2_, float p_178097_3_, float p_178097_4_) {}
    @Shadow private void func_178098_a(float p_178098_1_, AbstractClientPlayer clientPlayer) {}
    @Shadow private void func_178105_d(float p_178105_1_) {}
    @Shadow public void renderItem(EntityLivingBase entityIn, ItemStack heldStack, ItemCameraTransforms.TransformType transform) {}
    @Shadow private void func_178095_a(AbstractClientPlayer clientPlayer, float p_178095_2_, float p_178095_3_) {}
    @Shadow protected Minecraft mc;
    @Shadow @Final private RenderItem itemRenderer;
    @Shadow
    private int equippedItemSlot;
    public float f(){
        AbstractClientPlayer abstractclientplayer = Minecraft.getMinecraft().thePlayer;
        return 1.0F - (this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * and1558.getInstance().partialTicks);
    }
    private float swingProg = 0.0f;
    public void f1(float swingProgress){
        swingProg = swingProgress;
    }
    @Inject(
            method = { "renderItemInFirstPerson"},
            at = {             @At("HEAD")},
            cancellable = true
    )
    public void renderItemInFirstPerson(float partialTicks, CallbackInfo ci) {
        if (this.itemToRender != null) {
            ItemRenderer $this = new ItemRenderer(this.mc);
            float equipProgress = this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * partialTicks;

            if (AnimationHandler.getInstance().renderItemInFirstPerson($this, this.itemToRender, equipProgress, partialTicks)) {
                //and1558.logger.info("Cancelled");
                ci.cancel();
            }
        }
    }

    @ModifyArg(
            method = { "updateEquippedItem"},
            at =             @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/MathHelper;clamp_float(FFF)F"
            ),
            index = 0
    )
    private float handleItemSwitch(float original) {
        EntityPlayerSP entityplayer = Minecraft.getMinecraft().thePlayer;
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();

        return this.equippedItemSlot == entityplayer.inventory.currentItem && ItemStack.areItemsEqual(this.itemToRender, itemstack) ? 1.0F - this.equippedProgress : original;
    }
}
