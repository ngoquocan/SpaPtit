package com.example.demo.dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DAO {
	public static Connection con;
	private final String FILE_PROPERTIES_NAME = "/src/main/resources/application.properties";

	private Properties properties;
	public DAO() {
		if (con == null) {
			try {
				String currentDir = System.getProperty("user.dir");
				InputStream inputStream = new FileInputStream(currentDir + FILE_PROPERTIES_NAME);
				properties = new Properties();
				properties.load(inputStream);

				String driver = properties.getProperty("spring.datasource.driver-class-name");
				String database = properties.getProperty("spring.datasource.url");
				String username = properties.getProperty("spring.datasource.username");
				String password = properties.getProperty("spring.datasource.password");

				Class.forName(driver);
				con = DriverManager.getConnection(database, username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
