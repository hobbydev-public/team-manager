package hobbydev.teammanager.api.models.be.generic;

public class SuccessModel extends AbstractModel {
	
	private String message;
	
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
}
