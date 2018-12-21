package ar.com.fdv.rentalBusiness.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {
	private static ApplicationProperties instance = null;
	InputStream inputStream;
	
	private ApplicationProperties(){}
	
    private synchronized static void createInstance() {
        if (instance == null) { 
            instance = new ApplicationProperties();
        }
    }

    public static ApplicationProperties getInstance() {
        if (instance == null) createInstance();
        return instance;
    }
	
	public String getPropertyValue(String propertyName) throws IOException {
			Properties properties = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
			properties.load(inputStream);
			return properties.getProperty(propertyName);
	}
}
