package ar.com.fdv.rentalBusiness.rentalStrategy;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.domainModel.Bike;
import ar.com.fdv.rentalBusiness.domainModel.RentalCompany;

public class RentalByHourStrategyTest {
	final Long MILISECONDS_PER_HOUR = new Long(60 * 60 * 1000);
	Bike bike = null;
	RentalCompany rentalCompany = null;
	RentalByHourStrategy rentalByHourStrategy = null;
	
	@Before
	public void initialize(){
		rentalCompany = new RentalCompany();
		bike = new Bike(1, rentalCompany);
		rentalByHourStrategy = new RentalByHourStrategy();
	}
	
	@Test
	public void testRentalCharge() throws IOException, BadRequestException{
		BigDecimal charge = rentalByHourStrategy.rent(bike, 3);
		Assert.assertEquals(charge, new BigDecimal(15));
	}	

	@Test
	public void testBikeReturnDate() throws IOException, BadRequestException{
		Date now = new Date();
		rentalByHourStrategy.rent(bike, 3);
		Long diff = bike.getReturnDate().getTime() - now.getTime();
		//It considers the time that the test spends running
		Assert.assertTrue(diff - (new Long(3 * MILISECONDS_PER_HOUR)) < new Long(50000));
	}	

	@Test(expected=BadRequestException.class)
	public void testNegativeHours() throws IOException, BadRequestException{
		rentalByHourStrategy.rent(bike, -1);
	}	

	@Test(expected=BadRequestException.class)
	public void testZeroHours() throws IOException, BadRequestException{
		rentalByHourStrategy.rent(bike, 0);
	}	
	
	@Test(expected=BadRequestException.class)
	public void testNullHours() throws IOException, BadRequestException{
		rentalByHourStrategy.rent(bike, null);
	}	

	@Test(expected=BadRequestException.class)
	public void testNullBike() throws IOException, BadRequestException{
		rentalByHourStrategy.rent(null, 5);
	}
}
