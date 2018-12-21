package ar.com.fdv.rentalBusiness.rentalStrategy;

import java.io.IOException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.domainModel.Bike;

public class RentalByWeekStrategyTest {
	final Long MILISECONDS_PER_WEEK = new Long(7 * 24 * 60 * 60 * 1000);
	Bike bike = null;
	RentalByWeekStrategy rentalByWeekStrategy = null;
	
	@Before
	public void initialize(){
		bike = new Bike(1);
		rentalByWeekStrategy = new RentalByWeekStrategy();
	}
	
	@Test
	public void testRentalCharge() throws IOException, BadRequestException{
		Float charge = rentalByWeekStrategy.rent(bike, 2);
		Assert.assertEquals(charge, 120F, 0F);
	}	

	@Test
	public void testBikeReturnDate() throws IOException, BadRequestException{
		Date now = new Date();
		rentalByWeekStrategy.rent(bike, 5);
		Long diff = bike.getReturnDate().getTime() - now.getTime();
		//It considers the time that the test spends running
		Assert.assertTrue(diff - (new Long(5 * MILISECONDS_PER_WEEK)) < new Long(50000));
	}	

	@Test(expected=BadRequestException.class)
	public void testNegativeWeeks() throws IOException, BadRequestException{
		rentalByWeekStrategy.rent(bike, -1);
	}	

	@Test(expected=BadRequestException.class)
	public void testZeroWeeks() throws IOException, BadRequestException{
		rentalByWeekStrategy.rent(bike, 0);
	}	
	
	@Test(expected=BadRequestException.class)
	public void testNullWeeks() throws IOException, BadRequestException{
		rentalByWeekStrategy.rent(bike, null);
	}	

	@Test(expected=BadRequestException.class)
	public void testNullBike() throws IOException, BadRequestException{
		rentalByWeekStrategy.rent(null, 8);
	}
}
