package de.xancake.io.db.sql.config;

public class DBConfigurationSimple implements DBConfiguration_I {
	private String myDriver;
	private String myHost;
	private String myUser;
	private String myPassword;
	
	public DBConfigurationSimple(String driver, String host, String user, String password) {
		myDriver = driver;
		myHost = host;
		myUser = user;
		myPassword = password;
	}
	
	@Override
	public String getDriver() {
		return myDriver;
	}
	
	@Override
	public String getHost() {
		return myHost;
	}
	
	@Override
	public String getUser() {
		return myUser;
	}
	
	@Override
	public String getPassword() {
		return myPassword;
	}
}
