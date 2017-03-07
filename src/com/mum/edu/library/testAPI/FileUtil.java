package com.mum.edu.library.testAPI;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class FileUtil {
	public static void main(String[] args) {
		FileUtil obj = new FileUtil();
		System.out.println(obj.getFileWithUtil("database/stateUS.txt"));

	}

	private String getFileWithUtil(String fileName) {
		String result = "";
		
		ClassLoader classLoader = getClass().getClassLoader();
//		URL resource = classLoader.getResource(fileName).toString();
		try {
		    result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	  }

	}
