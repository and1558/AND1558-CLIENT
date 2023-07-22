package uk.to.and1558.Mods.ModLoader;

import uk.to.and1558.Gui.HUD.HUDManager;
import uk.to.and1558.Mods.*;

public class ModInstances {

    private static ModBPS bps = new ModBPS();
    private static ModKeystrokes cps = new ModKeystrokes();
    private static ModArmorView armor = new ModArmorView();
    private static ModPlayerView player = new ModPlayerView();
    private static ModPing ping = new ModPing();
    private static ModLowFire lfire = new ModLowFire();
    private static OldAnimations oldanim = new OldAnimations();
    private static DisableAchievements achievements = new DisableAchievements();
    private static ModItemPhysics itemPhysics = new ModItemPhysics();
    private static ModToggleSprint toggleSprint = new ModToggleSprint();
    private static ModOldDebug oldDebug = new ModOldDebug();
    private static ModZoomNoOptifine zoom = new ModZoomNoOptifine();
    private static RawMouseInput rawInput = new RawMouseInput();
    private static HP hp = new HP();
    private static PerspectiveMod modPerspective = new PerspectiveMod();
    private static ModComboCounter comboCounter = new ModComboCounter();
    private static TNTCounter tntCounter = new TNTCounter();

    public static void register(HUDManager api) {
        api.register(cps);
        api.register(lfire);
        api.register(bps);
        api.register(armor);
        api.register(ping);
        api.register(oldanim);
        api.register(achievements);
        api.register(itemPhysics);
        api.register(toggleSprint);
        api.register(oldDebug);
        api.register(zoom);
        api.register(hp);
        api.register(rawInput);
        api.register(modPerspective);
        api.register(comboCounter);
        api.register(tntCounter);
        //api.register(player); - UNFINISHED/BUGGY
    }
    public static HP getHPDisplay() { return hp; }
    public static ModComboCounter getComboCounter() { return comboCounter; }
    public static TNTCounter getTntCounter() { return tntCounter; }
    public static ModLowFire getLfire(){
        return lfire;
    }
    public static OldAnimations getOldanim() {
        return oldanim;
    }
    public static DisableAchievements getAchievements() { return achievements; }
    public static ModItemPhysics getItemPhysics() {return itemPhysics; }
    public static ModOldDebug getOldDebug() { return oldDebug; }
    public static ModKeystrokes getKeystrokes() { return cps; }
    public static ModBPS getSpeedCounter() { return bps; }
    public static ModPing getPingCounter() { return ping; }
    public static ModArmorView getArmorView() { return armor; }
    public static ModToggleSprint getToggleSprint() { return toggleSprint; }
    public static ModZoomNoOptifine getZoom() { return zoom; }
    public static RawMouseInput getRawInput() { return rawInput; }
    public static PerspectiveMod getPerspective() { return modPerspective;}
}
