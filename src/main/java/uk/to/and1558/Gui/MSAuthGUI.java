package uk.to.and1558.Gui;

import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import uk.to.and1558.Gui.impl.GuiCheckBox;
import uk.to.and1558.Plugins.SessionHelper;
import uk.to.and1558.and1558;

import java.awt.*;
import java.io.IOException;

public class MSAuthGUI extends GuiScreen {
    private GuiTextField email;
    private GuiTextField pass;

    @Override
    public void updateScreen() {
        this.email.updateCursorCounter();
    }
    boolean showPass = false;
    @Override
    public void initGui() {
        //this.buttonList.add(new GuiButton(0, this.width / 2 - 155, this.height - 28, 150, 20, "Login"));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 160, 150, 150, 20, "Login"));
        this.buttonList.add(new GuiButton(1, this.width / 2, 150, 150, 20, "Back"));
        this.buttonList.add(new GuiCheckBox(4, this.width / 2 + 105, 100, showPass));
        this.email = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        this.pass = new GuiTextField(3, this.fontRendererObj,this.width / 2 - 100, 100, 200, 20);
        this.email.setFocused(true);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case 0:{
                try {
                    new MSAuth().authenticate(this.email.getText(), this.pass.getText());
                }
                catch (MicrosoftAuthenticationException e){
                    err = e.getMessage();
                    break;
                }
                err = "Successfully logged in as: " + and1558.getInstance().modifyableSession.getUsername();
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
    String passdebug = "if this shows up your password will also shows up";
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Two-Factor Authenticated accounts may not work", this.width / 2, 17, 16777215);
        this.drawCenteredString(this.fontRendererObj, "and you are typing your password its just not visible", this.width / 2, 32, 16777215);
        this.drawCenteredString(this.fontRendererObj, err, this.width / 2, 42, 16777215);
        this.drawString(this.fontRendererObj,"Password Length = " + pass.getText().length(), this.width / 2 - 100, 130,-1);
        this.drawString(this.fontRendererObj,"- Show Password", this.width / 2 + 130, 107,-1);
        this.drawString(this.fontRendererObj,"Email: ", this.width / 2 - 130, 65,-1);
        this.drawString(this.fontRendererObj,"Pass: ", this.width / 2 - 130, 105,-1);
        this.email.drawTextBox();
        this.pass.drawTextBox();
        if(!showPass){
            pass.setTextColor(Color.black.getRGB());
        }else{
            pass.setTextColor(Color.white.getRGB());
        }
        super.drawScreen(mouseX,mouseY,partialTicks);
    }
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        this.email.textboxKeyTyped(typedChar, keyCode);
        this.pass.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.email.mouseClicked(mouseX, mouseY, mouseButton);
        this.pass.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
