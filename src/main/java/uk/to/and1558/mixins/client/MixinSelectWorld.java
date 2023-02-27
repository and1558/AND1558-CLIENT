package uk.to.and1558.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSelectWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.to.and1558.and1558;

@Mixin(GuiSelectWorld.class)
public class MixinSelectWorld {

    @Inject(method = "initGui", at = @At("HEAD"))
    public void initSelectWorld(CallbackInfo ci){
        and1558.getInstance().getDiscordRPC().update("Selecting World", "Looking for a world to play on");
    }
}
