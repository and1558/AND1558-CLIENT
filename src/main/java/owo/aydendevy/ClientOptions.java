package owo.aydendevy;

public class ClientOptions {
    public boolean darkMode;
    public boolean customBackground;
    public String customBackgroundPath;
    public boolean snowParticles;
    public boolean snowParticlesGUI;
    public boolean blurEffect;
    public boolean blurEffectGUI;
    public boolean mcBlurMethod;
    public void init(){
        load();
    }
    public void load(){
        darkMode = DevyClient.getIO.loadMultipleConfig("darkMode");
        customBackground = DevyClient.getIO.loadMultipleConfig("customBackground");
        customBackgroundPath = DevyClient.getIO.loadMultipleConfigS("customBackgroundPath");
        snowParticles = DevyClient.getIO.loadMultipleConfig("snowParticles");
        snowParticlesGUI = DevyClient.getIO.loadMultipleConfig("snowParticlesGUI");
        blurEffect = DevyClient.getIO.loadMultipleConfig("blurEffect");
        blurEffectGUI = DevyClient.getIO.loadMultipleConfig("blurEffectGUI");
        mcBlurMethod = DevyClient.getIO.loadMultipleConfig("mcBlurMethod");
    }

    public void save() {
        DevyClient.getIO.saveMultipleConfig(this);
    }
}
