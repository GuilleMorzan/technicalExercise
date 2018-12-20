package ar.com.fdv.domainModel;

import java.util.Date;

public class Bike {
	private Integer serialNumber;
	private Date returnTime = null;

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

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
}
