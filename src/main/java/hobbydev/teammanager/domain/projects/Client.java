package hobbydev.teammanager.domain.projects;

import hobbydev.teammanager.domain.accounts.Company;
import hobbydev.teammanager.domain.accounts.User;
import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;

import java.util.List;

public class Client implements IdentifiedEntityInterface {
	
	private Long id;
	private String name;
	private User userOwner;
	private Company companyOwner;
	
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
	
	public User getUserOwner() {
		return userOwner;
	}
	
	public void setUserOwner(User userOwner) {
		this.userOwner = userOwner;
	}
	
	public Company getCompanyOwner() {
		return companyOwner;
	}
	
	public void setCompanyOwner(Company companyOwner) {
		this.companyOwner = companyOwner;
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
		
		Client client = (Client) o;
		
		return getId().equals(client.getId());
		
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}
