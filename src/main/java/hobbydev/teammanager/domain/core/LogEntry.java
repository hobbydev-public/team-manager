package hobbydev.teammanager.domain.core;

import hobbydev.teammanager.domain.accounts.User;
import org.hibernate.annotations.Type;
import org.hibernate.type.StringType;
import org.hibernate.type.TextType;

import javax.persistence.*;

@Entity
@Table(name = "logs")
public class LogEntry implements IdentifiedEntityInterface {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="method")
	private String method;
	@Column(name="url")
	private String url;
	@Column(name="status_code")
	private Integer statusCode;
	@Column(name="status_text")
	private String statusText;
	@Column(name="message")
	private String message;
	@Column(name="stack_trace")
	@Type(type="text")
	private String stackTrace;
	
	@ManyToOne
	@JoinColumn(name = "session_user_id")
	private User sessionUser;
	
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
	
	public String getStackTrace() {
		return stackTrace;
	}
	
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	
	public User getSessionUser() {
		return sessionUser;
	}
	
	public void setSessionUser(User sessionUser) {
		this.sessionUser = sessionUser;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		LogEntry logEntry = (LogEntry) o;
		
		return getId().equals(logEntry.getId());
		
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}
