package hobbydev.teammanager.domain.projects;

import hobbydev.teammanager.domain.accounts.Company;
import hobbydev.teammanager.domain.accounts.User;
import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;
import hobbydev.teammanager.domain.core.UserGroup;

import javax.persistence.Transient;
import java.util.List;
import java.util.stream.Collectors;

public class Project implements IdentifiedEntityInterface, UserGroup {
	
	private Long id;
	private String name;
	private User userOwner;
	private Company companyOwner;
	private Client client;
	private List<ProjectRole> projectRoles;
	private List<Division> divisions;
	private List<ProjectMember> members;
	
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
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public List<ProjectRole> getProjectRoles() {
		return projectRoles;
	}
	
	public void setProjectRoles(List<ProjectRole> projectRoles) {
		this.projectRoles = projectRoles;
	}
	
	public List<Division> getDivisions() {
		return divisions;
	}
	
	public void setDivisions(List<Division> divisions) {
		this.divisions = divisions;
	}
	
	public List<ProjectMember> getMembers() {
		return members;
	}
	
	public void setMembers(List<ProjectMember> members) {
		this.members = members;
	}
	
	@Override
	@Transient
	public List<User> getUsers() {
		List<User> users = getMembers().stream()
				.map(member -> member.getUser())
				.collect(Collectors.toList());
		return users;
	}
	
	@Override
	@Transient
	public List<? extends IdentifiedEntityInterface> getGroupMembers() {
		return getUsers();
	}
	
	@Override
	@Transient
	public List<? extends IdentifiedEntityInterface> getElements() {
		return getGroupMembers();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Project client = (Project) o;
		
		return getId().equals(client.getId());
		
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}
