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

public class RentalByDayStrategyTest {
	final Long MILISECONDS_PER_DAY = new Long(24 * 60 * 60 * 1000);
	Bike bike = null;
	RentalCompany rentalCompany = null;
	RentalByDayStrategy rentalByDayStrategy = null;
	
	@Before
	public void initialize(){
		rentalCompany = new RentalCompany();
		bike = new Bike(1, rentalCompany);
		rentalByDayStrategy = new RentalByDayStrategy();
	}
	
	@Test
	public void testRentalCharge() throws IOException, BadRequestException{
		BigDecimal charge = rentalByDayStrategy.rent(bike, 2);
		Assert.assertEquals(charge, new BigDecimal(40));
	}	

	@Test
	public void testBikeReturnDate() throws IOException, BadRequestException{
		Date now = new Date();
		rentalByDayStrategy.rent(bike, 2);
		Long diff = bike.getReturnDate().getTime() - now.getTime();
		//It considers the time that the test spends running
		Assert.assertTrue(diff - (new Long(2 * MILISECONDS_PER_DAY)) < new Long(50000));
	}	

	@Test(expected=BadRequestException.class)
	public void testNegativeDays() throws IOException, BadRequestException{
		rentalByDayStrategy.rent(bike, -1);
	}	

	@Test(expected=BadRequestException.class)
	public void testZeroDays() throws IOException, BadRequestException{
		rentalByDayStrategy.rent(bike, 0);
	}	
	
	@Test(expected=BadRequestException.class)
	public void testNullDays() throws IOException, BadRequestException{
		rentalByDayStrategy.rent(bike, null);
	}	

	@Test(expected=BadRequestException.class)
	public void testNullBike() throws IOException, BadRequestException{
		rentalByDayStrategy.rent(null, 5);
	}
}
