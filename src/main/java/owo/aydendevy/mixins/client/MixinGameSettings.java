package owo.aydendevy.mixins.client;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import owo.aydendevy.DevyClient;

@Mixin(GameSettings.class)
public class MixinGameSettings {
    @Shadow
    public KeyBinding[] keyBindings;

    public KeyBinding MODPOSGUI = new KeyBinding("Change Mod Position", Keyboard.KEY_RSHIFT, "AND1558");
    public KeyBinding PerspectiveToggle = new KeyBinding("Toggle Perspective Mod", Keyboard.KEY_V, "AND1558");
    // dev 1.82 -> Fixed controls not saving after being changed by the players
    @Inject(at = @At("HEAD"), method = "loadOptions")
    private void injectFirstConstructor(CallbackInfo ci) {
        this.addKeybinds();
    }

    private void addKeybinds() {
        this.keyBindings = ((KeyBinding[]) ArrayUtils.add(this.keyBindings, this.MODPOSGUI));
        this.keyBindings = ((KeyBinding[]) ArrayUtils.add(this.keyBindings, this.PerspectiveToggle));
        DevyClient.getInstance().setKeybind(MODPOSGUI, "POS");
        DevyClient.getInstance().setKeybind(PerspectiveToggle, "PERSPECTIVE");
    }

}
