package com.util;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class DbUtil {
	 private  Connection connection;

	 @Autowired
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

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	}