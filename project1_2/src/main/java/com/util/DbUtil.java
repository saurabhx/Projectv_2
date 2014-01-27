package com.util;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class DbUtil {
	 private   Connection connection = null;
	 private DataSource dataSource;
	    public   Connection getConnection() {
	        if (connection != null)
	            return connection;
	        else {
	            try {
	            	connection= dataSource.getConnection();
	             
	            }catch(Exception e){}
	            return connection;
	        }
	    }
	}