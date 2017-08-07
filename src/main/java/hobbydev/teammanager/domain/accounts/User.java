package hobbydev.teammanager.domain.accounts;

import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="users")
public class User /*extends Account*/ implements UserDetails, IdentifiedEntityInterface {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name = "restore_key")
	private String restoreKey = null;
	
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	
	@OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	private Company company;
	
	/*
	@Column(name = "start_work_date")
	private LocalDate startWorkDate = LocalDate.now();
	@Column(name = "birth_date")
	private LocalDate birthDate = LocalDate.now();
	@ManyToOne
	@JoinColumn(name = "position_id")
	private Position position;
	@OneToMany(mappedBy = "userOwner", orphanRemoval = true)
	private List<Project> projects;
	@OneToMany(mappedBy = "userOwner", orphanRemoval = true)
	private List<Client> clients;
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	private List<ProjectMember> projectsMember;*/
	

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRestoreKey() {
		return restoreKey;
	}
	
	public void setRestoreKey(String restoreKey) {
		this.restoreKey = restoreKey;
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
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		if(company == null) {
			removeCompany();
		}
		company.setOwner(this);
		this.company = company;
	}
	
	public void removeCompany() {
		if(this.company != null) {
			this.company.setOwner(null);
			this.company = null;
		}
	}
	
	/*public String getFirstName() {
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
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	@Override
	public List<Client> getClients() {
		return clients;
	}
	
	@Override
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	@Override
	public List<Project> getProjects() {
		return projects;
	}
	
	@Override
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public List<ProjectMember> getProjectsMember() {
		return projectsMember;
	}
	
	public void setProjectsMember(List<ProjectMember> projectsMember) {
		this.projectsMember = projectsMember;
	}
	
	public String getRestoreKey() {
		return restoreKey;
	}
	
	public void setRestoreKey(String restoreKey) {
		this.restoreKey = restoreKey;
	}
	
	public LocalDate getStartWorkDate() {
		return startWorkDate;
	}
	
	public void setStartWorkDate(LocalDate startWorkDate) {
		this.startWorkDate = startWorkDate;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}*/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.NO_AUTHORITIES;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return true;
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return isEnabled();
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return isEnabled();
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return isEnabled();
	}
	
	/*@Override
	@Transient
	public String getName() {
		return firstName + " " + lastName;
	}
	
	@Override
	public void setName(String name) {
		
	}*/
}
