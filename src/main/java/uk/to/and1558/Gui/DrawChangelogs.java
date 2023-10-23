package uk.to.and1558.Gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import uk.to.and1558.Plugins.ClientAnimations.Animation;
import uk.to.and1558.Plugins.ClientAnimations.Easing;
import uk.to.and1558.VersionString;

import java.util.ArrayList;
import java.util.List;

public class DrawChangelogs {
    public static VersionString ver = new VersionString();
    public static void get(GuiScreen wheretoDraw, FontRenderer fontRendererObj, int posX){
        int y = 22;
        int yAdd = 0;
        wheretoDraw.drawCenteredString(fontRendererObj, EnumChatFormatting.GREEN + ver.ChangelogMainMenu2, wheretoDraw.width / 2, 10, 0);
        List<String> changelog = new ArrayList<>();
        changelog.add("-  Improved credits text Animations");
        changelog.add("-  Disabled HUD Mod from being visible when another menu opens");
        changelog.add("-  Fixed client crashing when trying to login into a Mojang/MS Account");
        changelog.add("-  Fixed client freezing when trying to login");
        changelog.add("-  Fixed client from going to \"Pick Accounts menu\" when logging in on an offline Account");
        changelog.add("-  Removed offensive text on the DiscordRPC when staying in the main menu");
        changelog.add("-  Added more Animations when the Main Menu is opened");
        changelog.add("-  Changed main menu changelog to a smoother one");
        changelog.add("-  Changed text from \"Finally Mixin!\" to \"Welcome {username}\"");
        changelog.add("-  Fixed background button being too small and the text overflowing it on the Mod Screen");
        changelog.add("-  Makes the background square smaller on the Mod Screen");
        changelog.add("-  Added a blur that only blurs whats behind the translucent square background");
        changelog.add("-  Added an animations for GUI that uses a non- full translucent square");
        changelog.add("-  Made Old F3 Toggleable");
        changelog.add("-  Fixed Mod Toggler not checking every mods when opening");
        changelog.add("-  Changed from using Minecraft's default blur shader to a new one");
        changelog.add("-  Fixed HP Text not showing when opening the editor without a world opened");
        changelog.add("-  Fixed Glitched Combo Text when opening the editor");
        changelog.add("-  Fixed Client keybinds resetting after restarting Minecraft");
        changelog.add("-  Changed Microsoft Login method to allow 2FA Accounts");
        changelog.add("-  Made the fire effect lower when using the Low Fire Mod");
        changelog.add("-  Changed 30 FPS Limit on GUI when no world is opened to 60 FPS");
        changelog.add("NOTE: ANY IMPROVEMENTS/FIXES TO BUGS WILL BE ACCEPTED AFTER CHECKED THAT ITS WORKING");
        for (String s : changelog) {
            wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + s, posX + fontRendererObj.getStringWidth("joe") - 0, y + yAdd,0);
            yAdd+=12;
        }
    }

    public static void getLess(GuiScreen wheretoDraw, FontRenderer fontRendererObj, int posX){
        int y = 83;
        int yAdd = 0;
        // dev 1.82 -> Moved changelog text to an array instead of individually making them
        // dev 1.82 pt2 -> Removed changelog from Main Menu to conserve screen space
        List<String> changelogLess = new ArrayList<>();
        changelogLess.add(ver.ChangelogMainMenu);
        changelogLess.add("Big Update?!?!");
        changelogLess.add("[To see the Changelogs, open the changelog menu]");
        //changelogLess.add("NOTE: ANY CODE PUSH WILL BE ACCEPTED");
        //changelogLess.add("AFTER THAT IT IS CONFIRMED WORKING/SAFE");
        changelogLess.add("Turn ON Fast-Render to turn OFF UI Blur");
        for (String s : changelogLess) {
            wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + s,posX + fontRendererObj.getStringWidth("joe") - 0, y + yAdd,0);
            yAdd+=12;
        }
    }
}
