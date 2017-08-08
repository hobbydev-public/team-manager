package hobbydev.teammanager.api.models.fe;

import hobbydev.teammanager.api.models.fe.generic.View;
import hobbydev.teammanager.domain.accounts.User;

public class UserView implements View<User> {
	
	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	
	@Override
	public User toDomain() {
		User domain = new User();
		
		domain.setId(this.id);
		domain.setEmail(this.email);
		domain.setFirstName(this.firstName);
		domain.setLastName(this.lastName);
		
		return domain;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
