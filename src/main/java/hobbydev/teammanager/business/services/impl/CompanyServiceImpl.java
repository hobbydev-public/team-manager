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
}
