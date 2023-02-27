package uk.to.and1558.Gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import uk.to.and1558.VersionString;

public class DrawChangelogs {
    public static VersionString ver = new VersionString();
    public static void get(GuiScreen wheretoDraw, FontRenderer fontRendererObj, int posX){
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + ver.ChangelogMainMenu, posX + fontRendererObj.getStringWidth("joe") - 0, 83, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "- Switched to mixin coding", posX + fontRendererObj.getStringWidth("joe") - 0, 95, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "- Perspective Mod Ported [Using Canelex||DjTheRedstoner]" /**Perspective mod works by pressing not holding (and is kind of janky)**/, posX + fontRendererObj.getStringWidth("joe") - 0, 107, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "- Updated MC version from 1.8.8 to 1.8.9", posX + fontRendererObj.getStringWidth("joe") - 0, 119, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "- Old Animations added (if any bugs is found, create issue on the installer github)", posX + fontRendererObj.getStringWidth("joe") - 0, 131, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "- Partially success porting from native java to mixin", posX + fontRendererObj.getStringWidth("joe") - 0, 143, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "- Fix minecraft unable to resize after exiting fullscreen bug", posX + fontRendererObj.getStringWidth("joe") - 0, 155, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "- Added Session Changer", posX + fontRendererObj.getStringWidth("joe") - 0, 167, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "NOTE: ANY IMPROVEMENTS/FIXES TO BUGS WILL BE ACCEPTED AFTER CHECKED THAT ITS WORKING", posX + fontRendererObj.getStringWidth("joe") - 0, 179, 0);
    }
    public static void getLess(GuiScreen wheretoDraw, FontRenderer fontRendererObj, int posX){
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + ver.ChangelogMainMenu, posX + fontRendererObj.getStringWidth("joe") - 0, 83, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "- Perspective mod [more info on changelog screen]", posX + fontRendererObj.getStringWidth("joe") - 0, 95, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "- Updated MC version .8 -> .9", posX + fontRendererObj.getStringWidth("joe") - 0, 107, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "- Old Animations added (more info on changelog screen)", posX + fontRendererObj.getStringWidth("joe") - 0, 119, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "- Added Session Changer", posX + fontRendererObj.getStringWidth("joe") - 0, 131, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "NOTE: ANY CODE PUSH WILL BE ACCEPTED", posX + fontRendererObj.getStringWidth("joe") - 0, 143, 0);
        wheretoDraw.drawString(fontRendererObj, EnumChatFormatting.GREEN + "AFTER THAT IT IS CONFIRMED WORKING/SAFE", posX + fontRendererObj.getStringWidth("joe") - 0, 155, 0);
    }
}
