package hobbydev.teammanager.business.validations;

import hobbydev.teammanager.domain.accounts.Company;
import hobbydev.teammanager.domain.accounts.User;

public class AccessValidations {
	
	public static boolean canUserViewCompany(User user, Company company) {
		boolean can = false;
		
		if(company.getOwner().getId().equals(user.getId())) {
			can = true;
			return can;
		}
		
		return can;
	}
	
	public static boolean canUserViewCompanyOffices(User user, Company company) {
		boolean can = true;
		
		if(!canUserViewCompany(user, company)) {
			can = false;
			return can;
		}
		
		return can;
	}
	
	public static boolean canUserAddCompanyOffices(User user, Company company) {
		boolean can = true;
		
		if(!canUserViewCompany(user, company)) {
			can = false;
			return can;
		}
		
		if(!canUserViewCompanyOffices(user, company)) {
			can = false;
			return can;
		}
		
		return can;
	}
}
