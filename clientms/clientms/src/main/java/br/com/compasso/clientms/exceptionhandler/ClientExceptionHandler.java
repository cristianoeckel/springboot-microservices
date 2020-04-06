package br.com.compasso.clientms.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

/**
 * Handles exceptions that are valid for the entire application.
 *
 * @author cristiano.eckel
 *
 */
@ControllerAdvice
public class ClientExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	/**
	 * Handle messages when BeanValidation signals that it has an incorrect field.
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Error> errors = createErrorList(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * Handle exceptions when no data was found in the database.
	 */
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String userMessage = ex.getMessage();
		String developerMessage = ex.toString();
		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));

		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	/**
	 * Handle exceptions when JSON cannot be deserialized.
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// Handles errors in the parse of fields, to return a more user-friendly
		// message.
		if (ex.getCause() instanceof InvalidFormatException) {
			String userMessage = "Some field is filled in with an incorrect format. For dates, use the yyyy-DD-mm format.";
			String developerMessage = ex.toString();
			List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));

			return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		} else {
			String userMessage = ex.getMessage();
			String developerMessage = ex.toString();
			List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));

			return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
		}
	}

	/**
	 * Handle invalid fields, as several fields may be in error.
	 *
	 * @param bindingResult
	 * @return List of errors to return to the user.
	 */
	private List<Error> createErrorList(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			// Use the messageSource by passing the fieldError directly, because in the
			// Properties file, we treat the entity's fields.
			String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String developerMessage = fieldError.toString();
			errors.add(new Error(userMessage, developerMessage));
		}
		return errors;
	}

	/**
	 * Class to organize the error messages that will be returned to users.
	 *
	 */
	public static class Error {

		/**
		 * Friendly message, to be presented to the user.
		 */
		private String userMessage;

		/**
		 * Message to be presented to the developer, to help him find the problem.
		 */
		private String developerMessage;

		public Error(String userMessage, String developerMessage) {
			this.userMessage = userMessage;
			this.developerMessage = developerMessage;
		}

		public String getUserMessage() {
			return userMessage;
		}

		public String getDeveloperMessage() {
			return developerMessage;
		}
	}
}