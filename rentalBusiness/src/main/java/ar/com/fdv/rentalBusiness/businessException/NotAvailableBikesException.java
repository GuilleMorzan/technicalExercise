package ar.com.fdv.rentalBusiness.businessException;


public class NotAvailableBikesException extends Exception {
	private static final long serialVersionUID = 8104079622098281485L;
	public String message;

	public NotAvailableBikesException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
