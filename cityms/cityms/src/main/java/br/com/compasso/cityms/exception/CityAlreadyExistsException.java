package br.com.compasso.cityms.exception;

public class CityAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	String exceptionText;

	public CityAlreadyExistsException(String exceptionText) {
		this.exceptionText = exceptionText;
	}
	
}
