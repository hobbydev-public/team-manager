package hobbydev.teammanager.api.models.be;

import hobbydev.teammanager.api.models.be.generic.AbstractModel;
import hobbydev.teammanager.domain.projects.Project;

public class ProjectModel extends AbstractModel {
	
	private String name;
	private ShallowUserModel owner;
	
	public ProjectModel(Project domain) {
		super(domain);
		
		this.name = domain.getName();
		this.owner = new ShallowUserModel(domain.getOwner());
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ShallowUserModel getOwner() {
		return owner;
	}
	
	public void setOwner(ShallowUserModel owner) {
		this.owner = owner;
	}
}
