package com.mum.edu.library.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommonAPI {
	private static final String FILENAME = "E:\\MPP\\LibraryProblem\\stateUS.txt";

	public static List<String> getUSState() {
		BufferedReader br = null;
		FileReader fr = null;
		List<String> result = null;
		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			
			String sCurrentLine;
			result = new ArrayList<>();
			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {
				result.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
		return result;
	}
}
