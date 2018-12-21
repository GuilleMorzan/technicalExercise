package ar.com.fdv.rentalBusiness.rentalStrategy;

import java.io.IOException;
import java.math.BigDecimal;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.domainModel.Bike;
import ar.com.fdv.rentalBusiness.utils.ApplicationProperties;
import ar.com.fdv.rentalBusiness.utils.TimeUtils;

public class RentalByDayStrategy extends RentalStrategy{
	private final Integer HOURS_PER_DAY = 24;
	
	@Override
	public BigDecimal rent(Bike bike, Integer daysQuantity) throws IOException, BadRequestException {
		validateParameters(bike, daysQuantity);
		setBikeReturnDate(bike, daysQuantity);
		return calculateCharge(daysQuantity);
	}

	private void setBikeReturnDate(Bike bike, Integer daysQuantity) {
		Integer hours = daysQuantity * HOURS_PER_DAY;
		bike.setReturnDate(TimeUtils.addHours(hours));
	}

	private BigDecimal calculateCharge(Integer daysQuantity) throws IOException {
		ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
		String chargeByDay = applicationProperties.getPropertyValue("CHARGE_BY_DAY");	
		return new BigDecimal(new BigDecimal(daysQuantity).multiply(new BigDecimal(chargeByDay)).intValue());
	}
}
