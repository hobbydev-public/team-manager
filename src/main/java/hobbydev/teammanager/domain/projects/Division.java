package hobbydev.teammanager.domain.projects;

import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;

import java.util.List;

public class Division implements IdentifiedEntityInterface {
	
	private Long id;
	private String name;
	private List<Project> projects;
	
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
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Division division = (Division) o;
		
		return getId().equals(division.getId());
		
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}
