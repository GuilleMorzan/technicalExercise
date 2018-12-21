package ar.com.fdv.rentalBusiness.domainModel;

import java.util.ArrayList;
import java.util.List;

public class RentalCompany {
	private Float cash = 0F;
	private List<Bike> availableBikes = new ArrayList<Bike>();
	
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
}
