package ar.com.fdv.rentalBusiness.rentalStrategy;

import java.io.IOException;
import java.math.BigDecimal;

import ar.com.fdv.rentalBusiness.businessException.BadRequestException;
import ar.com.fdv.rentalBusiness.domainModel.Bike;
import ar.com.fdv.rentalBusiness.utils.ApplicationProperties;
import ar.com.fdv.rentalBusiness.utils.TimeUtils;

public class RentalByHourStrategy extends RentalStrategy{

	@Override
	public BigDecimal rent(Bike bike, Integer hoursQuantity) throws IOException, BadRequestException {
		validateParameters(bike, hoursQuantity);
		setBikeReturnDate(bike, hoursQuantity);
		return calculateCharge(hoursQuantity);
	}

	private void setBikeReturnDate(Bike bike, Integer hoursQuantity) {
		bike.setReturnDate(TimeUtils.addHours(hoursQuantity));
	}

	private BigDecimal calculateCharge(Integer hoursQuantity) throws IOException {
		ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
		String chargeByHour = applicationProperties.getPropertyValue("CHARGE_BY_HOUR");	
		return new BigDecimal(new BigDecimal(hoursQuantity).multiply(new BigDecimal(chargeByHour)).intValue());
	}
}
