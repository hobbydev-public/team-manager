package hobbydev.teammanager.business.services;

import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.domain.accounts.Company;
import hobbydev.teammanager.domain.accounts.offices.Office;

import java.util.List;

public interface CompanyService {
	
	List<Company> listCompanies();
	Company getCompany(Long id) throws ResourceNotFoundException;
	
	Office addOffice(Office office) throws ResourceForbiddenOperationException, ResourceNotFoundException;
	Office updateOffice(Office office) throws ResourceForbiddenOperationException, ResourceNotFoundException;
	boolean deleteOffice(Office office) throws ResourceNotFoundException;
}
