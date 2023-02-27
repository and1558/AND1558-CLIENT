package uk.to.and1558.Events;

import uk.to.and1558.Events.impl.UpdateCheckThread;
import uk.to.and1558.VersionString;

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
