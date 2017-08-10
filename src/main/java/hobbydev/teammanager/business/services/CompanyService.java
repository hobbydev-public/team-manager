package hobbydev.teammanager.business.services;

import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.domain.accounts.Company;

import java.util.List;

public interface CompanyService {
	
	List<Company> listCompanies();
	Company getCompany(Long id) throws ResourceNotFoundException;
}
