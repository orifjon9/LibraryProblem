	package com.mum.edu.library.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mum.edu.library.constant.Constant;

public class CommonAPI {

	public static List<String> getUSState() {
		List<String> result = new ArrayList<>();
		ClassLoader classLoader = new ClassLoader() {
		};
		File file = new File(classLoader.getResource(Constant.US_STATE_FILE).getFile());

		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.add(line);
			}

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static String findExactlyDataBase(String path) {
		ClassLoader classLoader = new ClassLoader() {};
		return classLoader.getResource(path).getFile().replace(Constant.BIN, Constant.RESOURCES);
	}
}
