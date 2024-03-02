package owo.aydendevy.Mods;

import net.minecraft.util.MouseHelper;
import org.lwjgl.input.Mouse;

public class RawMouseHelper extends MouseHelper {
    @Override
    public void mouseXYChange()
    {
        this.deltaX = RawMouseInput.dx;
        RawMouseInput.dx = 0;
        this.deltaY = -RawMouseInput.dy;
        RawMouseInput.dy = 0;
    }
    @Override
    public void grabMouseCursor()
    {
        if (Boolean.parseBoolean(System.getProperty("fml.noGrab","false"))) return;
        Mouse.setGrabbed(true);
        this.deltaX = 0;
        RawMouseInput.dx = 0;
        this.deltaY = 0;
        RawMouseInput.dy = 0;
    }
}
