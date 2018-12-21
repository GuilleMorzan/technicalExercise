package ar.com.fdv.rentalBusiness.rentalStrategy;

import java.io.IOException;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.domainModel.Bike;
import ar.com.fdv.rentalBusiness.utils.ApplicationProperties;
import ar.com.fdv.rentalBusiness.utils.TimeUtils;

public class RentalByWeekStrategy extends RentalStrategy{
	private final Integer HOURS_PER_WEEK = 24 * 7;
	
	@Override
	public Float rent(Bike bike, Integer weeksQuantity) throws IOException, BadRequestException {
		validateParameters(bike, weeksQuantity);
		setBikeReturnDate(bike, weeksQuantity);
		return calculateCharge(weeksQuantity);
	}

	private void setBikeReturnDate(Bike bike, Integer weeksQuantity) {
		Integer hours = weeksQuantity * HOURS_PER_WEEK;
		bike.setReturnDate(TimeUtils.addHours(hours));
	}

	private Float calculateCharge(Integer weeksQuantity) throws IOException {
		ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
		String chargeByWeek = applicationProperties.getPropertyValue("CHARGE_BY_WEEK");	
		return new Float(new Float(weeksQuantity) * new Float(chargeByWeek));
	}
}
