package hobbydev.teammanager.api.models.fe;

import hobbydev.teammanager.api.models.fe.generic.View;
import hobbydev.teammanager.domain.core.LogEntry;

import java.util.Arrays;

public class LogEntryView implements View<LogEntry> {
	
	private String method;
	private String url;
	private Integer statusCode;
	private String statusText;
	private String message;
	private String[] stackTrace;
	
	@Override
	public LogEntry toDomain() {
		LogEntry domain = new LogEntry();
		
		domain.setMessage(this.message);
		domain.setMethod(this.method);
		domain.setStackTrace(Arrays.deepToString(this.stackTrace));
		domain.setStatusCode(this.statusCode);
		domain.setStatusText(this.statusText);
		domain.setUrl(this.url);
		
		return domain;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getStatusText() {
		return statusText;
	}
	
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String[] getStackTrace() {
		return stackTrace;
	}
	
	public void setStackTrace(String[] stackTrace) {
		this.stackTrace = stackTrace;
	}
}
