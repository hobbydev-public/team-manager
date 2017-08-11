package hobbydev.teammanager.business.services.impl;

import hobbydev.teammanager.business.AbstractService;
import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.business.services.CompanyService;
import hobbydev.teammanager.domain.accounts.Company;
import hobbydev.teammanager.domain.accounts.offices.Office;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl extends AbstractService implements CompanyService {
	
	@Override
	protected Class<Company> getEntityClass() {
		return Company.class;
	}
	
	@Override
	@Transactional
	public List<Company> listCompanies() {
		return getDAO().getAll(getEntityClass());
	}
	
	@Override
	@Transactional
	public Company getCompany(Long id) throws ResourceNotFoundException {
		return listCompanies().stream()
				.filter(company -> company.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(id, getEntityClass().getSimpleName()));
	}
	
	@Override
	@Transactional
	public Office addOffice(Office office) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		if(office.getCompany() == null) {
			throw new ResourceForbiddenOperationException("Office can be added only in the context of some company");
		}
		
		Long companyId = office.getCompany().getId();
		if(companyId == null) {
			throw new ResourceForbiddenOperationException("Office can be only added for already existing company");
		}
		
		Company company = getCompany(companyId);
		company.addOffice(office);
		
		return office;
	}
	
	@Override
	@Transactional
	public Office updateOffice(Office updatedOffice) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		if(updatedOffice.getCompany() == null) {
			throw new ResourceForbiddenOperationException("Office can be updated only in the context of some company");
		}
		
		Long companyId = updatedOffice.getCompany().getId();
		if(companyId == null) {
			throw new ResourceForbiddenOperationException("Office can be only updated for already existing company");
		}
		
		Office persistant = getCompany(updatedOffice.getCompany().getId()).getOffices().stream()
				.filter(office -> office.getId().equals(updatedOffice.getId()))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(updatedOffice.getId(), updatedOffice.getClass().getSimpleName()));
		
		persistant.setName(updatedOffice.getName());
		return persistant;
	}
	
	@Override
	@Transactional
	public boolean deleteOffice(Office office) throws ResourceNotFoundException {
		if(office == null) {
			throw new IllegalArgumentException("Office is null");
		}
		
		if(office.getId() == null || Long.valueOf(0).compareTo(office.getId()) >= 0) {
			throw new ResourceNotFoundException("Office ID does not exist. Office can only be deleted with valid ID.");
		}
		
		if(office.getCompany() == null) {
			throw new IllegalArgumentException("Office does not have a company");
		}
		
		if(office.getCompany().getId() == null || Long.valueOf(0).compareTo(office.getCompany().getId()) >= 0) {
			throw new ResourceNotFoundException("Company ID does not exist. Office can only be deleted with valid company ID.");
		}
		
		Company persistant = getCompany(office.getCompany().getId());
		if(!persistant.getOffices().contains(office)) {
			return false;
		}
		
		persistant.removeOffice(office);
		return true;
	}
}
