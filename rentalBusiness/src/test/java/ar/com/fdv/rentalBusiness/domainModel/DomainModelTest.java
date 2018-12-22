package ar.com.fdv.rentalBusiness.domainModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DomainModelTest {

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
		
		initializeBikes();
		addElementsToListsOfBikes(rentedBikes, availableBikes);
		initializeCustomers(rentedBikes);
		initializeRentalCompany(availableBikes); 
	}

	private void initializeBikes() {
		bike1 = new Bike(1, rentalCompany);
		bike2 = new Bike(2, rentalCompany);
		bike3 = new Bike(3, rentalCompany);
	}

	private void addElementsToListsOfBikes(List<Bike> rentedBikes,
			List<Bike> availableBikes) {
		rentedBikes.add(bike1);
		availableBikes.add(bike2);
		availableBikes.add(bike3);
	}

	private void initializeCustomers(List<Bike> rentedBikes) {
		customer1 = new Customer(new BigDecimal(100), null);
		customer2 = new Customer(new BigDecimal(10), rentedBikes);
	}

	private void initializeRentalCompany(List<Bike> availableBikes) {
		rentalCompany = new RentalCompany(new BigDecimal(1000), availableBikes);
	}

	@Test
	public void testDomainModel(){
		Assert.assertEquals(rentalCompany.getCash(), new BigDecimal(1000));
		Assert.assertEquals(rentalCompany.getAvailableBikes().size(), 2);
		Assert.assertEquals(customer1.getCash(), new BigDecimal(100));
		Assert.assertEquals(customer2.getRentedBikes().size(), 1);
		Assert.assertEquals(bike1.getSerialNumber(), new Integer(1));		
	}
	
	@Test
	public void testDomainModelSetters(){
		RentalCompany rentalCompany2 = new RentalCompany();
		bike1.setRentalCompany(rentalCompany2);
		bike1.setSerialNumber(5);
		customer1.setRentedBikes(new ArrayList<Bike>());
		rentalCompany2.setCash(new BigDecimal(1000));
		Assert.assertEquals(bike1.getSerialNumber(), new Integer(5));		
		Assert.assertEquals(bike1.getRentalCompany(), rentalCompany2);
		Assert.assertTrue(customer1.getRentedBikes().size() == 0);
		Assert.assertTrue(rentalCompany2.getCash().equals(new BigDecimal(1000)));
	}
}
