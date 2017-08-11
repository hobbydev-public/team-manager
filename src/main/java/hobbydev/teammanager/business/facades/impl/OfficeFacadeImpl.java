package hobbydev.teammanager.business.facades.impl;

import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.business.facades.OfficeFacade;
import hobbydev.teammanager.business.services.CompanyService;
import hobbydev.teammanager.business.services.UserService;
import hobbydev.teammanager.domain.accounts.Company;
import hobbydev.teammanager.domain.accounts.User;
import hobbydev.teammanager.domain.accounts.offices.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static hobbydev.teammanager.business.validations.UserToCompanyAndOfficesAccessValidations.canUserAddCompanyOffices;
import static hobbydev.teammanager.business.validations.UserToCompanyAndOfficesAccessValidations.canUserListCompanyOffices;
import static hobbydev.teammanager.business.validations.UserToCompanyAndOfficesAccessValidations.canUserViewCompanyOffice;

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
		
		if(!canUserListCompanyOffices(user, company)) {
			throw new ResourceForbiddenOperationException("User with ID=[" + userId + "] cannot see a list of offices for company with ID=[" + companyId + "]");
		}
		
		offices = company.getOffices();
		
		return offices;
	}
	
	@Override
	public Office getOffice(Long companyId, Long officeId, Long userId) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		Company company = companyService.getCompany(companyId);
		User user = userService.getUser(userId);
		
		Office office = listOffices(companyId, userId).stream()
				.filter(o -> o.getId().equals(officeId))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(officeId, Office.class.getSimpleName()));
		
		if(!canUserViewCompanyOffice(user, company, office)) {
			throw new ResourceForbiddenOperationException("User with ID=[" + userId + "] cannot see an offices with ID=[" + officeId + "] of company with ID=[" + companyId + "]");
		}
		
		return office;
	}
	
	@Override
	public Office addOffice(Long companyId, String officeName, Long userId) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		Company company = companyService.getCompany(companyId);
		User user = userService.getUser(userId);
		
		if(!canUserAddCompanyOffices(user, company)) {
			throw new ResourceForbiddenOperationException("User with ID=[" + userId + "] cannot add offices for company with ID=[" + companyId + "]");
		}
		
		Office office = new Office();
		office.setName(officeName);
		office.setCompany(company);
		
		return companyService.addOffice(office);
	}
}
