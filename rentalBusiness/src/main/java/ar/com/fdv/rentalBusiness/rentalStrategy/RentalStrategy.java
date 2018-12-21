package ar.com.fdv.rentalBusiness.rentalStrategy;

import java.io.IOException;
import java.math.BigDecimal;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.domainModel.Bike;

public abstract class RentalStrategy {

	public BigDecimal rent(Bike bike, Integer quantity) throws IOException, BadRequestException{
		return new BigDecimal(0);
	}
	
	public void validateParameters(Bike bike, Integer timeQuantity) throws BadRequestException{
		if(bike == null || timeQuantity == null || timeQuantity < 1){
			throw new BadRequestException("The bike or the time quantity is NULL or the time quantity is 0 or a negative number.");
		}
	}
}
