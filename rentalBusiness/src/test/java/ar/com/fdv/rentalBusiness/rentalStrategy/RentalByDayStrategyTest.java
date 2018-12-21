package ar.com.fdv.rentalBusiness.rentalStrategy;

import java.io.IOException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.fdv.rentalBusiness.domainModel.Bike;

public class RentalByDayStrategyTest {
	final Long MILISECONDS_PER_DAY = new Long(24 * 60 * 60 * 1000);
	Bike bike = null;
	RentalByDayStrategy rentalByDayStrategy = null;
	
	@Before
	public void initialize(){
		bike = new Bike(1);
		rentalByDayStrategy = new RentalByDayStrategy();
	}
	
	@Test
	public void testRentalCharge() throws IOException{
		Float charge = rentalByDayStrategy.rent(bike, 2);
		Assert.assertEquals(charge, 40F, 0F);
	}	

	@Test
	public void testBikeReturnDate() throws IOException{
		Date now = new Date();
		rentalByDayStrategy.rent(bike, 2);
		Long diff = bike.getReturnDate().getTime() - now.getTime();
		//It considers the time that the test spends running
		Assert.assertTrue(diff - (new Long(2 * MILISECONDS_PER_DAY)) < new Long(50000));
	}	

}
