package hobbydev.teammanager.business.exception;

public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ResourceNotFoundException(Long id, String resourceName) {
		super(resourceName + " resource with ID=[" + id + "] is not found.");
	}

}
