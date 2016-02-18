package bg.uni.sofia.fmi.corejava.stringsrearchtree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindAllTextFiles {
	private static List<String> listWithTextFiles = new ArrayList<>();

	public static List<String> findTextFiles(File file) {
		if (file.isFile() && file.toString().endsWith(".txt"))
			listWithTextFiles.add(file.getAbsolutePath());
		else if (file.isDirectory()) {
			File[] listOfFiles = file.listFiles();
			if (listOfFiles != null) {
				for (File file0 : listOfFiles) {
					findTextFiles(file0);
				}
			}
		}

		return listWithTextFiles;
	}
}
