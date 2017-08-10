package hobbydev.teammanager.business.facades;

import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.domain.accounts.offices.Office;

import java.util.List;

public interface OfficeFacade {
	
	List<Office> listOffices(Long companyId, Long userId) throws ResourceNotFoundException, ResourceForbiddenOperationException;
}
