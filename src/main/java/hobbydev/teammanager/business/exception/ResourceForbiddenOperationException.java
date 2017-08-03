package hobbydev.teammanager.business.exception;

public class ResourceForbiddenOperationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ResourceForbiddenOperationException(String message) {
		super(message);
	}

	public ResourceForbiddenOperationException(String message, Throwable cause) {
		super(message, cause);
	}

}
