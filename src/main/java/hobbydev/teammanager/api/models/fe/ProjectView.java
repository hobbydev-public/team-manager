package hobbydev.teammanager.api.models.fe;

import hobbydev.teammanager.api.models.fe.generic.View;
import hobbydev.teammanager.domain.accounts.User;
import hobbydev.teammanager.domain.projects.Project;

public class ProjectView implements View<Project> {
	
	private Long id;
	private String name = "";
	private UserView owner;
	
	@Override
	public Project toDomain() {
		Project domain = new Project();
		
		domain.setId(this.id);
		domain.setName(this.name);
		
		User owner = this.owner == null? null : this.owner.toDomain();
		domain.setOwner(owner);
		
		return domain;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public UserView getOwner() {
		return owner;
	}
	
	public void setOwner(UserView owner) {
		this.owner = owner;
	}
}
