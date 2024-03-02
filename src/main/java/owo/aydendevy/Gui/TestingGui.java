package owo.aydendevy.Gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import owo.aydendevy.DevyClient;

import java.io.IOException;
// Unused!
// Test saving mechanism.
public class TestingGui extends GuiScreen {
    private GuiTextField typeToFind;

    @Override
    public void updateScreen() {
        this.typeToFind.updateCursorCounter();
    }

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 - 155, this.height - 28, 150, 20, "Find"));
        this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height - 28, 150, 20, "Create test Folder"));
        this.typeToFind = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 60, 200, 20);
        this.typeToFind.setFocused(true);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id){
            case 0:{
                DevyClient.getIO.loadConfigNew(typeToFind.getText());
                break;
            }
            case 1:{
                DevyClient.getIO.saveConfigNew(false, "test1");
                DevyClient.getIO.saveConfigNew(true, "test2");
                DevyClient.getIO.saveConfigNew(true, "test3");
                DevyClient.getIO.saveConfigNew(false, "test4");
                DevyClient.getIO.saveConfigNew(true, "test5");
                DevyClient.getIO.saveConfigNew(false, "test6");
                DevyClient.getIO.saveConfigNew(false, "test7");
                DevyClient.getIO.saveConfigNew(true, "test8");
                DevyClient.getIO.saveConfigNew(false, "test9");
                DevyClient.getIO.saveConfigNew(true, "test10");
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.typeToFind.drawTextBox();
        super.drawScreen(mouseX,mouseY,partialTicks);
    }
}
