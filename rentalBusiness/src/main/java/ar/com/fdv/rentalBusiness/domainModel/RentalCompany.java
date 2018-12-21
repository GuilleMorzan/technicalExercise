package ar.com.fdv.rentalBusiness.domainModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.businessException.NotAvailableBikesException;
import ar.com.fdv.rentalBusiness.dto.CustomerOrder;
import ar.com.fdv.rentalBusiness.rentalStrategy.RentalStrategy;
import ar.com.fdv.rentalBusiness.utils.LoggerUtils;

public class RentalCompany {
	private Float cash = 0F;
	private List<Bike> availableBikes = new ArrayList<Bike>();

	private static final Logger LOGGER = Logger.getLogger(RentalCompany.class.getName());
	
	public RentalCompany() {
		super();
	}

	public RentalCompany(Float cash, List<Bike> availableBikes) {
		super();
		this.cash = cash;
		this.availableBikes = availableBikes;
	}

	public Float getCash() {
		return cash;
	}

	public void setCash(Float cash) {
		this.cash = cash;
	}

	public List<Bike> getAvailableBikes() {
		return availableBikes;
	}

	public void setAvailableBikes(List<Bike> availableBikes) {
		this.availableBikes = availableBikes;
	}
	
	public void rent(List<CustomerOrder> customerOrders) throws BadRequestException, NotAvailableBikesException, IOException{
		List<Bike> rentedBikes = new ArrayList<Bike>();
		Float totalAmount = 0F;
		
		LoggerUtils.configureLogger(LOGGER);
		LOGGER.info("A customer wants to rent one or more bikes.");

		validateInputParameter(customerOrders);
		for(CustomerOrder customerOrder: customerOrders){
			RentalStrategy rentalStrategy = customerOrder.getRentalStrategy();
			Integer timeQuantity = customerOrder.getTimeQuantity();
			
			validateCustomerOrder(customerOrder);
			Bike bikeToBeRent = getABike();
			totalAmount = totalAmount + rentalStrategy.rent(bikeToBeRent, timeQuantity);
			rentedBikes.add(bikeToBeRent);
		}
	}

	private void validateInputParameter(List<CustomerOrder> customerOrders) throws BadRequestException {
		if(customerOrders == null || customerOrders.size() == 0){
			LOGGER.severe("The list of customer orders is null or empty.");
			throw new BadRequestException("The list of customer orders is null or empty.");
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
		return availableBikes.get(0);
	}
}