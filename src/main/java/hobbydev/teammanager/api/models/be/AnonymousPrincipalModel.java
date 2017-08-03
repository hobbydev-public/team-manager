package hobbydev.teammanager.api.models.be;

import hobbydev.teammanager.domain.accounts.User;

public class AnonymousPrincipalModel extends PrincipalModel {
	
	private String email;
	private boolean authenticated;
	
	public AnonymousPrincipalModel() {
		this(null);
	}
	
	private AnonymousPrincipalModel(User domain) {
		setId(-1L);
		setEmail("anonymous@user");
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
	public boolean isAuthenticated() {
		return authenticated;
	}
	
	@Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}
