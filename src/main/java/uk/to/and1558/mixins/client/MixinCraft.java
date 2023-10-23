package uk.to.and1558.mixins.client;

import jdk.nashorn.internal.runtime.Version;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.*;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.*;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import net.minecraft.world.storage.ISaveFormat;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.to.and1558.Events.KeyEvent;
import uk.to.and1558.Events.impl.ClientTickEvent;
import uk.to.and1558.Gui.SplashScreen;
import uk.to.and1558.Gui.impl.BlurUtils;
import uk.to.and1558.Mods.RawMouseHelper;
import uk.to.and1558.Mods.RawMouseInput;
import uk.to.and1558.Plugins.GuiUtils;
import uk.to.and1558.Plugins.SessionMod;
import uk.to.and1558.Plugins.ShaderLoader;
import uk.to.and1558.VersionString;
import uk.to.and1558.and1558;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

import static org.lwjgl.opengl.GL11.glClearColor;


@Mixin(Minecraft.class)
public class MixinCraft{
    @Shadow public GuiIngame ingameGUI;
    @Shadow public GameSettings gameSettings;
    /**
     * Useful class shadowed
     */
    @Shadow
    private void updateDisplayMode() throws LWJGLException {}
    @Shadow public EntityRenderer entityRenderer;
    @Shadow public GuiScreen currentScreen;
    @Shadow @Final
    private static Logger logger;
    @Shadow private Timer timer;
    @Shadow private Session session;
    boolean displayCreated = false;
    @Inject(method = "<init>", at = @At("RETURN"))
    private void EarlyInit(GameConfiguration gameConfiguration, CallbackInfo ci){
        /**---------------------------------------------------------
         * Change minecraft default session class to my modified one
         * as using the default one (atleast on my configurations)
         * crashes the game
         * ---------------------------------------------------------
        **/
        session = new SessionMod(gameConfiguration.userInfo.session.getUsername(),gameConfiguration.userInfo.session.getPlayerID(), gameConfiguration.userInfo.session.getToken(), "mojang");
        // Initialized more early for stuff needed to load before startGame() is called
        and1558.getInstance().initEarly(gameConfiguration, (SessionMod) session);
    }
    @Inject(method = "startGame", at = @At("HEAD"))
    private void initMinecraft(CallbackInfo ci) throws InterruptedException {
        this.displayWidth = 1280;
        this.displayHeight = 720;
        and1558.getInstance().init();
        // Pass-in Minecraft's Render Engine to AND1558, so we can access it
        and1558.getInstance().renderEngine = renderEngine;
        SplashScreen.setProgress(2, "Mixin - Initializing Mixins");
        /**----------------------------------
         * Raw Mouse Handling Mod Initializer
         * ----------------------------------
         */
        Minecraft.getMinecraft().mouseHelper = new RawMouseHelper();
        SplashScreen.setProgress(3, "Client - Initializing Raw Mouse Input");
        RawMouseInput.init();
        SplashScreen.setProgress(5, "Minecraft - Initializing");
    }
    @Inject(method = "startGame", at = @At("RETURN"))
    private void initMinecraftReturn(CallbackInfo ci){
        and1558.logger.info("MIXING CLASSES Stage 2 SUCCESS");
        SplashScreen.setProgress(9, "Mods - Initializing Instances");
        and1558.getInstance().start();
        SplashScreen.setProgress(11, "Mixin - Mixing Menus");
        and1558.INSTANCE.started = true;
        // Set minecraft launch size to 1280x720 [Set this to whatever you want for your own build]
        // NOTE: YOU MAY NOT CHANGE THIS UNLESS A VALID REASON IS GIVEN UNLESS YOU ARE MAKING YOUR OWN BUILD
        this.resize(1280, 720);
    }
    /**@Inject(
            method = { "rightClickMouse"},
            at = {             @At("HEAD")}
    )
    public void rightClickMouse(CallbackInfo ci) {
        if (ModInstances.getOldanim().isEnabled() && Minecraft.getMinecraft().playerController.func_181040_m() && Minecraft.getMinecraft().thePlayer.getHeldItem() != null && (Minecraft.getMinecraft().thePlayer.getHeldItem().getItemUseAction() != EnumAction.NONE || Minecraft.getMinecraft().thePlayer.getHeldItem().getItem() instanceof ItemBlock)) {
            Minecraft.getMinecraft().playerController.resetBlockRemoving();
        }
    }**/

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureMap;setBlurMipmapDirect(ZZ)V"))
    private void init3(CallbackInfo ci){
        and1558.logger.info("MIXING CLASSES... Stage 2");
    }
    @Inject(method = "runTick", at = @At("HEAD"))
    private void event1(CallbackInfo ci){
        and1558.getInstance().EarlyTick();
    }
    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;dispatchKeypresses()V", shift = At.Shift.BEFORE))
    private void onKey(CallbackInfo ci) {
        // Keyboard key event
        int k = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
        KeyEvent keyEvent = new KeyEvent(k);
        keyEvent.call();
    }

    @Inject(method = "runTick", at = @At("RETURN"))
    private void event(CallbackInfo ci){
        and1558.getInstance().setPartialTicks(timer.renderPartialTicks);
        and1558.getInstance().tick();
        // Removed default GUI blur. [dev 1.82]
        new ClientTickEvent().call();
    }


    @Inject(method = "shutdownMinecraftApplet", at = @At("HEAD"))
    private void shutdownMinecraft(CallbackInfo ci){
        // Logic to turn off Minecraft
        // Use this to properly deload or stop some stuff in the client
        this.gameSettings.fullScreen = false;
        and1558.getInstance().shutdown();
    }
    /**
     * @author Mojang
     * @reason Changed to use custom splash screen
     */
    @Overwrite
    private void drawSplashScreen(TextureManager textureManagerInstance)  {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        // Draw custom splash screen
        SplashScreen.drawSplash(textureManagerInstance);
    }
    /**
     * @author Mojang
     * @reason Remove 30 FPS Limit in GUIs
     */
    @Overwrite
    public int getLimitFramerate()
    {
        return Minecraft.getMinecraft().theWorld == null && this.currentScreen != null ? 60 : this.gameSettings.limitFramerate;
    }
    /**
     * @author Mojang
     * @reason Change title text
     */
    @Overwrite
    private void createDisplay() throws LWJGLException
    {
        if(displayCreated) {
            glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            return;
        }
        displayCreated = true;
        Display.setResizable(true);
        Display.setTitle(VersionString.titleVer);

        try
        {
            Display.create((new PixelFormat()).withDepthBits(24));
        }
        catch (LWJGLException lwjglexception)
        {
            logger.error((String)"Couldn\'t set pixel format", (Throwable)lwjglexception);

            try
            {
                Thread.sleep(1000L);
            }
            catch (InterruptedException var3)
            {
                ;
            }

            if (this.fullscreen)
            {
                this.updateDisplayMode();
            }

            Display.create();
            glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    /**
     * @author Mojang
     * @reason Modified Game Icon
     */
    @Overwrite
    private void setWindowIcon()
    {
        if (!System.getProperty("os.name").toLowerCase().contains("mac"))
        {
            InputStream inputstream = null;
            InputStream inputstream1 = null;

            try
            {
                inputstream = ClassLoader.getSystemResourceAsStream("assets/minecraft/icons/icon_16.png");
                inputstream1 = ClassLoader.getSystemResourceAsStream("assets/minecraft/icons/icon_32.png");

                if (inputstream != null && inputstream1 != null)
                {
                    Display.setIcon(new ByteBuffer[] {this.readImageToBuffer(inputstream), this.readImageToBuffer(inputstream1)});
                }
            }
            catch (IOException ioexception)
            {
                logger.error((String)"Couldn\'t set icon", (Throwable)ioexception);
            }
            finally
            {
                IOUtils.closeQuietly(inputstream);
                IOUtils.closeQuietly(inputstream1);
            }
        }
    }
    /**
     * @author Mojang
     */
    @Shadow
    private ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException
    {
        return null;
    }

    @Shadow public int displayWidth;
    @Shadow public int displayHeight;

    @Shadow private int tempDisplayWidth;
    @Shadow private int tempDisplayHeight;
    //@Inject(method = "toggleFullscreen", at = @At("RETURN"))
    /**
     * @author Mojang
     * @reason fix fullscreen bug
     */
    @Overwrite
    public void toggleFullscreen(){
        try
        {
            this.fullscreen = !this.fullscreen;
            this.gameSettings.fullScreen = this.fullscreen;

            if (this.fullscreen)
            {
                this.updateDisplayMode();
                this.displayWidth = Display.getDisplayMode().getWidth();
                this.displayHeight = Display.getDisplayMode().getHeight();

                if (this.displayWidth <= 0)
                {
                    this.displayWidth = 1;
                }

                if (this.displayHeight <= 0)
                {
                    this.displayHeight = 1;
                }
            }
            else
            {
                // Changed default value of window size to the correct one
                this.tempDisplayWidth = 1280;
                this.tempDisplayHeight = 720;
                Display.setDisplayMode(new DisplayMode(this.tempDisplayWidth, this.tempDisplayHeight));
                this.displayWidth = this.tempDisplayWidth;
                this.displayHeight = this.tempDisplayHeight;

                if (this.displayWidth <= 0)
                {
                    this.displayWidth = 1;
                }

                if (this.displayHeight <= 0)
                {
                    this.displayHeight = 1;
                }
            }

            if (this.currentScreen != null)
            {
                this.resize(this.displayWidth, this.displayHeight);
            }
            else
            {
                this.updateFramebufferSize();
            }

            Display.setFullscreen(this.fullscreen);

            if(!this.fullscreen){
                Display.setResizable(false);
                Display.setResizable(true);
            }
            Display.setVSyncEnabled(this.gameSettings.enableVsync);
            this.updateDisplay();
        }
        catch (Exception exception)
        {
            logger.error((String)"Couldn\'t toggle fullscreen", (Throwable)exception);
        }
    }
    @Shadow private void resize(int width, int height) {}
    @Shadow private void updateFramebufferSize() {}
    @Shadow public void updateDisplay() {}
    @Shadow private TextureManager renderEngine; // <-- used even if it looks unused
    @Shadow private boolean fullscreen;
}
