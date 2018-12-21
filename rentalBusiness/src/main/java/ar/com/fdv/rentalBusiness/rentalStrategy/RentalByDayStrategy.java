package ar.com.fdv.rentalBusiness.rentalStrategy;

import java.io.IOException;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.domainModel.Bike;
import ar.com.fdv.rentalBusiness.utils.ApplicationProperties;
import ar.com.fdv.rentalBusiness.utils.TimeUtils;

public class RentalByDayStrategy extends RentalStrategy{
	private final Integer HOURS_PER_DAY = 24;
	
	@Override
	public Float rent(Bike bike, Integer daysQuantity) throws IOException, BadRequestException {
		validateParameters(bike, daysQuantity);
		setBikeReturnDate(bike, daysQuantity);
		return calculateCharge(daysQuantity);
	}

	private void setBikeReturnDate(Bike bike, Integer daysQuantity) {
		Integer hours = daysQuantity * HOURS_PER_DAY;
		bike.setReturnDate(TimeUtils.addHours(hours));
	}

	private Float calculateCharge(Integer daysQuantity) throws IOException {
		ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
		String chargeByDay = applicationProperties.getPropertyValue("CHARGE_BY_DAY");	
		return new Float(new Float(daysQuantity) * new Float(chargeByDay));
	}
}
