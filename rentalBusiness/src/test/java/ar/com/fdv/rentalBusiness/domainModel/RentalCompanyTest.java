package ar.com.fdv.rentalBusiness.domainModel;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.dto.CustomerOrder;

public class RentalCompanyTest {

	private Bike bike1 = null;
	private Bike bike2 = null;
	private Bike bike3 = null;
	private Customer customer1 = null;
	private Customer customer2 = null;
	private RentalCompany rentalCompany = null; 
	
	@Before
	public void initialize(){
		List<Bike> rentedBikes = new ArrayList<Bike>();
		List<Bike> availableBikes = new ArrayList<Bike>();
		
		bike1 = new Bike(1);
		bike2 = new Bike(2);
		bike3 = new Bike(3);
		
		rentedBikes.add(bike1);
		availableBikes.add(bike2);
		availableBikes.add(bike3);
		
		customer1 = new Customer(100F, null);
		customer2 = new Customer(10F, rentedBikes);
		
		rentalCompany = new RentalCompany(1000F, availableBikes); 
	}
	
	@Test(expected=BadRequestException.class)
	public void testNullCustomerOrder() throws BadRequestException{
		rentalCompany.rent(null);
	}
	
	@Test(expected=BadRequestException.class)
	public void testEmptyCustomerOrder() throws BadRequestException{
		rentalCompany.rent(new ArrayList<CustomerOrder>());
	}
}
