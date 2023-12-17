package uk.to.and1558;

public class ClientOptions {
    public boolean darkMode;
    public void init(){
        load();
    }
    public void load(){
        darkMode = and1558.getIO.loadMultipleConfig("darkMode");
    }
    public void save(String key, Object value){
        and1558.getIO.saveMultipleConfig(key, value);
    }

    public void saveAll() {
        and1558.getIO.saveMultipleConfig("darkMode", darkMode);
    }
}
