package uk.to.and1558.Mods.ModLoader;

import com.google.gson.Gson;
import org.lwjgl.Sys;
import uk.to.and1558.Maxytreal123.Configuration;
import uk.to.and1558.Maxytreal123.ConfigurationAPI;
import uk.to.and1558.and1558;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    // Testing!!!
    public void createKey(Object value, String modjson) {
        configSaving = ConfigurationAPI.newConfiguration(new File(getJsonFolder(), "mod.cfg"));
        configSaving.set(modjson, value); // The Value can be set anything from string to boolean, int, float, double, long, etc

        try {
            configSaving.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    String content;
    public boolean loadConfig(String modjson) {
        try {
            content = new ConfigurationAPI().readFile(new File(getJsonFolder(), modjson + "_config" + ".cfg"));
        } catch (IOException e) {
            //e.printStackTrace();
            and1558.logger.error("The following "+e.getMessage());
            and1558.logger.error("Saving mod with default value!");
            and1558.logger.error("This is not a bug! its just that you haven't changed this settings");
            configSaving = ConfigurationAPI.newConfiguration(new File(getJsonFolder(), modjson + "_config" + ".cfg"));
            configSaving.set("Enabled", true); // The Value can be set anything from string to boolean, int, float, double, long, etc
            try {
                configSaving.save();
            }catch (Exception e2){
                and1558.logger.error("Failed to save: " + e.getMessage());
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
    // Testing!!!
    String contentNew="";
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
