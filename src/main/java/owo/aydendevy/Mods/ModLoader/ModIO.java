package owo.aydendevy.Mods.ModLoader;

import com.google.gson.Gson;
import owo.aydendevy.ClientOptions;
import owo.aydendevy.Maxytreal123.Configuration;
import owo.aydendevy.Maxytreal123.ConfigurationAPI;
import owo.aydendevy.DevyClient;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Map;

public class ModIO {
    public Configuration config, configSaving;
    public void saveConfig(boolean value, String modjson) {
        configSaving = ConfigurationAPI.newConfiguration(new File(getJsonFolder(), modjson + "_config" + ".cfg"));
        configSaving.set("Enabled", value); // The Value can be set anything from string to boolean, int, float, double, long, etc

        try {
            configSaving.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Testing!!!
    public void saveConfigNew(Object value, String modjson) {
        configSaving = ConfigurationAPI.newConfiguration(new File(getJsonFolder(), "mod.cfg"));
        configSaving.set(modjson, value); // The Value can be set anything from string to boolean, int, float, double, long, etc

        try {
            configSaving.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    String content;
    Configuration contentNew;
    public boolean loadConfig(String modjson) {
        try {
            content = new ConfigurationAPI().readFile(new File(getJsonFolder(), modjson + "_config" + ".cfg"));
        } catch (IOException e) {
            //e.printStackTrace();
            DevyClient.logger.error("The following "+e.getMessage());
            DevyClient.logger.error("Saving mod with default value!");
            DevyClient.logger.error("This is not a bug! its just that you haven't changed this settings");
            configSaving = ConfigurationAPI.newConfiguration(new File(getJsonFolder(), modjson + "_config" + ".cfg"));
            configSaving.set("Enabled", true); // The Value can be set anything from string to boolean, int, float, double, long, etc
            try {
                configSaving.save();
            }catch (Exception e2){
                DevyClient.logger.error("Failed to save: " + e.getMessage());
            }

            return true;
        }
        if(content != null) {
            if(content.contains("true")) {
                return true;
            }else {
                return false;
            }
        }else {
            return true;
        }
    }
    // This is a more efficient way of using the IO API, unlike what i did at the top
    // Wow, i was a fucking dumbass back then lmfao
    public boolean loadMultipleConfig(String settingName) {
        try {
            contentNew = new ConfigurationAPI().loadExistingConfiguration(new File(getJsonFolder(), "clientsettings.cfg"));
        } catch (IOException e) {
            //e.printStackTrace();
            loadDefaultValues();
            try {
                configSaving.save();
            }catch (Exception e2){
                DevyClient.logger.error("Failed to save: " + e.getMessage());
            }

            return true;
        }
        try {
            //and1558.logger.info(settingName + " has a value of " + contentNew.get(settingName));
            return contentNew.get(settingName).equals(true);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            contentNew.set(settingName, true);
            return true;
        }
    }
    public void saveMultipleConfig(ClientOptions co) {
        configSaving = ConfigurationAPI.newConfiguration(new File(getJsonFolder(), "clientsettings.cfg"));
        configSaving.set("darkMode", co.darkMode);
        configSaving.set("snowParticles", co.snowParticles);
        configSaving.set("snowParticlesGUI", co.snowParticlesGUI);
        configSaving.set("blurEffect", co.blurEffect);
        configSaving.set("blurEffectGUI", co.blurEffectGUI);
        configSaving.set("mcBlurMethod", co.mcBlurMethod);


        try {
            configSaving.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadDefaultValues() {
        configSaving = ConfigurationAPI.newConfiguration(new File(getJsonFolder(), "clientsettings.cfg"));
        configSaving.set("darkMode", true);
        configSaving.set("snowParticles", true);
        configSaving.set("snowParticlesGUI", true);
        configSaving.set("blurEffect", true);
        configSaving.set("blurEffectGUI", true);
        configSaving.set("mcBlurMethod", true);


        try {
            configSaving.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadNewConfig(){
        
    }
    /**public String loadRToken() {
        try {
            content = new ConfigurationAPI().readFile(new File(getJsonFolder(), "creds.json"));
            JSONObject jsonObject = new JSONObject(content);
            return jsonObject.getString("token");
        } catch (IOException e) {
            //e.printStackTrace();
            and1558.logger.error("The following "+e.getMessage());
            and1558.logger.error("Saving token with a placeholder value!");
            and1558.logger.error("This is not a bug! its just that you haven't logged in using a microsoft account");
            try {
                and1558.getIO.saveCredsJson("refreshtokenhere");
            }catch (Exception e2){
                and1558.logger.error("Failed to save: " + e.getMessage());
            }
        }
        return "refreshtokenhere";
    }**/
    // Testing!!!
    String contentNew1 = "";
    public boolean loadConfigNew(String modType){
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = Files.newBufferedReader(new File(getJsonFolder(), "mod.cfg").toPath());
            System.out.println("[Debug/AND1558/ModIOManager] Path = " + new File(getJsonFolder(), "mod.cfg").toPath());
            System.out.println("[Debug/AND1558/ModIOManager] Looking For = " + modType);
            // convert JSON file to map
            Map<?, ?> map = gson.fromJson(reader, Map.class);

            // print map entries
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (entry.getKey().toString().contains(modType)) {
                    System.out.println("[Debug/AND1558/ModIOManager] Reader has found the key it was looking for!");
                    System.out.println("[Debug/AND1558/ModIOManager] " + entry.getKey() + "=" + entry.getValue());
                    if(entry.getValue() instanceof Boolean){
                        System.out.println("[Debug/AND1558/ModIOManager] the boolean for the key is = " + entry.getValue());
                    }
                    return false;
                }
                System.out.println("[Debug/AND1558/ModIOManager] " + entry.getKey() + "=" + entry.getValue() + " not the value that is being looked at");
            }
            // close reader
            reader.close();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    private File getJsonFolder() {
        File folder = new File(Configuration.getConfigDir(), this.getClass().getSimpleName());
        folder.mkdirs();
        return folder;
    }
}
