package hobbydev.teammanager.api.models.be;

import hobbydev.teammanager.domain.accounts.User;

public class AnonymousPrincipalModel extends PrincipalModel {
	
	public AnonymousPrincipalModel() {
		this(null);
	}
	
	private AnonymousPrincipalModel(User domain) {
		setId(-1L);
		setEmail("anonymous@user");
		setFirstName("Anonymous");
		setLastName("User");
		setAuthenticated(false);
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String getEmail() {
		return email;
	}
	
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String getFirstName() {
		return super.getFirstName();
	}
	
	@Override
	public void setFirstName(String firstName) {
		super.setFirstName(firstName);
	}
	
	@Override
	public String getLastName() {
		return super.getLastName();
	}
	
	@Override
	public void setLastName(String lastName) {
		super.setLastName(lastName);
	}
	
	@Override
	public String getFullName() {
		return super.getFullName();
	}
	
	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}
	
	@Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
