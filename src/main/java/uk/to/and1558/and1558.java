package uk.to.and1558;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import uk.to.and1558.DiscordRP.DiscordRPCEvent;
import uk.to.and1558.Events.EventManager;
import uk.to.and1558.Events.EventTarget;
import uk.to.and1558.Events.UpdateCheckEvent;
import uk.to.and1558.Events.impl.ClientTickEvent;
import uk.to.and1558.Events.impl.UpdateCheckThread;
import uk.to.and1558.Gui.HUD.HUDManager;
import uk.to.and1558.Gui.ModTogglerScreen;
import uk.to.and1558.Gui.SplashScreen;
import uk.to.and1558.IO.FileIOManager;
import uk.to.and1558.Mods.ModLoader.ModIO;
import uk.to.and1558.Mods.ModLoader.ModInstances;
import uk.to.and1558.Mods.RawMouseInput;
import uk.to.and1558.Plugins.AnimationHandler;
import uk.to.and1558.Gui.impl.GuiUtils;
import uk.to.and1558.Plugins.ClientAnimations.Animation;
import uk.to.and1558.Plugins.ClientAnimations.Easing;
import uk.to.and1558.Plugins.SessionMod;

public class and1558 {
    public static final Logger logger = LogManager.getLogger();
    public static final and1558 INSTANCE = new and1558();
    public boolean started = false;
    public static final and1558 getInstance() { return INSTANCE; }
    // dev-1.82. Added animations for GuiUtils and made it accessible here
    public static GuiUtils guiUtils = new GuiUtils();
    private DiscordRPCEvent DiscordEvent = new DiscordRPCEvent();
    private HUDManager hudManager;
    public KeyBinding MODPOSGUI;
    public KeyBinding PRSPCTVMOD;
    public static ModIO getIO = new ModIO();
    private boolean isUpdateChecked = false;
    public float partialTicks;
    public void setPartialTicks(float partialTicks1){
        partialTicks = partialTicks1;
    }
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
        this.logger.info("Initializing Mods");
        if (getIO.loadConfig("Keystrokes") == true) {
            new ModTogglerScreen().setEnable(0, true);
            ModInstances.getKeystrokes().isEnabled = true;
        } else {
            new ModTogglerScreen().setEnable(0, false);
            ModInstances.getKeystrokes().isEnabled = false;
        }
        if (getIO.loadConfig("bps") == true) {
            new ModTogglerScreen().setEnable(1, true);
            ModInstances.getSpeedCounter().isEnabled = true;
        } else {
            new ModTogglerScreen().setEnable(1, false);
            ModInstances.getSpeedCounter().isEnabled = false;
        }
        if (getIO.loadConfig("ping") == true) {
            new ModTogglerScreen().setEnable(2, true);
            ModInstances.getPingCounter().isEnabled = true;
        } else {
            new ModTogglerScreen().setEnable(2, false);
            ModInstances.getPingCounter().isEnabled = false;
        }
        if (getIO.loadConfig("lowfire") == true) {
            new ModTogglerScreen().setEnable(3, true);
            ModInstances.getLfire().isEnabled = true;
        } else {
            new ModTogglerScreen().setEnable(3, false);
            ModInstances.getLfire().isEnabled = false;
        }
        if(getIO.loadConfig("oldanimations") == true){
            new ModTogglerScreen().setEnable(4, true);
            ModInstances.getOldanim().isEnabled = true;
        }else{
            new ModTogglerScreen().setEnable(4, false);
            ModInstances.getOldanim().isEnabled = false;
        }
        if(getIO.loadConfig("perspective") == true){
            new ModTogglerScreen().setEnable(6, true);
            ModInstances.getPerspective().isEnabled = true;
        }else{
            new ModTogglerScreen().setEnable(6, false);
            ModInstances.getPerspective().isEnabled = false;
        }
        if (getIO.loadConfig("armorview") == true) {
            new ModTogglerScreen().setEnable(8, true);
            ModInstances.getArmorView().isEnabled = true;
        } else {
            new ModTogglerScreen().setEnable(8, false);
            ModInstances.getArmorView().isEnabled = false;
        }
        if (getIO.loadConfig("sprinttoggle") == true) {
            new ModTogglerScreen().setEnable(7, true);
            ModInstances.getToggleSprint().isEnabled = true;
        } else {
            new ModTogglerScreen().setEnable(7, false);
            ModInstances.getToggleSprint().isEnabled = false;
        }
        if(getIO.loadConfig("rminput") == true){
            RawMouseInput.turnOnRMInput();
        }
    }
    public void start(){
        hudManager = HUDManager.getInstance();
        ModInstances.register(hudManager);
    }

    public void shutdown(){
        DiscordEvent.shutdown();
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
            and1558.getInstance().getDiscordRPC().update("Playing Singleplayer", "IGN: " + Minecraft.getMinecraft().getSession().getUsername());
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
                isUpdateChecked = true;
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
            and1558.getInstance().StartOldMenu = true;
        }else if (!start){
            and1558.getInstance().StartOldMenu = false;
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
}
