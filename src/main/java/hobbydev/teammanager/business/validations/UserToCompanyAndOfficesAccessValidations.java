package hobbydev.teammanager.business.validations;

import hobbydev.teammanager.domain.accounts.Company;
import hobbydev.teammanager.domain.accounts.User;
import hobbydev.teammanager.domain.accounts.offices.Office;

public class UserToCompanyAndOfficesAccessValidations {
	
	public static boolean canUserViewCompany(User user, Company company) {
		boolean can = false;
		
		if(company.getOwner().getId().equals(user.getId())) {
			can = true;
			return can;
		}
		
		return can;
	}
	
	public static boolean canUserListCompanyOffices(User user, Company company) {
		boolean can = true;
		
		if(!canUserViewCompany(user, company)) {
			can = false;
			return can;
		}
		
		return can;
	}
	
	public static boolean canUserViewCompanyOffice(User user, Company company, Office office) {
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
		
		if(!canUserListCompanyOffices(user, company)) {
			can = false;
			return can;
		}
		
		return can;
	}
	
	public static boolean canUserEditCompanyOffice(User user, Company company, Office office) {
		boolean can = true;
		
		if(!canUserViewCompany(user, company)) {
			can = false;
			return can;
		}
		
		return can;
	}
}
