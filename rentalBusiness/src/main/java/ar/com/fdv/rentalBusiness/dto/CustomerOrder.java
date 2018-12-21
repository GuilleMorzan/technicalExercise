package ar.com.fdv.rentalBusiness.dto;

import ar.com.fdv.rentalBusiness.rentalStrategy.RentalStrategy;

public class CustomerOrder {

	private RentalStrategy rentalStrategy;
	private Integer timeQuantity;

	public CustomerOrder(RentalStrategy rentalStrategy, Integer timeQuantity) {
		super();
		this.rentalStrategy = rentalStrategy;
		this.timeQuantity = timeQuantity;
	}

	public RentalStrategy getRentalStrategy() {
		return rentalStrategy;
	}

	public void setRentalStrategy(RentalStrategy rentalStrategy) {
		this.rentalStrategy = rentalStrategy;
	}

	public Integer getTimeQuantity() {
		return timeQuantity;
	}

	public void setTimeQuantity(Integer timeQuantity) {
		this.timeQuantity = timeQuantity;
	}
}
