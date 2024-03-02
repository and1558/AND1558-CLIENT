package owo.aydendevy.Events;

import owo.aydendevy.Events.impl.UpdateCheckThread;
import owo.aydendevy.VersionString;

public class UpdateCheckEvent {

    public String currentVersion = VersionString.verSimple;

    public boolean isLatest(){
        if (UpdateCheckThread.getVersionNumber().equals(currentVersion)) {
            return true;
        }else{
            return false;
        }
    }
}
