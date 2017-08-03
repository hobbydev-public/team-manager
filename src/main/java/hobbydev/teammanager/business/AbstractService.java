package hobbydev.teammanager.business;

import hobbydev.teammanager.data.DefaultDAO;
import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract entity service implementation
 */
public abstract class AbstractService {
	
	@Autowired
	private DefaultDAO dao;

	protected abstract Class<? extends IdentifiedEntityInterface> getEntityClass();
	
	protected DefaultDAO getDAO() {
		return dao;
	}
	
	@Transactional
	public boolean delete(Long id) {
		return delete(id, getEntityClass());
	}
	
	@Transactional
	public boolean delete(Long id, Class<? extends IdentifiedEntityInterface> clazz) {
		if(id == null) {
			return false;
		}
		
		return getDAO().delete(clazz, id);
	}

}
