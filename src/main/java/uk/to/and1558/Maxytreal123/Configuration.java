package uk.to.and1558.Maxytreal123;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private File file;
    public Map<String, Object> options;
    private static File ROOT = new File("AND1558");
    private static File MODS = new File(ROOT, "84HFJN2GHGNSSF");
    public static void init() {
        if(!MODS.exists()) { MODS.mkdirs(); }
    }
    public static  File getConfigDir() {
        return MODS;
    }
    public Configuration(File file, Map<String, Object> options) {
        this.file = file;
        this.options = options;
    }

    public Configuration(File file) {
        this.file = file;
        this.options = new HashMap<String, Object>();
    }

    public Object get(String key) {
        return options.get(key);
    }

    public void set(String key, Object value) {
        options.put(key, value);
    }

    public void save() throws IOException {
        JSONObject jsonObject = new JSONObject(options);
        if(!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(jsonObject.toString());
        fileWriter.flush();
        fileWriter.close();
    }
}
