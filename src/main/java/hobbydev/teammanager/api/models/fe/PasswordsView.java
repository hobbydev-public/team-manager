package hobbydev.teammanager.api.models.fe;

public class PasswordsView {
	
	private String oldPass;
	private String newPass;
	
	public String getOldPass() {
		return oldPass;
	}
	
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	
	public String getNewPass() {
		return newPass;
	}
	
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
}
