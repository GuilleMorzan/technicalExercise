package ar.com.fdv.rentalBusiness.businessException;

public class BadRequestException extends Exception{
	private static final long serialVersionUID = -4261138012187755757L;
	public String message;
	
	public BadRequestException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
