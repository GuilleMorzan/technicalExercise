package ar.com.fdv.rentalBusiness.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtils {

	public static void configureLogger(Logger logger){
		try{
			Handler fileHandler  = new FileHandler("./rentalBusiness.log");
			logger.addHandler(fileHandler);
			logger.setLevel(Level.ALL);
		} 
		catch (IOException ioException){
			//I prefer to log in the console and continue operating the rental system
			System.out.println("An error occurred during the configuration of the logger.");
		}
	}
}
