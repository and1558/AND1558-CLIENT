package uk.to.and1558.Gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import uk.to.and1558.Gui.impl.GuiCheckBox;
import uk.to.and1558.Plugins.SessionHelper;
import uk.to.and1558.Plugins.openauth.microsoft.MicrosoftAuthenticationException;
import uk.to.and1558.and1558;

import java.awt.*;
import java.io.IOException;

public class MSAuthGUI extends GuiScreen {
    private GuiButton loginButton;
    private GuiButton backButton;

    boolean showPass = false;
    @Override
    public void initGui() {
        loginButton = new GuiButton(0, this.width / 2 - 160, 150, 150, 20, "Click here to login");
        backButton = new GuiButton(1, this.width / 2, 150, 150, 20, "Back");
        this.buttonList.add(backButton);
        this.buttonList.add(loginButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        // dev 1-.82 -> Fixed client freezing when trying to login
        switch (button.id){
            case 0:{
                new Thread(() -> {
                    err = "Trying to login, Please Wait...";
                    err2 = "DO NOT PRESS THE ESCAPE KEY!";
                    loginButton.enabled = false;
                    backButton.enabled = false;
                    try {
                        new MSAuth().authenticate();
                        err = "Successfully logged in as: " + and1558.getInstance().modifyableSession.getUsername();
                    }
                    catch (MicrosoftAuthenticationException e){
                        if(e.getMessage().contains("User closed")){
                            err = "Authentication Window Closed!";
                        }else{
                            err = e.getMessage();
                        }
                    }
                    loginButton.enabled = true;
                    backButton.enabled = true;
                    err2 = "";
                }).start();
                break;
            }
            case 1:{
                this.mc.displayGuiScreen(new GuiSelectSession());
            }
            case 4:{
                if(!showPass){
                    showPass = true;
                }
                else if (showPass){
                    showPass = false;
                }
                break;
            }
        }
    }
    String err = "No Error Yet!";
    String err2 = "";
    String passdebug = "if this shows up your password will also shows up";
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Microsoft Login GUI", this.width / 2, 17, 16777215);
        this.drawCenteredString(this.fontRendererObj, err, this.width / 2, 42, 16777215);
        this.drawCenteredString(this.fontRendererObj, err2, this.width / 2, 52, 16777215);
        super.drawScreen(mouseX,mouseY,partialTicks);
    }
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }
}
