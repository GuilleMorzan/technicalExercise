package ar.com.fdv.rentalBusiness.domainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.dto.CustomerOrder;
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
	
	public void rent(List<CustomerOrder> customerOrder) throws BadRequestException{
		LoggerUtils.configureLogger(LOGGER);
		LOGGER.info("A customer wants to rent one or more bikes.");
		validateParameters(customerOrder);
	}

	private void validateParameters(List<CustomerOrder> customerOrder) throws BadRequestException {
		LoggerUtils.configureLogger(LOGGER);
		if(customerOrder == null || customerOrder.size() == 0){
			LOGGER.severe("The customer order is null or empty.");
			throw new BadRequestException("The customer order is null or empty.");
		}
	}
}
