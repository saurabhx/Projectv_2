package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DbUtil {
	private  static Connection connection;
	
	@Value("#{db_config[driver]}")
	private String driver;
	@Value("#{db_config[url]}")
	private String url;
	@Value("#{db_config[user]}")
	private String user;
	@Value("#{db_config[password]}")
	private String password;
	
    public Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
            	
            	Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }

}
