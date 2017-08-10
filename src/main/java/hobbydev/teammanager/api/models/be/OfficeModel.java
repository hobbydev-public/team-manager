package hobbydev.teammanager.api.models.be;

import hobbydev.teammanager.api.models.be.generic.AbstractModel;
import hobbydev.teammanager.domain.accounts.offices.Office;

public class OfficeModel extends AbstractModel {
	
	private String name;
	private CompanyModel company;
	
	public OfficeModel(Office domain) {
		super(domain);
		
		this.name = domain.getName();
		this.company = new CompanyModel(domain.getCompany());
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
	
	public CompanyModel getCompany() {
		return company;
	}
	
	public void setCompany(CompanyModel company) {
		this.company = company;
	}
}
