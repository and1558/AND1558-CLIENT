package uk.to.and1558.Plugins;

import com.mojang.authlib.GameProfile;
import com.mojang.util.UUIDTypeAdapter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import uk.to.and1558.and1558;

import java.util.UUID;

public class SessionMod extends Session {
    private String username;
    private String playerID;
    private String token;
    private Session.Type sessionType;
    public SessionMod(String usernameIn, String playerIDIn, String tokenIn, String sessionTypeIn) {
        super(usernameIn, playerIDIn, tokenIn, sessionTypeIn);
    }
    String UUID = "000000-000-000-00000";
    public void changeSession(String usernameIn, String playerIDIn, String tokenIn, String sessionTypeIn){
        this.username = usernameIn;
        this.playerID = playerIDIn;
        this.token = tokenIn;
        this.sessionType = Session.Type.setSessionType(sessionTypeIn);
        this.UUID = UUID;
        showChangetoLog();
    }
    // VALUE SHOULD ALWAYS BE FALSE UNLESS ITS USED FOR DEBUGGING PURPOSES
    public static boolean showToken = false;
    public void showChangetoLog(){
        and1558.logger.info("-----------------Session Changed-------------------");
        and1558.logger.info("Successfully changed session to the given arguments");
        and1558.logger.info("Username : " + username);
        if(showToken){
            and1558.logger.info("Access Token : " + token);
        }else{
            and1558.logger.info("Access Token : [CENSORED]");
        }
        and1558.logger.info("UUID : " + UUID);
        and1558.logger.info("---------------------------------------------------");
    }
    public String getSessionID()
    {
        return "token:" + this.token + ":" + this.playerID;
    }

    public String getPlayerID()
    {
        return this.playerID;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getToken()
    {
        return this.token;
    }

    public GameProfile getProfile()
    {
        try
        {
            UUID uuid = UUIDTypeAdapter.fromString(this.getPlayerID());
            return new GameProfile(uuid, this.getUsername());
        }
        catch (IllegalArgumentException var2)
        {
            return new GameProfile((UUID)null, this.getUsername());
        }
    }
}
