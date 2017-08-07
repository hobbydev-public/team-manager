package hobbydev.teammanager.api.models.be.generic;

public class ErrorModel extends AbstractModel {
	
	private String message;
	private String[] stackTrace;
	
	@Override
	public Long getId() {
		return -1L;
	}
	
	@Override
	public void setId(Long id) {}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String[] getStackTrace() {
		return stackTrace;
	}
	
	public void setStackTrace(StackTraceElement[] stackTrace) {
		this.stackTrace = new String[stackTrace.length];
		for(int i = 0; i < stackTrace.length; i++) {
			this.stackTrace[i] = stackTrace[i].toString();
		}
	}
}
