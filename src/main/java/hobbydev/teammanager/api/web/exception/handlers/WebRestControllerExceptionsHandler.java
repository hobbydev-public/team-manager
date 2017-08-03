package hobbydev.teammanager.api.web.exception.handlers;
		
import hobbydev.teammanager.api.models.be.generic.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import hobbydev.teammanager.api.web.exception.HttpBadRequestException;
import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;

@ControllerAdvice(annotations = {RestController.class})
public class WebRestControllerExceptionsHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<ResponseObject> handleAccessDeniedException(Exception e) {
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<>(ResponseObject.errorResponse(e.getMessage(), e, HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
		return responseEntity;
	}
	
	@ExceptionHandler({HttpBadRequestException.class})
	public ResponseEntity<ResponseObject> handleBadRequestException(Exception e) {
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<>(ResponseObject.errorResponse(e.getMessage(), e, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<ResponseObject> handleNotFoundException(Exception e) {
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<>(ResponseObject.errorResponse(e.getMessage(), e, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
		return responseEntity;
	}
	
	@ExceptionHandler({ResourceForbiddenOperationException.class})
	public ResponseEntity<ResponseObject> handleForbiddenException(Exception e) {
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<>(ResponseObject.errorResponse(e.getMessage(), e, HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
		return responseEntity;
	}
	
	@ExceptionHandler({UsernameNotFoundException.class})
	public ResponseEntity<ResponseObject> handleUsernameNotFoundException(Exception e) {
		ResponseEntity<ResponseObject> responseEntity = new ResponseEntity<>(ResponseObject.errorResponse(e.getMessage(), e, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
		return responseEntity;
	}
}
