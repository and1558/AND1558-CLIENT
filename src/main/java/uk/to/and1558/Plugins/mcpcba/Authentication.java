package uk.to.and1558.Plugins.mcpcba;

import org.json.JSONObject;
import uk.to.and1558.Plugins.mcpcba.http.HttpClient;
import uk.to.and1558.Plugins.mcpcba.http.HttpRequest;
import uk.to.and1558.Plugins.mcpcba.http.HttpResponse;
import uk.to.and1558.and1558;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class Authentication {
    
    // we are using essential's client id as we need to apply for a forum so minecraft verifies your azure app client id, so to prevent that process we are using a one that is allready accepted
    public static final String CLIENT_ID = "e39cc675-eb52-4475-b5f8-82aaae14eeba";
    public static final String REDIRECT_URI = "http://localhost:6921/microsoft/complete";

    /**
    * Retreives a minecraft access token that is then returned back as a String
    *
    * @param mscode       The microsoft code provided by our WebServer
    * @param recentPkce   The pkce/Proof Key for Code Exchange that we generated earlier
    */
    public String retrieveAccessToken(String mscode, String recentPkce) {
    	try {
    		// This sends a POST request to get microsoft account's token
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://login.live.com/oauth20_token.srf"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(
                    "client_id=" + CLIENT_ID +
                    "&code=" + mscode +
                    "&scope=Xboxlive.signin+Xboxlive.offline_access" +
                    "&code_verifier=" + recentPkce +
                    "&redirect_uri=http://localhost:6921/microsoft/complete" +
                    "&grant_type=authorization_code"))
                .build();

            HttpResponse response = client.send(request);
            JsonReader jsonReader = Json.createReader(new StringReader(response.body()));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            // Retreive ms account token, this will be useful to login to xbox
            String accessTokenToLive = jsonObject.getString("access_token");
            
            // This sends a POST request to xboxlive.com to retreive the user's xbox live token, this is used to get the xsts token
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                .uri(new URI("https://user.auth.xboxlive.com/user/authenticate"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                    "{\"Properties\":{\"AuthMethod\":\"RPS\",\"SiteName\":\"user.auth.xboxlive.com\",\"RpsTicket\":\"d=" + accessTokenToLive + "\"},\"RelyingParty\":\"http://auth.xboxlive.com\",\"TokenType\":\"JWT\"}"))
                .build();
            
            response = client.send(request);
            jsonReader = Json.createReader(new StringReader(response.body()));
            jsonObject = jsonReader.readObject();
            jsonReader.close();

            // Store xbl auth token & user hash
            String xblAuthToken = jsonObject.getString("Token");
            String userHashString = jsonObject
            		.getJsonObject("DisplayClaims")
            	    .getJsonArray("xui")
            	    .getJsonObject(0)
            	    .getString("uhs");

            // This sends a POST request xboxlive.com in order to retreive the xsts token
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                .uri(new URI("https://xsts.auth.xboxlive.com/xsts/authorize"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                    "{\"Properties\":{\"SandboxId\":\"RETAIL\",\"UserTokens\":[\"" + xblAuthToken + "\"]},\"RelyingParty\":\"rp://api.minecraftservices.com/\",\"TokenType\":\"JWT\"}"))
                .build();
            
            response = client.send(request);
            jsonReader = Json.createReader(new StringReader(response.body()));
            jsonObject = jsonReader.readObject();
            jsonReader.close();

            // Store xsts token for the final step
            String xstsToken = jsonObject.getString("Token");

            // This sends a request to minecraftservices.com in order to get the final access token to minecraft, this access token lasts 24 hours
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                .uri(new URI("https://api.minecraftservices.com/authentication/login_with_xbox"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                    "{\"identityToken\":\"XBL3.0 x=" + userHashString + ";" + xstsToken + "\",\"ensureLegacyEnabled\":\"true\"}"))
                .build();
            
            response = client.send(request);
            jsonReader = Json.createReader(new StringReader(response.body()));
            jsonObject = jsonReader.readObject();
            jsonReader.close();

            // We got the access token!
            String minecraftAccessToken = jsonObject.getString("access_token");
            return minecraftAccessToken;
    	} catch (Exception e) {
    		return e.getMessage();
    	}
    }
    
    /**
    * Fetches account info. Needed for the new session instance. This returns the JSON data that got returned after the GET request.
    *
    * @param accessToken  Minecraft authentication token provided from the retrieveAccessToken() function
    */
    public static JsonObject getAccountInfo(String accessToken){
        // This creates the request body needed in order to fetch the account info
        and1558.logger.info("========== Microsoft Login Debug ==========");
        and1558.logger.info("[MSLOGIN] Starting HTTP Client");
        HttpClient client = HttpClient.newHttpClient();
        and1558.logger.info("[MSLOGIN] Building HTTP Request");
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.minecraftservices.com/minecraft/profile"))
            .header("Authorization", "Bearer " + accessToken)
            .GET()
            .build();
        if(SessionUtils.printJsonInfo){
            and1558.logger.info("ACCESSTOKEN (DO NOT SHARE): " + accessToken);
        }
        // We to send the request & if it throws an error, return null, if it does not then return the request response
        try {
            and1558.logger.info("[MSLOGIN] Sending HTTP Request");
            // Send the request
            HttpResponse response = client.send(request);
            if(SessionUtils.printJsonInfo) {
                and1558.logger.info(response.body());
            }
            and1558.logger.info("[MSLOGIN] Recevied a respond of " + response.statusCode() + "! Parsing into JSON...");
            if(response.statusCode() == 401){
                and1558.logger.info("FAILED TO LOGIN, RECEIVED 401!");
                and1558.logger.info("THIS CAN BE CAUSED BY PKIX ERROR ON JAVA DUE TO MISSING CERTIFICATES");
                and1558.logger.info("TRY USING ANOTHER JAVA INSTALLATION");
                and1558.logger.info("Developer Recommends Liberica JRE 8 or any Java 8 Version above 112");
            }
            // Read & parse the json data provided, this is where it could error
            JsonReader jsonReader = Json.createReader(new StringReader(response.body()));
            and1558.logger.info("[MSLOGIN] Parsing into JSON... Stage 2");
            JsonObject jsonObject = jsonReader.readObject();
            and1558.logger.info("[MSLOGIN] Finished Parsing! Closing JSONReader");
            jsonReader.close();
            if(SessionUtils.printJsonInfo){
                and1558.logger.info("=====Result of jsonReader=====");
                and1558.logger.info(jsonReader);
                and1558.logger.info("==============================");
            }
            and1558.logger.info("===========================================");
            return jsonObject;
        } catch (Exception e) {
            and1558.logger.info("Exception Thrown!");
            and1558.logger.info("Exception type: " + e.getMessage());
            e.printStackTrace();
            and1558.logger.info("===========================================");
            if(e.getMessage().contains("401")){
                JSONObject json = new JSONObject();

                json.put("errortype", "http401");
                JsonReader jsonReader = Json.createReader(new StringReader(json.toString()));
                JsonObject json1 = jsonReader.readObject();
                return json1;
            }
            return null;
        }
    }
}
