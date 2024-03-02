package owo.aydendevy.Plugins.mcpcba;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import net.minecraft.util.Session;
import owo.aydendevy.Gui.MSAuthGUI;
import owo.aydendevy.DevyClient;
import javax.json.JsonObject;

public class SessionUtils {

	static String recentPkce;
	
	/**
    * Sets the minecraft session to the provided argument session, you may need to set "session" to public
    *
    * @param session      Session instance from net.minecraft.util.Session
    */
	public static void setSession(Session session) {
		DevyClient.INSTANCE.modifyableSession.changeSession(session.getUsername(), session.getPlayerID(), session.getToken(), session.getSessionType().toString());
	}
	
	/**
    * Generates Proof Key for Code Exchange or a PKCE
    *
	* @return random PKCE
    */
	public static String generatePKCE() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] codeVerifierBytes = new byte[32];
		secureRandom.nextBytes(codeVerifierBytes);
		String codeVerifier = Base64.encodeBase64URLSafeString(codeVerifierBytes);

		return codeVerifier;
	}

	
	/**
	* Tries to login using browser
	* 
	* @throws IOException If an I/O error occurs during the process.
 	* @throws URISyntaxException If an URI Syntax error occurs during the process.
    */
	public static void tryLoginBrowser(MSAuthGUI gui) throws IOException, URISyntaxException {
		recentPkce = generatePKCE();
		
		WebServer.initWebServer(gui);
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			Desktop.getDesktop()
					.browse(new URI("https://login.live.com/oauth20_authorize.srf?"
							+ "client_id=" + Authentication.CLIENT_ID + "&prompt=select_account"
							+ "&scope=offline_access+Xboxlive.signin+Xboxlive.offline_access" + "&code_challenge_method=S256"
							+ "&code_challenge=" + Base64.encodeBase64URLSafeString(DigestUtils.sha256(recentPkce))
							+ "&response_type=code" + "&redirect_uri=" + Authentication.REDIRECT_URI));
		} else {
			WebServer.server.stop(0);
		}
	}

	/**
    * Called when the webserver got the response with the code.
	* 
	* @param code          the code provided from login.live.com/oauth20_authorize.srf
	* @return A status string you can put on your GUI
    */
	public static boolean printJsonInfo = false;
	public static String recieveResponse(String code) {
		try {
			if(printJsonInfo){
				DevyClient.logger.info("==========================================================");
				DevyClient.logger.info("WARNING! printJsonInfo is set to TRUE");
				DevyClient.logger.info("CONSOLE MAY OR MAY NOT OUTPUT SENSITIVE INFORMATION!");
			}
			DevyClient.logger.info("Getting access token");
			String accessToken = new Authentication().retrieveAccessToken(code,recentPkce);
			DevyClient.logger.info("Getting profile info");
			JsonObject loginProfileInfo = Authentication.getAccountInfo(accessToken);
			if(loginProfileInfo.containsValue("http401")){
				return "Failed to Login! Try changing to another Java Installation!";
			}
			if(printJsonInfo){
				DevyClient.logger.info("=======RESULT OF loginProfileInfo=======");
				DevyClient.logger.info(loginProfileInfo);
				DevyClient.logger.info("========================================");
				DevyClient.logger.info("==========================================================");
			}
			DevyClient.logger.info("Getting profile name");
            String name = loginProfileInfo.getString("name");
			DevyClient.logger.info("Getting profile id");
			String id = loginProfileInfo.getString("id");
			DevyClient.logger.info("Found all needed info!");
			DevyClient.logger.info("Changing session to selected profile!");
			setSession(new Session(name, id, accessToken, "legacy"));
			DevyClient.logger.info("Success");
			return "Logged in successfully as " + name +"!";
		} catch (Exception e) {
			DevyClient.logger.info("==========FAILED TO LOGIN==========");
			e.printStackTrace();
			DevyClient.logger.info("===================================");
			return "Could not log-in. Try using another Java Installation!";
		}
	}
}
