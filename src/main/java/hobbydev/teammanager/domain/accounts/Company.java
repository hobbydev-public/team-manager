package hobbydev.teammanager.domain.accounts;

import hobbydev.teammanager.domain.accounts.offices.Office;
import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="companies")
public class Company /*extends Account*/ implements IdentifiedEntityInterface /*UserGroup*/ {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String name;
	@OneToOne
	@JoinColumn(name = "owner_id")
	private User owner;
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Office> offices = new ArrayList<>();
	
	/*private List<User> employees;
	private List<Project> projects;
	private List<Client> clients;*/
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	//@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public List<Office> getOffices() {
		return offices;
	}
	
	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}
	
	public void addOffice(Office office) {
		offices.add(office);
		office.setCompany(this);
	}
	
	public void removeOffice(Office office) {
		offices.remove(office);
		office.setCompany(null);
	}
	
	/*@Override
	public List<Project> getProjects() {
		return projects;
	}
	
	@Override
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	@Override
	public List<Client> getClients() {
		return clients;
	}
	
	@Override
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	public List<User> getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<User> employees) {
		this.employees = employees;
	}
	
	@Override
	@Transient
	public List<User> getUsers() {
		return getEmployees();
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
	}*/
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Company company = (Company) o;
		
		return getId().equals(company.getId());
		
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}
