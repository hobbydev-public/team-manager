package hobbydev.teammanager.api.models.be.generic;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ResponseObject<DATA> {
	
	private boolean success = true;
	private HttpStatus status = HttpStatus.OK;
	private int code = status.value();
	private String message = "";
	private boolean isArray = false;
	private DATA data;
	
	public ResponseObject(DATA data) {
		this.data = data;
		if(data instanceof Collection) {
			isArray = true;
		}
	}
	
	private ResponseObject(){}
	
	public static ResponseObject<List<StackTraceElement>> errorResponse(String message, Exception cause, HttpStatus status) {
		ResponseObject<List<StackTraceElement>> errorResponseObject = new ResponseObject<>(Arrays.asList(cause.getStackTrace()));
		errorResponseObject.setArray(true);
		errorResponseObject.setStatus(status);
		errorResponseObject.setCode(status.value());
		errorResponseObject.setMessage(message);
		errorResponseObject.setSuccess(false);
		
		return errorResponseObject;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isArray() {
		return isArray;
	}
	
	public void setArray(boolean array) {
		isArray = array;
	}
	
	public DATA getData() {
		return data;
	}
	
	public void setData(DATA data) {
		this.data = data;
	}
}
