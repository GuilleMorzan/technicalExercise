package ar.com.fdv.rentalBusiness.domainModel;

import java.util.Date;

public class Bike {
	private Integer serialNumber;
	private RentalCompany rentalCompany;
	private Date returnDate = null;

	public Bike() {
		super();
	}
	
	public Bike(Integer serialNumber, RentalCompany rentalCompany) {
		super();
		this.serialNumber = serialNumber;
		this.rentalCompany = rentalCompany;
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

	public RentalCompany getRentalCompany() {
		return rentalCompany;
	}

	public void setRentalCompany(RentalCompany rentalCompany) {
		this.rentalCompany = rentalCompany;
	}
}
