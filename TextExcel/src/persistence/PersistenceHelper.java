package persistence;

import java.io.*;
import java.util.*;

public class PersistenceHelper {

	public static void save(String filePath, Savable toSave) throws Exception {

		String[] data = toSave.getSaveData();

		PrintStream out = new PrintStream(new File(filePath));

		try {
			for (String datum : data) {
				out.println(datum);
			}
		} finally {
			out.close();
		}
	}

	public static void load(String filePath, Savable toLoadTo) throws Exception {
		ArrayList<String> data = new ArrayList<String>();

		Scanner input = new Scanner(new File(filePath));

		try {
			while (input.hasNextLine()) {
				data.add(input.nextLine());
			}
		} finally {
			input.close();
		}

		String[] dataArray = new String[data.size()];
		toLoadTo.loadFrom(data.toArray(dataArray));
	}

}
