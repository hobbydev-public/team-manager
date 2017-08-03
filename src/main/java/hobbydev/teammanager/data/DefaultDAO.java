package hobbydev.teammanager.data;

import hobbydev.teammanager.data.exception.DataEntityNotFoundException;
import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * Generic class for accessing application datastorage
 */
@Repository
public class DefaultDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Gets all entities of the provided class
	 * @param clazz entity class
	 *
     * @return a list of all entities of provided class or empty list if there no such entities
     */
	public <ENTITY extends IdentifiedEntityInterface> List<ENTITY> getAll(Class<ENTITY> clazz) {
		Session session = getSession();
		
		@SuppressWarnings("unchecked")
		List<ENTITY> entities = session.createCriteria(clazz).list();
		
		if(entities == null) {
			entities = Collections.emptyList();
		}
		
		return entities;
	}

	/**
	 * Creates a new entry in the datastorage with data from provided entity
	 *
	 * @param entity entity information to save
	 *
     * @return ID of the newly created entity
     */
	public <ENTITY extends IdentifiedEntityInterface> Long create(ENTITY entity) {
		if(entity == null) {
			throw new IllegalArgumentException("Invalid entity provided:[NULL]");
		}
		
		if(entity.getId() != null) {
			entity.setId(null);
		}
		
		Session session = getSession();
		
		return (Long) session.save(entity);
	}

	public <ENTITY extends IdentifiedEntityInterface> boolean delete(Class<ENTITY> clazz, Long id) {
		
		if (id == null) {
			return false;
		}
		
		Session session = getSession();
		try {
			session.delete(getById(clazz, id));
		} catch (DataEntityNotFoundException e) {
			// TODO add logging
			return false;
		}
		return true;
	}
	
	private <ENTITY extends IdentifiedEntityInterface> ENTITY getById(Class<ENTITY> clazz, Long id) throws DataEntityNotFoundException {
		if(id == null) {
			throw new IllegalArgumentException("Invalid value for entity ID provided:[NULL]");
		}
		
		ENTITY entity = getAll(clazz).stream()
				.filter(e -> e.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new DataEntityNotFoundException(id, clazz.getSimpleName()));
		
		return entity;
	}
}
