package uk.to.and1558.mixins.client;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.to.and1558.and1558;

@Mixin(GameSettings.class)
public class MixinGameSettings {
    @Shadow
    public KeyBinding[] keyBindings;

    public KeyBinding MODPOSGUI = new KeyBinding("Change Mod Position", Keyboard.KEY_RSHIFT, "AND1558");
    public KeyBinding PerspectiveToggle = new KeyBinding("Toggle Perspective Mod", Keyboard.KEY_V, "AND1558");

    @Inject(at = @At("RETURN"), method = "<init>()V")
    private void injectFirstConstructor(CallbackInfo ci) {
        this.addKeybinds();
    }

    @Inject(at = @At("RETURN"), method = "<init>(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V")
    private void injectSecondConstructor(CallbackInfo ci) {
        this.addKeybinds();
    }

    private void addKeybinds() {
        this.keyBindings = ((KeyBinding[]) ArrayUtils.add(this.keyBindings, this.MODPOSGUI));
        this.keyBindings = ((KeyBinding[]) ArrayUtils.add(this.keyBindings, this.PerspectiveToggle));
        and1558.getInstance().setKeybind(MODPOSGUI, "POS");
        and1558.getInstance().setKeybind(PerspectiveToggle, "PERSPECTIVE");
    }

}
