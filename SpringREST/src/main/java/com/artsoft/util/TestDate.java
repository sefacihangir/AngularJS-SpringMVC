package com.artsoft.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestDate {

	public static void main(String[] args) {
		TestDate test = new TestDate();
		try {
			System.out.println("PATH -- " + test.getUploadFolderPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUploadFolderPath() throws IOException {

		String path = "";

		try {
			Properties properties = new Properties();
			String propertiesFileName = "application.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);

			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException(
						"Property file '" + propertiesFileName + "' not found in the classpath");
			}

			path = properties.getProperty("upload_folder_path");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

}
