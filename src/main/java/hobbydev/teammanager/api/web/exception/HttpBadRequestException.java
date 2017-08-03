package hobbydev.teammanager.api.web.exception;

public class HttpBadRequestException extends RuntimeException {
	
	public HttpBadRequestException(String message) {
		super(message);
	}
}
