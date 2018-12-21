package ar.com.fdv.rentalBusiness.domainModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.businessException.InsufficientFundsException;
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
	private Customer customer = null;
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
	List<CustomerOrder> customerOrders4 = new ArrayList<CustomerOrder>();
	
	@Before
	public void initialize(){
		initializeBikes();
		initializeListsOfBikes();
		initializeCustomer();
		initializeRentalStrategies();
		initializeCustomerOrders();
		initializeListOfCustomerOrders();
		initializeRentalCompany(); 
	}
	
	private void initializeBikes() {
		bike1 = new Bike(1, rentalCompany);
		bike2 = new Bike(2, rentalCompany);
		bike3 = new Bike(3, rentalCompany);
	}
	
	private void initializeListsOfBikes() {
		rentedBikes.add(bike1);
		availableBikes.add(bike2);
		availableBikes.add(bike3);
	}

	private void initializeCustomer() {
		customer = new Customer(new BigDecimal(100), new ArrayList<Bike>());
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
		customerOrders3.add(customerOrder1);
		customerOrders3.add(customerOrder2);
		customerOrders3.add(customerOrder3);
		customerOrders4.add(customerOrder1);
		customerOrders4.add(customerOrder2);
		customerOrders4.add(customerOrder3);
		customerOrders4.add(customerOrder4);
		customerOrders4.add(customerOrder5);
		customerOrders4.add(customerOrder6);


	}

	private void initializeRentalCompany() {
		rentalCompany = new RentalCompany(new BigDecimal(1000), availableBikes);
	}

	@Test
	public void testRentalCompanyChargeOperation() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		rentalCompany.rent(customer, customerOrders1);
		Assert.assertTrue(rentalCompany.getCash().equals(new BigDecimal(1005)));
	}

	@Test
	public void testRentalCompanyDelivery() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		rentalCompany.rent(customer, customerOrders1);
		Assert.assertTrue(rentalCompany.getAvailableBikes().size() == 1);
	}
	
	@Test
	public void testCustomerPaymentOperation() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		rentalCompany.rent(customer, customerOrders1);
		Assert.assertTrue(customer.getCash().equals(new BigDecimal(95)));
	}
	
	@Test
	public void testCustomerReceiveBike() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		rentalCompany.rent(customer, customerOrders1);
		Assert.assertTrue(customer.getRentedBikes().size() == 1);
	}

	@Test
	public void testTwoCustomerOrders() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		rentalCompany.rent(customer, customerOrders2);
		Assert.assertTrue(customer.getRentedBikes().size() == 2);
		Assert.assertTrue(customer.getCash().equals(new BigDecimal(55)));
		Assert.assertTrue(rentalCompany.getCash().equals(new BigDecimal(1045)));
		Assert.assertTrue(rentalCompany.getAvailableBikes().size() == 0);
	}
	
	@Test
	public void testFamilyPlan() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		rentalCompany.getAvailableBikes().add(new Bike());
		customer.setCash(new BigDecimal(1000));
		rentalCompany.rent(customer, customerOrders3);
		Assert.assertTrue(customer.getRentedBikes().size() == 3);
		Assert.assertTrue(customer.getCash().equals(new BigDecimal(842.5)));
		Assert.assertTrue(rentalCompany.getCash().equals(new BigDecimal(1157.5)));
		Assert.assertTrue(rentalCompany.getAvailableBikes().size() == 0);
	}
	
	@Test
	public void testMoreThan5CustomerOrders() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		rentalCompany.getAvailableBikes().add(new Bike());
		rentalCompany.getAvailableBikes().add(new Bike());
		rentalCompany.getAvailableBikes().add(new Bike());
		rentalCompany.getAvailableBikes().add(new Bike());
		customer.setCash(new BigDecimal(1000));
		rentalCompany.rent(customer, customerOrders4);
		Assert.assertTrue(customer.getRentedBikes().size() == 6);
		Assert.assertTrue(customer.getCash().equals(new BigDecimal(295)));
		Assert.assertTrue(rentalCompany.getCash().equals(new BigDecimal(1705)));
		Assert.assertTrue(rentalCompany.getAvailableBikes().size() == 0);
	}

	@Test
	public void testCustomerReturnBikeOperation() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		Bike bike = new Bike(7, rentalCompany);
		bike.setReturnDate(new Date());
		customer.getRentedBikes().add(bike);
		customer.setCash(new BigDecimal(1000));
		customer.giveBackBike();
		Assert.assertTrue(customer.getRentedBikes().size() == 0);
		Assert.assertTrue(rentalCompany.getAvailableBikes().size() == 3);
	}

	@Test(expected=BadRequestException.class)
	public void testCustomerReturnBikeWithEmptyRentedBikes() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		customer.giveBackBike();
	}
	
	@Test(expected=BadRequestException.class)
	public void testNullCustomer() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		rentalCompany.rent(null, customerOrders1);
	}
	
	@Test(expected=BadRequestException.class)
	public void testNullCustomerOrders() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		rentalCompany.rent(customer, null);
	}
	
	@Test(expected=BadRequestException.class)
	public void testEmptyCustomerOrders() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		rentalCompany.rent(customer, new ArrayList<CustomerOrder>());
	}

	@Test(expected=BadRequestException.class)
	public void testNullRentalStrategy() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		customerOrder1 = new CustomerOrder(null, 1);
		List<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();
		customerOrders.add(customerOrder1);
		rentalCompany.rent(customer, customerOrders);
	}

	@Test(expected=BadRequestException.class)
	public void testNullTimeQuantity() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		customerOrder1 = new CustomerOrder(rentalByDayStrategy, null);
		List<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();
		customerOrders.add(customerOrder1);
		rentalCompany.rent(customer, customerOrders);
	}
	
	@Test(expected=NotAvailableBikesException.class)
	public void testNotAvailableBikes() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		rentalCompany.setAvailableBikes(new ArrayList<Bike>());
		rentalCompany.rent(customer, customerOrders1);
	}
	
	@Test(expected=InsufficientFundsException.class)
	public void testInsufficientFunds() throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		customer.setCash(new BigDecimal(0));
		rentalCompany.rent(customer, customerOrders1);
	}
}