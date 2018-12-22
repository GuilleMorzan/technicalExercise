package ar.com.fdv.rentalBusiness.domainModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.businessException.InsufficientFundsException;
import ar.com.fdv.rentalBusiness.businessException.NotAvailableBikesException;
import ar.com.fdv.rentalBusiness.dto.CustomerOrder;
import ar.com.fdv.rentalBusiness.rentalStrategy.RentalStrategy;
import ar.com.fdv.rentalBusiness.utils.ApplicationProperties;
import ar.com.fdv.rentalBusiness.utils.LoggerUtils;

public class RentalCompany {
	private BigDecimal cash = new BigDecimal(0);
	private List<Bike> availableBikes = new ArrayList<Bike>();

	private static final Logger LOGGER = Logger.getLogger(RentalCompany.class.getName());
	
	public RentalCompany() {
		super();
	}

	public RentalCompany(BigDecimal cash, List<Bike> availableBikes) {
		super();
		this.cash = cash;
		this.availableBikes = availableBikes;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public List<Bike> getAvailableBikes() {
		return availableBikes;
	}

	public void setAvailableBikes(List<Bike> availableBikes) {
		this.availableBikes = availableBikes;
	}
	
	public void rent(Customer customer, List<CustomerOrder> customerOrders) throws BadRequestException, NotAvailableBikesException, IOException, InsufficientFundsException{
		List<Bike> rentedBikes = new ArrayList<Bike>();
		BigDecimal totalAmount = new BigDecimal(0);
		
		LoggerUtils.configureLogger(LOGGER);
		LOGGER.info("A customer wants to rent one or more bikes.");

		validateInputParameters(customer,customerOrders);
		for(CustomerOrder customerOrder: customerOrders){
			RentalStrategy rentalStrategy = customerOrder.getRentalStrategy();
			Integer timeQuantity = customerOrder.getTimeQuantity();
			
			validateCustomerOrder(customerOrder);
			Bike bikeToBeRent = getABike();
			totalAmount = totalAmount.add(rentalStrategy.rent(bikeToBeRent, timeQuantity));
			rentedBikes.add(bikeToBeRent);
		}
		charge(customer, totalAmount, rentedBikes);
	}

	private void validateInputParameters(Customer customer, List<CustomerOrder> customerOrders) throws BadRequestException {
		if(customer == null || customerOrders == null || customerOrders.size() == 0){
			LOGGER.severe("The customer is null or the list of customer's orders is null or empty.");
			throw new BadRequestException("The customer is null or the list of customer's orders is null or empty.");
		}
	}

	private void validateCustomerOrder(CustomerOrder customerOrder) throws BadRequestException {
		if(customerOrder.getRentalStrategy() == null || customerOrder.getTimeQuantity() == null){
			LOGGER.severe("The rental strategy or the time quantity is null.");
			throw new BadRequestException("The rental strategy or the time quantity is null.");
		}
	}
	
	private Bike getABike() throws NotAvailableBikesException {
		if(availableBikes == null || availableBikes.size() == 0){
			LOGGER.severe("The rental company does not have any bike at this moment.");
			throw new NotAvailableBikesException("The rental company does not have any bike at this moment.");
		}
		return availableBikes.remove(0);
	}
	
	private void charge(Customer customer, BigDecimal totalAmount, List<Bike> rentedBikes) throws IOException, InsufficientFundsException {
		if(isAFamilyRental(rentedBikes)){
			totalAmount = applyDiscount(totalAmount);
		}
		chargeToCustomer(customer, totalAmount);
		deliverBikesToCustomer(customer, rentedBikes);
	}

	private boolean isAFamilyRental(List<Bike> rentedBikes) throws IOException {
		ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
		String familyRentalMinimum = applicationProperties.getPropertyValue("FAMILY_RENTAL_MINIMUM");
		String familyRentalMaximum = applicationProperties.getPropertyValue("FAMILY_RENTAL_MAXIMUM");
		return ((new Integer(rentedBikes.size())).compareTo(new Integer(familyRentalMinimum)) >= 0 
					&& new Integer(rentedBikes.size()).compareTo(new Integer(familyRentalMaximum)) <= 0);
	}

	private BigDecimal applyDiscount(BigDecimal totalAmount) throws IOException {
		ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
		BigDecimal familyRentalDiscountRate = new BigDecimal(applicationProperties.getPropertyValue("FAMILY_RENTAL_DISCOUNT_RATE"));
		return totalAmount.subtract(totalAmount.multiply(familyRentalDiscountRate).divide(new BigDecimal(100)));
	}

	private void chargeToCustomer(Customer customer, BigDecimal totalAmount) throws InsufficientFundsException {
		customer.pay(totalAmount);
		cash = cash.add(totalAmount);
	}

	private void deliverBikesToCustomer(Customer customer, List<Bike> rentedBikes) {
		customer.receiveBikes(rentedBikes);
	}

	public void collect(Customer customer, Bike bikeReturned) throws IOException, InsufficientFundsException {
		availableBikes.add(bikeReturned);
		if(bikeReturned.getReturnDate().before(new Date())){
			ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
			BigDecimal fine = new BigDecimal(applicationProperties.getPropertyValue("FINE"));
			customer.pay(fine);
			cash = cash.add(fine);
		}
		bikeReturned.setReturnDate(null);
	}
}