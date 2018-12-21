package ar.com.fdv.rentalBusiness.businessException;

public class InsufficientFundsException extends Exception{
	private static final long serialVersionUID = -2177103551045327054L;
	public String message;
	
	public InsufficientFundsException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
