package hobbydev.teammanager.api.models.fe;

import hobbydev.teammanager.api.models.fe.generic.View;
import hobbydev.teammanager.domain.accounts.Company;
import hobbydev.teammanager.domain.accounts.offices.Office;

public class OfficeView implements View<Office> {
	
	private Long id;
	private String name = "";
	private CompanyView company;
	
	@Override
	public Office toDomain() {
		Office domain = new Office();
		
		domain.setId(this.id);
		domain.setName(this.name);
		
		Company company = this.company == null? null : this.company.toDomain();
		domain.setCompany(company);
		
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
	
	public CompanyView getCompany() {
		return company;
	}
	
	public void setCompany(CompanyView company) {
		this.company = company;
	}
}
