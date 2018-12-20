package ar.com.fdv.domainModel;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private Float cash= 0F;
	private List<Bike> rentedBikes = new ArrayList<Bike>();
	
	public Customer() {
		super();
	}

	public Customer(Float cash, List<Bike> rentedBikes) {
		super();
		this.cash = cash;
		this.rentedBikes = rentedBikes;
	}

	public Float getCash() {
		return cash;
	}

	public void setCash(Float cash) {
		this.cash = cash;
	}

	public List<Bike> getRentedBikes() {
		return rentedBikes;
	}

	public void setRentedBikes(List<Bike> rentedBikes) {
		this.rentedBikes = rentedBikes;
	}
}
