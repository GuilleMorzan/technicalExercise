package ar.com.fdv.rentalBusiness.domainModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.businessException.InsufficientFundsException;
import ar.com.fdv.rentalBusiness.utils.LoggerUtils;

public class Customer {
	private BigDecimal cash= new BigDecimal(0);
	private List<Bike> rentedBikes = new ArrayList<Bike>();
	private static final Logger LOGGER = Logger.getLogger(Customer.class.getName());
	
	public Customer() {
		super();
	}

	public Customer(BigDecimal cash, List<Bike> rentedBikes) {
		super();
		this.cash = cash;
		this.rentedBikes = rentedBikes;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public List<Bike> getRentedBikes() {
		return rentedBikes;
	}

	public void setRentedBikes(List<Bike> rentedBikes) {
		this.rentedBikes = rentedBikes;
	}

	public void pay(BigDecimal totalAmount) throws InsufficientFundsException {
		if(customerHasSufficientFunds(totalAmount)){
			cash = cash.subtract(totalAmount);
		}
	}

	private boolean customerHasSufficientFunds(BigDecimal totalAmount) throws InsufficientFundsException {
		if(cash.compareTo(totalAmount) < 0){
			LoggerUtils.configureLogger(LOGGER);
			LOGGER.severe("The customer has not enough money. He has " + cash + " dollars.");
			throw new InsufficientFundsException("The customer has not enough money.");
		}
		return true;
	}

	public void getBikes(List<Bike> bikes) {
		this.rentedBikes.addAll(bikes);
	}
	
	public void giveBackBike() throws BadRequestException, IOException, InsufficientFundsException{
		if(rentedBikes == null || rentedBikes.size() == 0){
			LoggerUtils.configureLogger(LOGGER);
			LOGGER.severe("The customer does not have any bike");
			throw new BadRequestException("The customer does not have any bike");
		}
		
		Bike bikeToBeReturned = rentedBikes.remove(0);
		RentalCompany rentalCompany = bikeToBeReturned.getRentalCompany();
		rentalCompany.collect(this, bikeToBeReturned);
	}
}
