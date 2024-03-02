package owo.aydendevy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import owo.aydendevy.DiscordRP.DiscordRPCEvent;
import owo.aydendevy.Events.EventManager;
import owo.aydendevy.Events.EventTarget;
import owo.aydendevy.Events.UpdateCheckEvent;
import owo.aydendevy.Events.impl.ClientTickEvent;
import owo.aydendevy.Events.impl.UpdateCheckThread;
import owo.aydendevy.Gui.HUD.HUDManager;
import owo.aydendevy.Gui.ModTogglerScreen;
import owo.aydendevy.Gui.SplashScreen;
import owo.aydendevy.IO.FileIOManager;
import owo.aydendevy.Mods.ModLoader.ModIO;
import owo.aydendevy.Mods.ModLoader.ModInstances;
import owo.aydendevy.Mods.RawMouseInput;
import owo.aydendevy.Plugins.AnimationHandler;
import owo.aydendevy.Gui.impl.GuiUtils;
import owo.aydendevy.Plugins.ClientAnimations.Animation;
import owo.aydendevy.Plugins.ClientAnimations.Easing;
import owo.aydendevy.Plugins.SessionMod;
import owo.aydendevy.club.aetherium.api.particle.SnowfallParticles;

import java.util.Calendar;
import java.util.Date;

public class DevyClient {
    public static final Logger logger = LogManager.getLogger();
    public static final DevyClient INSTANCE = new DevyClient();
    public static ClientOptions options = new ClientOptions();
    public boolean started = false;
    public static final DevyClient getInstance() { return INSTANCE; }
    // dev-1.82. Added animations for GuiUtils and made it accessible here
    public static GuiUtils guiUtils = new GuiUtils();
    private DiscordRPCEvent DiscordEvent = new DiscordRPCEvent();
    private HUDManager hudManager;
    public KeyBinding MODPOSGUI;
    public KeyBinding PRSPCTVMOD;
    public static ModIO getIO = new ModIO();
    private boolean isUpdateChecked = false;
    public float partialTicks;
    public Timer mcTimer;
    public void setPartialTicks(float partialTicks1){
        partialTicks = partialTicks1;
    }
    public void setTimer(Timer timer) {mcTimer = timer;}
    public void init(){
        SplashScreen.setProgress(1, "Mods - Initializing Mod Configs & Disabling debugger");
        logger.info("MIXING CLASSES... Stage 1");
        logger.info("MIXING CLASSES Stage 1 SUCCESS");
        logger.info("Loading Mod Configs...");
        FileIOManager.init();
        logger.info("Mod Configs Loaded!");
        logger.info("Starting Discord RPC");
        DiscordEvent.start();
        logger.info("Started Discord RPC");
        DiscordEvent.update("Starting","Cannot find version String");
        EventManager.register(this);
        EventManager.register(new AnimationHandler());
    }
    public void init2() {
        if(!initialized) {
            this.logger.info("Initializing Mods");
            if (getIO.loadConfig("Keystrokes")) {
                new ModTogglerScreen().setEnable(0, true);
                ModInstances.getKeystrokes().isEnabled = true;
            } else {
                new ModTogglerScreen().setEnable(0, false);
                ModInstances.getKeystrokes().isEnabled = false;
            }
            if (getIO.loadConfig("bps")) {
                new ModTogglerScreen().setEnable(1, true);
                ModInstances.getSpeedCounter().isEnabled = true;
            } else {
                new ModTogglerScreen().setEnable(1, false);
                ModInstances.getSpeedCounter().isEnabled = false;
            }
            if (getIO.loadConfig("ping")) {
                new ModTogglerScreen().setEnable(2, true);
                ModInstances.getPingCounter().isEnabled = true;
            } else {
                new ModTogglerScreen().setEnable(2, false);
                ModInstances.getPingCounter().isEnabled = false;
            }
            if (getIO.loadConfig("lowfire")) {
                new ModTogglerScreen().setEnable(3, true);
                ModInstances.getLfire().isEnabled = true;
            } else {
                new ModTogglerScreen().setEnable(3, false);
                ModInstances.getLfire().isEnabled = false;
            }
            if (getIO.loadConfig("oldanimations")) {
                new ModTogglerScreen().setEnable(4, true);
                ModInstances.getOldanim().isEnabled = true;
            } else {
                new ModTogglerScreen().setEnable(4, false);
                ModInstances.getOldanim().isEnabled = false;
            }
            if (getIO.loadConfig("perspective")) {
                new ModTogglerScreen().setEnable(6, true);
                ModInstances.getPerspective().isEnabled = true;
            } else {
                new ModTogglerScreen().setEnable(6, false);
                ModInstances.getPerspective().isEnabled = false;
            }
            if (getIO.loadConfig("armorview")) {
                new ModTogglerScreen().setEnable(8, true);
                ModInstances.getArmorView().isEnabled = true;
            } else {
                new ModTogglerScreen().setEnable(8, false);
                ModInstances.getArmorView().isEnabled = false;
            }
            if (getIO.loadConfig("sprinttoggle")) {
                new ModTogglerScreen().setEnable(7, true);
                ModInstances.getToggleSprint().isEnabled = true;
            } else {
                new ModTogglerScreen().setEnable(7, false);
                ModInstances.getToggleSprint().isEnabled = false;
            }
            if (getIO.loadConfig("oldf3")) {
                new ModTogglerScreen().setEnable(9, true);
                ModInstances.getOldDebug().isEnabled = true;
            } else {
                new ModTogglerScreen().setEnable(9, false);
                ModInstances.getOldDebug().isEnabled = false;
            }
            if (getIO.loadConfig("hp")) {
                new ModTogglerScreen().setEnable(10, true);
                ModInstances.getHPDisplay().isEnabled = true;
            } else {
                new ModTogglerScreen().setEnable(10, false);
                ModInstances.getHPDisplay().isEnabled = false;
            }
            if (getIO.loadConfig("rminput")) {
                RawMouseInput.turnOnRMInput();
            }
            options.init();
            particles = SnowfallParticles.create(360);
            initialized = true;
        }
    }
    boolean initialized = false;
    public void start(){
        hudManager = HUDManager.getInstance();
        ModInstances.register(hudManager);
    }

    public void shutdown(){
        DiscordEvent.shutdown();
        options.save();
    }
    public DiscordRPCEvent getDiscordRPC(){
        return DiscordEvent;
    }
    public void destroy(){
        // Used for forcing something to be destroyed or stopped
        // ONLY USE WHEN NEEDED!!
        // Just use the safe stop
        /**
         * Un-needed, Unless.....
         */
    }
    public void runSingleplayer(){
        if(Minecraft.getMinecraft().isSingleplayer()) {
            DevyClient.getInstance().getDiscordRPC().update("Playing Singleplayer", "IGN: " + Minecraft.getMinecraft().getSession().getUsername());
            runUpdateCheck();
        }
    }

    /**
     * Resets the update checker<br>
     * Parameters: reset<br>
     * if Parameter Reset = false<br>
     * then the update checker will be reset<br>
     * else it wont reset<br>
     */
    public void setUpdateChecked(boolean reset){
        if(!reset){
            isUpdateChecked = false;
        }else{
            isUpdateChecked = true;
        }
    }
    public void runUpdateCheck(){
        if(isUpdateChecked == false) {
            if (new UpdateCheckEvent().isLatest() == false) {
                // If current version is NOT the latest version
                String newVersion = UpdateCheckThread.getVersionNumber();
                isUpdateChecked = true;
                if(newVersion == "Version.Number.Latest"){
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Unable to check for updates, Please check your internet connection"));
                    return;
                }
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("A New update is available, Please use the installer to update"));
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Current Version: " + EnumChatFormatting.RED + VersionString.verSimple));
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Latest Version: " + EnumChatFormatting.GREEN + UpdateCheckThread.getVersionNumber()));
            } else if (new UpdateCheckEvent().isLatest() == true) {
                // If the current version IS the latest version
                isUpdateChecked = true;
            } else {
                // If check fails or somehow updatecheckevent doesnt exists for some reason
                Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Failed to check updates"));
            }
        }
    }
    @EventTarget
    public void onTick(ClientTickEvent e){
        if(MODPOSGUI != null && MODPOSGUI.isPressed()){
            hudManager.openMenuScreen();
        }
        Display.setTitle(VersionString.titleVer);
    }
    public void setKeybind(KeyBinding key, String type){
        if(type.contains("POS")) {
            this.MODPOSGUI = key;
        }
        if(type.contains("PERSPECTIVE")){
            this.PRSPCTVMOD = key;
        }
    }
    public static void oldmenu(Boolean start){
        if(start) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiMainMenu());
            DevyClient.getInstance().StartOldMenu = true;
        }else if (!start){
            DevyClient.getInstance().StartOldMenu = false;
        }
    }
    public boolean StartOldMenu = false;
    public SessionMod modifyableSession;
    public TextureManager renderEngine;
    public void initEarly(GameConfiguration gameConfiguration, SessionMod session) {
        logger.info("[Session Changer Mod] Loading session changer");
        session.changeSession(gameConfiguration.userInfo.session.getUsername(), gameConfiguration.userInfo.session.getPlayerID(), gameConfiguration.userInfo.session.getToken(), "mojang");
        logger.info("[Session Changer Mod] User has been successfully changed to the given args");
        logger.info("[Session Changer Mod] aka: your default launchers account");
        modifyableSession = session;
        Keyboard.enableRepeatEvents(true);
        logger.info("[LWJGL] Enable Repeat Events has been set to [TRUE]");
    }
    boolean prevDown = false;
    protected Minecraft mc = Minecraft.getMinecraft();
    public void tick() {
        boolean down = GameSettings.isKeyDown(PRSPCTVMOD);
        if(down != prevDown && mc.currentScreen == null && mc.theWorld != null && mc.thePlayer != null){
            prevDown = down;
            ModInstances.getPerspective().startPerspective(down);
        }
    }
    public void EarlyTick(){

    }
    public SnowfallParticles particles;
    public static void drawClientBackground(boolean forceDark, int width, int height){
        if (Calendar.getInstance().get(Calendar.MONTH) == Calendar.DECEMBER){
            Date data = new Date();
            if(forceDark){
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("and1558/images/bgseasonal/bg_winter_snowy.png"));
                Gui.drawModalRectWithCustomSizedTexture(-21 + Mouse.getX() / 90, Mouse.getY() * -1 / 90, 0.0f, 0.0f, width + 20, height + 20, (float)(width + 21), (float)(height + 20));
                if(DevyClient.getInstance().particles != null && DevyClient.options.snowParticles) DevyClient.getInstance().particles.render(false);
            }else{
                if(data.getHours() < 6 || data.getHours() > 14){
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("and1558/images/bgseasonal/bg_winter_snowy.png"));
                    Gui.drawModalRectWithCustomSizedTexture(-21 + Mouse.getX() / 90, Mouse.getY() * -1 / 90, 0.0f, 0.0f, width + 20, height + 20, (float)(width + 21), (float)(height + 20));
                    if(DevyClient.getInstance().particles != null && DevyClient.options.snowParticles) DevyClient.getInstance().particles.render(false);
                }else{
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("and1558/images/bgseasonal/bg_winter_sun.png"));
                    Gui.drawModalRectWithCustomSizedTexture(-21 + Mouse.getX() / 90, Mouse.getY() * -1 / 90, 0.0f, 0.0f, width + 20, height + 20, (float)(width + 21), (float)(height + 20));
                }
            }
        }else{
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("and1558/images/bg.jpg"));
            Gui.drawModalRectWithCustomSizedTexture(-21 + Mouse.getX() / 90, Mouse.getY() * -1 / 90, 0.0f, 0.0f, width + 20, height + 20, (float)(width + 21), (float)(height + 20));
        }
    }
    boolean notifDone = false;
    boolean notifRDone = false;
    String notifMessage = "";
    Animation slideDownAnim;
    public void renderNotifs() {
        if(notifRDone) {
            slideDownAnim = new Animation(1l,10,-40, Easing.EASE_IN_QUINT);
            return;
        }
        float y = 10;
        if(!notifDone){
            new Thread(() -> {
                slideDownAnim = new Animation(877l,-40,10, Easing.EASE_OUT_QUINT);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                slideDownAnim = new Animation(877l,10,-40, Easing.EASE_IN_QUINT);
                try {
                    Thread.sleep(877l);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                notifRDone = true;
            }).start();
            notifDone=true;
        }
        if(!notifRDone) {
            if (slideDownAnim == null) y = 10;
            else y = slideDownAnim.getValue();
            ScaledResolution res = new ScaledResolution(mc);
            float width = mc.fontRendererObj.getStringWidth(notifMessage) * 1.5f + 32;
            float x = ((float) res.getScaledWidth() / 2) - (width / 2);
            int height = 32;
            owo.aydendevy.Renderer.GuiUtils.drawRoundedRect(x, y, x + width, y + height, 7, 0x99000000);
            GL11.glPushMatrix();
            GL11.glScalef(1.5f, 1.5f, 0);
            mc.fontRendererObj.drawStringWithShadow(notifMessage, x / 1.5f + 10, y * 1.3f, -1);
            GL11.glPopMatrix();
        }
    }
    public void sendNotif(String message) {
        new Thread(() ->{
            while(!notifRDone){
                // wait and do nothing, this will prevent notification glitching because of conflicts
                // Sleep the current notification thread.
                // prevents cpu from spiking to 100% if alot of notifications are in queue
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            notifMessage = message;
            notifDone = false;
            notifRDone = false;
        }).start();
    }
}
