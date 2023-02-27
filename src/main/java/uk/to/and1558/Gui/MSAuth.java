package uk.to.and1558.Gui;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.microsoft.model.response.MinecraftProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import uk.to.and1558.Plugins.ReflectionUtils;
import uk.to.and1558.and1558;
import uk.to.and1558.mixins.client.MixinCraft;

import java.lang.reflect.Field;

public class MSAuth {
    private static final Field sessionField = ReflectionUtils.findObfuscatedField(Minecraft.class, "field_71449_j", "session");
    public void authenticate(String email, String pass) throws MicrosoftAuthenticationException {
        Minecraft minecraft = Minecraft.getMinecraft();
        MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
        MicrosoftAuthResult result = authenticator.loginWithCredentials(email, pass);
        and1558.logger.info("Profile : " + result.getProfile());
        and1558.logger.info("Access Token : [CENSORED]");
        and1558.logger.info("Refresh Token : [CENSORED]");
        and1558.logger.info("Current Profile : " + minecraft.getSession().getProfile());
        MinecraftProfile mcprofile = result.getProfile();
        //((MixinCraft) Minecraft.getMinecraft()).setSession(new Session(result.getProfile().getName(),result.getProfile().getId(), result.getAccessToken(), "mojang"));
        and1558.getInstance().modifyableSession.changeSession(mcprofile.getName(), mcprofile.getId(), result.getAccessToken(), "mojang");
    }
}
