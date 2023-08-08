package uk.to.and1558.Gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import uk.to.and1558.Plugins.ReflectionUtils;
import uk.to.and1558.Plugins.openauth.microsoft.MicrosoftAuthResult;
import uk.to.and1558.Plugins.openauth.microsoft.MicrosoftAuthenticationException;
import uk.to.and1558.Plugins.openauth.microsoft.MicrosoftAuthenticator;
import uk.to.and1558.Plugins.openauth.microsoft.model.response.MinecraftProfile;
import uk.to.and1558.and1558;
import uk.to.and1558.mixins.client.MixinCraft;

import java.lang.reflect.Field;

public class MSAuth {
    private static final Field sessionField = ReflectionUtils.findObfuscatedField(Minecraft.class, "field_71449_j", "session");
    public void authenticate() throws MicrosoftAuthenticationException {
        Minecraft minecraft = Minecraft.getMinecraft();
        MicrosoftAuthenticator auth = new MicrosoftAuthenticator();
        try {
            MicrosoftAuthResult result = auth.loginWithWebview();
            if(result == null) throw new RuntimeException("NULL");
            and1558.logger.info("Profile : " + result.getProfile());
            and1558.logger.info("Access Token : [CENSORED]");
            and1558.logger.info("Refresh Token : [CENSORED]");
            and1558.logger.info("Current Profile : " + minecraft.getSession().getProfile());
            MinecraftProfile mcprofile = result.getProfile();
            //((MixinCraft) Minecraft.getMinecraft()).setSession(new Session(result.getProfile().getName(),result.getProfile().getId(), result.getAccessToken(), "mojang"));
            and1558.getInstance().modifyableSession.changeSession(mcprofile.getName(), mcprofile.getId(), result.getAccessToken(), "mojang");
        } catch (MicrosoftAuthenticationException e) {
            throw new MicrosoftAuthenticationException(e);
        }
    }
}
