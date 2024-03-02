package owo.aydendevy.Gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import owo.aydendevy.Plugins.ClientAnimations.Animation;
import owo.aydendevy.Plugins.ClientAnimations.Easing;
import owo.aydendevy.VersionString;

import java.util.ArrayList;
import java.util.List;

public class DrawChangelogs {
    public static VersionString ver = new VersionString();
    public static void get(GuiScreen wheretoDraw, FontRenderer fontRendererObj, int posX){
        int y = 22;
        int yAdd = 0;
        wheretoDraw.drawCenteredString(fontRendererObj, EnumChatFormatting.GREEN + ver.ChangelogMainMenu2, wheretoDraw.width / 2, 10, 0);
        List<String> changelog = new ArrayList<>();
        changelog.add("-  Fixed Item Physics rotating very fast on high FPS (>80FPS)");
        changelog.add("-  Made snow particles visible on almost all GUIs");
        changelog.add("-  Added more settings in the Extra Options Menu");
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
        changelogLess.add("Minor Update and Bug Fixes!");
        changelogLess.add("[To see the Changelogs, open the changelog menu]");
        //changelogLess.add("NOTE: ANY CODE PUSH WILL BE ACCEPTED");
        //changelogLess.add("AFTER THAT IT IS CONFIRMED WORKING/SAFE");
        changelogLess.add("To turn OFF UI Blur Turn ON Fast-Render or Turn off Blur in Extra Options");
        for (String s : changelogLess) {
            wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + s,posX + fontRendererObj.getStringWidth("joe") - 0, y + yAdd,0);
            yAdd+=12;
        }
    }
}
