package hobbydev.teammanager.api.models.fe;

import hobbydev.teammanager.api.models.fe.generic.View;
import hobbydev.teammanager.domain.accounts.Company;

public class CompanyView implements View<Company> {
	
	private String name = "";
	
	@Override
	public Company toDomain() {
		Company domain = new Company();
		
		domain.setName(this.name);
		
		return domain;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
