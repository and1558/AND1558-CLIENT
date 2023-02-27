package uk.to.and1558.Events.impl;

import net.minecraft.client.Minecraft;
import org.lwjgl.Sys;
import uk.to.and1558.Plugins.FileHelper;
import uk.to.and1558.and1558;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class UpdateCheckThread{
    private static final String BASE_URL = "https://raw.githubusercontent.com/and1558/ClientUpdates/main/";
    private static String versionNumber = null;
    public static String getVersionNumber() {
        if (versionNumber == null) {
            try {
                InputStream stream = FileHelper.getStreamFromUrl(BASE_URL + "dev.txt");
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader buffReader = new BufferedReader(reader);
                versionNumber = buffReader.readLine();
                buffReader.close();
                reader.close();
                stream.close();
            } catch (Exception e) {
                // TODO: handle exception better
                e.printStackTrace();
                and1558.logger.error("UNABLE TO CHECK FOR UPDATES!!");
                return "Version.Number.Latest";
            }
        }
        System.out.println(versionNumber);
        return versionNumber;
    }
}
