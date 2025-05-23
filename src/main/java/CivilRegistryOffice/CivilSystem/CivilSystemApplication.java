package CivilRegistryOffice.CivilSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import CivilRegistryOffice.CivilSystem.Core.exceptions.BusinessExceptions;
import CivilRegistryOffice.CivilSystem.Core.exceptions.ProblemDetails;

@SpringBootApplication
@RestControllerAdvice
public class CivilSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CivilSystemApplication.class, args);
	}
	
	
	@ExceptionHandler
	public ProblemDetails handleException(BusinessExceptions businessExceptions) {
		ProblemDetails problemDetails =new ProblemDetails();
		problemDetails.setMessage(businessExceptions.getMessage());
		
		return problemDetails;
	}
	@ExceptionHandler
	public ProblemDetails handleExceptionIllegal(IllegalArgumentException illegalArgumentException) {
		ProblemDetails problemDetails=new ProblemDetails();
		problemDetails.setMessage(illegalArgumentException.getMessage());
		
		return problemDetails;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetails handleValidationException(MethodArgumentNotValidException ex) {
	    ProblemDetails problemDetails = new ProblemDetails();

	    String firstErrorMessage = ex.getBindingResult()
	                                 .getFieldErrors()
	                                 .stream()
	                                 .findFirst()
	                                 .map(error -> error.getDefaultMessage())
	                                 .orElse("Validation failed");

	    problemDetails.setMessage(firstErrorMessage);
	    return problemDetails;
	}
}
