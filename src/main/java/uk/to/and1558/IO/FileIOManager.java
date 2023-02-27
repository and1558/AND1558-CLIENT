package uk.to.and1558.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class FileIOManager {
	
	private static Gson gson = new Gson();
	private static File ROOT = new File("AND1558");
	private static File MODS = new File(ROOT, "09HNF9843NJDF");
	private static File NOTE = new File(ROOT, "This folder is not a virus, it is for saving your HUD positions, do not delete or you will need to reset your HUD POS");
	
	public static void init() {
		if(!ROOT.exists()) { ROOT.mkdirs(); }
		if(!MODS.exists()) { MODS.mkdirs(); }
		if(!NOTE.exists()) { NOTE.mkdirs(); }
	}
	public static Gson getGson() {
		return gson;
	}
	public static File getModsDir() {
		return MODS;
	}
	public static boolean writeJsonToFile(File file, Object obj) {
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream output = new FileOutputStream(file);
			output.write(gson.toJson(obj).getBytes());
			output.flush();
			output.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public static <T extends Object> T readFromJson(File file, Class<T> c) {
		try {
			FileInputStream input = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(input);
			BufferedReader reader1 = new BufferedReader(reader);
			StringBuilder string = new StringBuilder();
			String line;
			while((line = reader1.readLine()) != null) {
				string.append(line);
			}
			reader1.close();
			reader.close();
			input.close();
			return gson.fromJson(string.toString(), c);
		}
		catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
}
