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

import static hobbydev.teammanager.business.validations.UserToCompanyAndOfficesAccessValidations.*;

@Service
public class OfficeFacadeImpl implements OfficeFacade {
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	
	@Override
	public List<Office> listOffices(Long companyId, Long userId) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		if(companyId == null || Long.valueOf(0L).compareTo(companyId) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be obtained for only a company with valid ID");
		}
		
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
		if(companyId == null || Long.valueOf(0L).compareTo(companyId) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be obtained for only a company with valid ID");
		}
		
		if(officeId == null || Long.valueOf(0L).compareTo(officeId) >= 0) {
			throw new ResourceForbiddenOperationException("Office can be obtained only by valid ID");
		}
		
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
		if(companyId == null || Long.valueOf(0L).compareTo(companyId) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be created for only a company with valid ID");
		}
		
		if(officeName == null || officeName.trim().isEmpty()) {
			throw new ResourceForbiddenOperationException("Offices without names cannot be created.");
		}
		
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
	
	@Override
	public Office updateOffice(Office updatedOffice, Long userId) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		if(updatedOffice == null) {
			throw new IllegalArgumentException("Office is null");
		}
		
		Long updatedOfficeId = updatedOffice.getId();
		
		if(updatedOfficeId == null || Long.valueOf(0L).compareTo(updatedOfficeId) >= 0) {
			throw new ResourceForbiddenOperationException("Office can be updated only if it has a valid ID.");
		}
		
		if(updatedOffice.getCompany() == null) {
			throw new ResourceForbiddenOperationException("Office can be updated only for existing company.");
		}
		
		Long companyId = updatedOffice.getCompany().getId();
		
		if(companyId == null || Long.valueOf(0L).compareTo(companyId) >= 0) {
			throw new ResourceForbiddenOperationException("Office can be updated only for company with valid ID.");
		}
		
		Company company = companyService.getCompany(companyId);
		User user = userService.getUser(userId);
		Office office = getOffice(company.getId(), updatedOffice.getId(), user.getId());
		
		if(!canUserEditCompanyOffice(user, company, office)) {
			throw new ResourceForbiddenOperationException("User with ID=[" + userId + "] cannot edit an office with ID=[" + office.getId() + "] of company with ID=[" + companyId + "]");
		}
		
		return companyService.updateOffice(updatedOffice);
	}
}
