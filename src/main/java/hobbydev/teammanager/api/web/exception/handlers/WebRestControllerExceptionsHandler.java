package hobbydev.teammanager.api.web.exception.handlers;
		
import hobbydev.teammanager.api.models.be.generic.ErrorModel;
import hobbydev.teammanager.api.web.exception.HttpBadRequestException;
import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = {RestController.class})
public class WebRestControllerExceptionsHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<ErrorModel> handleAccessDeniedException(Exception e) {
		ErrorModel errorModel = new ErrorModel();
		errorModel.setMessage(e.getMessage());
		errorModel.setStackTrace(e.getStackTrace());
		ResponseEntity<ErrorModel> response = new ResponseEntity<ErrorModel>(errorModel, HttpStatus.FORBIDDEN);
		
		return response;
	}
	
	@ExceptionHandler({HttpBadRequestException.class})
	public ResponseEntity<ErrorModel> handleBadRequestException(Exception e) {
		ErrorModel errorModel = new ErrorModel();
		errorModel.setMessage(e.getMessage());
		errorModel.setStackTrace(e.getStackTrace());
		ResponseEntity<ErrorModel> response = new ResponseEntity<ErrorModel>(errorModel, HttpStatus.BAD_REQUEST);
		
		return response;
	}
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<ErrorModel> handleNotFoundException(Exception e) {
		ErrorModel errorModel = new ErrorModel();
		errorModel.setMessage(e.getMessage());
		errorModel.setStackTrace(e.getStackTrace());
		ResponseEntity<ErrorModel> response = new ResponseEntity<ErrorModel>(errorModel, HttpStatus.NOT_FOUND);
		
		return response;
	}
	
	@ExceptionHandler({ResourceForbiddenOperationException.class})
	public ResponseEntity<ErrorModel> handleForbiddenException(Exception e) {
		ErrorModel errorModel = new ErrorModel();
		errorModel.setMessage(e.getMessage());
		errorModel.setStackTrace(e.getStackTrace());
		ResponseEntity<ErrorModel> response = new ResponseEntity<ErrorModel>(errorModel, HttpStatus.UNPROCESSABLE_ENTITY);
		
		return response;
	}
	
	@ExceptionHandler({UsernameNotFoundException.class})
	public ResponseEntity<ErrorModel> handleUsernameNotFoundException(Exception e) {
		ErrorModel errorModel = new ErrorModel();
		errorModel.setMessage(e.getMessage());
		errorModel.setStackTrace(e.getStackTrace());
		ResponseEntity<ErrorModel> response = new ResponseEntity<ErrorModel>(errorModel, HttpStatus.NOT_FOUND);
		
		return response;
	}
}
