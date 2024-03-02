package owo.aydendevy.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import owo.aydendevy.DevyClient;

import java.io.IOException;

public class OfflineAuthGUI extends GuiScreen {
    private GuiTextField email;

    @Override
    public void updateScreen() {
        this.email.updateCursorCounter();
    }

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 - 160, 90, 150, 20, "Login"));
        this.buttonList.add(new GuiButton(1, this.width / 2, 90, 150, 20, "Back"));
        this.email = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        //this.pass = new GuiTextField(3, this.fontRendererObj,this.width / 2 - 100, 100, 200, 20);
        this.email.setFocused(true);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        // dev 1.82 -> Fixed going to last menu after clicking login
        switch (button.id){
            case 0:{
                /**try {
                    new MSAuth().authenticate(this.email.getText(), this.pass.getText());
                }
                catch (MicrosoftAuthenticationException e){
                    err = e.getMessage();
                    break;
                }**/
                DevyClient.getInstance().modifyableSession.changeSession(email.getText(), email.getText(), "0", "legacy");
                err = "Successfully logged in as: " + DevyClient.getInstance().modifyableSession.getUsername();
                break;
            }
            case 1:{
                this.mc.displayGuiScreen(new GuiSelectSession());
            }
        }
    }
    String err = "No Error Yet!";
    String passdebug = "if this shows up your password will also shows up";
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Username only login, lets you choose any username", this.width / 2, 17, 16777215);
        this.drawCenteredString(this.fontRendererObj, "but you are unable to join some servers due to invalid session", this.width / 2, 32, 16777215);
        //this.drawCenteredString(this.fontRendererObj, err, this.width / 2, 42, 16777215);
        //this.drawString(this.fontRendererObj,"Password Length = " + pass.getText().length(), this.width / 2 - 100, 130,-1);
        this.email.drawTextBox();
        super.drawScreen(mouseX,mouseY,partialTicks);
    }
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        this.email.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.email.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
