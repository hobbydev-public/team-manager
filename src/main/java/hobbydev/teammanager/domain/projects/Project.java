package hobbydev.teammanager.domain.projects;

import hobbydev.teammanager.domain.accounts.User;
import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;

import javax.persistence.*;

@Entity
@Table(name="projects")
public class Project implements IdentifiedEntityInterface {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	private String name;
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;
	
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
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
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
