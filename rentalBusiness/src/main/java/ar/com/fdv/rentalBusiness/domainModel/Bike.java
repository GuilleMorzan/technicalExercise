package ar.com.fdv.rentalBusiness.domainModel;

import java.util.Date;

public class Bike {
	private Integer serialNumber;
	private Date returnDate = null;

	public Bike() {
		super();
	}
	
	public Bike(Integer serialNumber) {
		super();
		this.serialNumber = serialNumber;
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
