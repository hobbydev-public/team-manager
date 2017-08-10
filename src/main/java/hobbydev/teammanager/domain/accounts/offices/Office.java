package hobbydev.teammanager.domain.accounts.offices;

import hobbydev.teammanager.domain.accounts.Company;
import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;

import javax.persistence.*;

@Entity
@Table(name = "offices")
public class Office implements IdentifiedEntityInterface {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
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
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Office office = (Office) o;
		
		return getId().equals(office.getId());
		
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}
