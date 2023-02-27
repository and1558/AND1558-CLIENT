package uk.to.and1558.Mods.ModLoader;

import java.io.File;

import uk.to.and1558.Gui.HUD.IRenderer;
import uk.to.and1558.Gui.HUD.ScreenPosition;
import uk.to.and1558.IO.FileIOManager;

public abstract class ModDraggable extends Mod implements IRenderer {
	protected ScreenPosition pos;

	public ModDraggable() {
		// TODO Auto-generated constructor stub
		pos = loadPosFromJson();
	}
	@Override
	public ScreenPosition load() {
		// TODO Auto-generated method stub
		return pos;
	}
	@Override
	public void save(ScreenPosition pos) {
		// TODO Auto-generated method stub
		this.pos = pos;
		savePosToJson();
	}
	private File getJsonFolder() {
		File folder = new File(FileIOManager.getModsDir(), this.getClass().getSimpleName());
		folder.mkdirs();
		return folder;
	}
	private void savePosToJson() {
		// TODO Auto-generated method stub
		FileIOManager.writeJsonToFile(new File(getJsonFolder(), "ModPos.json"), pos);
	}
	private ScreenPosition loadPosFromJson() {
		// TODO Auto-generated method stub
		ScreenPosition loaded = FileIOManager.readFromJson(new File(getJsonFolder(), "ModPos.json"), ScreenPosition.class);
		if(loaded == null)
		{
			loaded = ScreenPosition.fromRelativePosition(0.5, 0.5);
			this.pos = loaded;
			savePosToJson();
		}
		return loaded;
	}
	public final int getLineOffset(ScreenPosition pos, int lineNum) {
		return pos.getAbsoluteY() + getLineOffset(lineNum);

	}

	private int getLineOffset(int lineNum) {
		// TODO Auto-generated method stub
		return (font.FONT_HEIGHT + 3) * lineNum;
	}

}
