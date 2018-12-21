package ar.com.fdv.rentalBusiness.rentalStrategy;

import java.io.IOException;

import ar.com.fdv.rentalBusiness.domainModel.Bike;

public interface RentalStrategy {

	public Float rent(Bike bike, Integer quantity) throws IOException;
}
