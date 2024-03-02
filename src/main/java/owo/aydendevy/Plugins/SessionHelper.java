package owo.aydendevy.Plugins;

public class SessionHelper {
    public static final String clientId = "fa861065-c46c-4ac9-a4da-59a7d40b8a72";
    public static final int port = 52371;
    public static final String redirectUri = "http://127.0.0.1:" + port;
    private static final String redirectUriEncoded = "http%3A%2F%2F127%2E0%2E0%2E1%3A" + port;

    private static final String scopeBasic = "XboxLive.signin";
    private static final String scopePersist = "XboxLive.signin XboxLive.offline_access";
    private static final String scopePersistUrl = "XboxLive.signin+XboxLive.offline_access";

    private static final String urlMicrosoftAuthorize = "https://login.microsoftonline.com/consumers/oauth2/v2.0/authorize";
    private static final String urlMicrosoftToken = "https://login.microsoftonline.com/consumers/oauth2/v2.0/token";
    private static final String urlMicrosoftDevice = "https://login.microsoftonline.com/consumers/oauth2/v2.0/devicecode";
    private static final String urlXboxLive = "https://user.auth.xboxlive.com/user/authenticate";
    private static final String urlXsts = "https://xsts.auth.xboxlive.com/xsts/authorize";
    private static final String urlMojangLogin = "https://api.minecraftservices.com/authentication/login_with_xbox";
    private static final String urlMojangProfile = "https://api.minecraftservices.com/minecraft/profile";
    public static String getLoginUrl(boolean persist, String pkceChallenge) {
        return urlMicrosoftAuthorize +
                "?client_id=" + clientId +
                "&redirect_uri=" + redirectUriEncoded +
                "&scope=" + (persist ? scopePersistUrl : scopeBasic) +
                "&response_type=code" +
                "&response_mode=form_post" +
                "&prompt=select_account" +
                "&code_challenge=" + pkceChallenge +
                "&code_challenge_method=S256";
    }
}
