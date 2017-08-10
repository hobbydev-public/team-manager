package hobbydev.teammanager.api.models.be;

import hobbydev.teammanager.api.models.be.generic.AbstractModel;
import hobbydev.teammanager.domain.accounts.User;
import hobbydev.teammanager.domain.core.LogEntry;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

public class LogEntryModel extends AbstractModel {
	
	private String method;
	private String url;
	private Integer statusCode;
	private String statusText;
	private String message;
	private List<String> stackTrace;
	private UserModel sessionUser;
	
	public LogEntryModel(LogEntry domain) {
		super(domain);
		setMethod(domain.getMethod());
		setUrl(domain.getUrl());
		setStatusCode(domain.getStatusCode());
		setStatusText(domain.getStatusText());
		setMessage(domain.getMessage());
		
		User user = domain.getSessionUser();
		
		setSessionUser(user == null? null : new ShallowUserModel(user));
		
		String rawStackTrace = domain.getStackTrace();
		rawStackTrace = rawStackTrace.substring(1, rawStackTrace.length() - 1);
		String[] stackTraceArray = StringUtils.delimitedListToStringArray(rawStackTrace, ", ");
		List<String> stackTraceList = Arrays.asList(stackTraceArray);
		setStackTrace(stackTraceList);
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
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
	
	public List<String> getStackTrace() {
		return stackTrace;
	}
	
	public void setStackTrace(List<String> stackTrace) {
		this.stackTrace = stackTrace;
	}
	
	public UserModel getSessionUser() {
		return sessionUser;
	}
	
	public void setSessionUser(UserModel sessionUser) {
		this.sessionUser = sessionUser;
	}
}
