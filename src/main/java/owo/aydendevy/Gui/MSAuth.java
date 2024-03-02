package owo.aydendevy.Gui;

import owo.aydendevy.Plugins.mcpcba.SessionUtils;

public class MSAuth {

    //String refreshToken = "";
    public void authenticate(MSAuthGUI gui) {
        /**Minecraft minecraft = Minecraft.getMinecraft();
        MicrosoftAuthenticator auth = new MicrosoftAuthenticator();
        try {
            MicrosoftAuthResult result = auth.loginWithWebview();
            if(result == null) throw new RuntimeException("NULL");
            and1558.logger.info("Profile : " + result.getProfile());
            and1558.logger.info("Access Token : [CENSORED]");
            and1558.logger.info("Refresh Token : [CENSORED]");
            and1558.logger.info("Current Profile : " + minecraft.getSession().getProfile());
            and1558.logger.info("WARNING: DO NOT GIVE REFRESH TOKEN TO ANYONE!");
            and1558.logger.info("IT WILL ALLOW THEM TO LOGIN INTO YOUR ACCOUNT!");
            and1558.logger.info("Saving Refresh Token to token.txt");
            refreshToken = result.getRefreshToken();
            and1558.getIO.saveCredsJson(refreshToken);
            MinecraftProfile mcprofile = result.getProfile();
            //((MixinCraft) Minecraft.getMinecraft()).setSession(new Session(result.getProfile().getName(),result.getProfile().getId(), result.getAccessToken(), "mojang"));
            and1558.getInstance().modifyableSession.changeSession(mcprofile.getName(), mcprofile.getId(), result.getAccessToken(), "mojang");
        } catch (MicrosoftAuthenticationException e) {
            throw new MicrosoftAuthenticationException(e);
        }**/
        try {
            SessionUtils.tryLoginBrowser(gui);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**public static void saveToken(String url){
        and1558.getIO.saveCredsJson(url);
    }**/
    /**public void authenticateWithRToken(String token) throws MicrosoftAuthenticationException{
        Minecraft minecraft = Minecraft.getMinecraft();
        MicrosoftAuthenticator auth = new MicrosoftAuthenticator();
        MicrosoftAuthResult result = auth.loginWithRefreshToken(token);
        and1558.logger.info("Profile : " + result.getProfile());
        and1558.logger.info("Access Token : [CENSORED]");
        and1558.logger.info("Refresh Token : [CENSORED]");
        and1558.logger.info("Current Profile : " + minecraft.getSession().getProfile());
        and1558.logger.info("WARNING: DO NOT GIVE REFRESH TOKEN TO ANYONE!");
        and1558.logger.info("IT WILL ALLOW THEM TO LOGIN INTO YOUR ACCOUNT!");
        and1558.logger.info("Saving Refresh Token to creds.json");
        refreshToken = result.getRefreshToken();
        and1558.getIO.saveCredsJson(refreshToken);
        MinecraftProfile mcprofile = result.getProfile();
        //((MixinCraft) Minecraft.getMinecraft()).setSession(new Session(result.getProfile().getName(),result.getProfile().getId(), result.getAccessToken(), "mojang"));
        and1558.getInstance().modifyableSession.changeSession(mcprofile.getName(), mcprofile.getId(), result.getAccessToken(), "mojang");
    }**/
}
