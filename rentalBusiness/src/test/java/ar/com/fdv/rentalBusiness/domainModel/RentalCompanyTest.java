package ar.com.fdv.rentalBusiness.domainModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.businessException.NotAvailableBikesException;
import ar.com.fdv.rentalBusiness.dto.CustomerOrder;
import ar.com.fdv.rentalBusiness.rentalStrategy.RentalByDayStrategy;
import ar.com.fdv.rentalBusiness.rentalStrategy.RentalByHourStrategy;
import ar.com.fdv.rentalBusiness.rentalStrategy.RentalByWeekStrategy;
import ar.com.fdv.rentalBusiness.rentalStrategy.RentalStrategy;

public class RentalCompanyTest {

	private Bike bike1 = null;
	private Bike bike2 = null;
	private Bike bike3 = null;
	private Customer customer1 = null;
	private Customer customer2 = null;
	private CustomerOrder customerOrder1 = null;
	private CustomerOrder customerOrder2 = null;
	private CustomerOrder customerOrder3 = null;
	private CustomerOrder customerOrder4 = null;
	private CustomerOrder customerOrder5 = null;
	private CustomerOrder customerOrder6 = null;
	private RentalStrategy rentalByHourStrategy = null; 
	private RentalStrategy rentalByDayStrategy = null; 
	private RentalStrategy rentalByWeekStrategy = null; 
	private RentalCompany rentalCompany = null; 
	List<Bike> rentedBikes = new ArrayList<Bike>();
	List<Bike> availableBikes = new ArrayList<Bike>();
	List<CustomerOrder> customerOrders1 = new ArrayList<CustomerOrder>();
	List<CustomerOrder> customerOrders2 = new ArrayList<CustomerOrder>();
	List<CustomerOrder> customerOrders3 = new ArrayList<CustomerOrder>();
	
	@Before
	public void initialize(){
		initializeBikes();
		initializeListsOfBikes();
		initializeCustomers();
		initializeRentalStrategies();
		initializeCustomerOrders();
		initializeListOfCustomerOrders();
		initializeRentalCompany(); 
	}
	
	private void initializeBikes() {
		bike1 = new Bike(1);
		bike2 = new Bike(2);
		bike3 = new Bike(3);
	}
	
	private void initializeListsOfBikes() {
		rentedBikes.add(bike1);
		availableBikes.add(bike2);
		availableBikes.add(bike3);
	}

	private void initializeCustomers() {
		customer1 = new Customer(100F, null);
		customer2 = new Customer(10F, rentedBikes);
	}

	private void initializeRentalStrategies() {
		rentalByHourStrategy = new RentalByHourStrategy();
		rentalByDayStrategy = new RentalByDayStrategy();
		rentalByWeekStrategy = new RentalByWeekStrategy();
	}

	private void initializeCustomerOrders() {
		customerOrder1 = new CustomerOrder(rentalByHourStrategy, 1);
		customerOrder2 = new CustomerOrder(rentalByDayStrategy, 2);
		customerOrder3 = new CustomerOrder(rentalByWeekStrategy, 3);
		customerOrder4 = new CustomerOrder(rentalByHourStrategy, 4);
		customerOrder5 = new CustomerOrder(rentalByDayStrategy, 5);
		customerOrder6 = new CustomerOrder(rentalByWeekStrategy, 6);
	}

	private void initializeListOfCustomerOrders() {
		customerOrders1.add(customerOrder1);
		customerOrders2.add(customerOrder1);
		customerOrders2.add(customerOrder2);
		customerOrders2.add(customerOrder3);
		customerOrders3.add(customerOrder1);
		customerOrders3.add(customerOrder2);
		customerOrders3.add(customerOrder3);
		customerOrders3.add(customerOrder4);
		customerOrders3.add(customerOrder5);
		customerOrders3.add(customerOrder6);
	}

	private void initializeRentalCompany() {
		rentalCompany = new RentalCompany(1000F, availableBikes);
	}
	
	@Test(expected=BadRequestException.class)
	public void testNullCustomerOrders() throws BadRequestException, NotAvailableBikesException, IOException{
		rentalCompany.rent(null);
	}
	
	@Test(expected=BadRequestException.class)
	public void testEmptyCustomerOrders() throws BadRequestException, NotAvailableBikesException, IOException{
		rentalCompany.rent(new ArrayList<CustomerOrder>());
	}

	@Test(expected=BadRequestException.class)
	public void testRentalStrategyIsNull() throws BadRequestException, NotAvailableBikesException, IOException{
		customerOrder1 = new CustomerOrder(null, 1);
		List<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();
		customerOrders.add(customerOrder1);
		rentalCompany.rent(customerOrders);
	}

	@Test(expected=BadRequestException.class)
	public void testTimeQuantityIsNull() throws BadRequestException, NotAvailableBikesException, IOException{
		customerOrder1 = new CustomerOrder(rentalByDayStrategy, null);
		List<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();
		customerOrders.add(customerOrder1);
		rentalCompany.rent(customerOrders);
	}
	
	@Test(expected=NotAvailableBikesException.class)
	public void testNotAvailableBikes() throws BadRequestException, NotAvailableBikesException, IOException{
		rentalCompany.setAvailableBikes(new ArrayList<Bike>());
		rentalCompany.rent(customerOrders1);
	}
}
