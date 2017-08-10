package hobbydev.teammanager.business.facades.impl;

import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.business.facades.OfficeFacade;
import hobbydev.teammanager.business.services.CompanyService;
import hobbydev.teammanager.business.services.UserService;
import hobbydev.teammanager.business.validations.AccessValidations;
import hobbydev.teammanager.domain.accounts.Company;
import hobbydev.teammanager.domain.accounts.User;
import hobbydev.teammanager.domain.accounts.offices.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficeFacadeImpl implements OfficeFacade {
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	
	@Override
	public List<Office> listOffices(Long companyId, Long userId) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		List<Office> offices = new ArrayList<>();
		
		Company company = companyService.getCompany(companyId);
		User user = userService.getUser(userId);
		
		if(!AccessValidations.canUserViewCompanyOffices(user, company)) {
			throw new ResourceForbiddenOperationException("User with ID=[" + userId + "] cannot see a list of offices for company with ID=[" + companyId + "]");
		}
		
		offices = company.getOffices();
		
		return offices;
	}
}
