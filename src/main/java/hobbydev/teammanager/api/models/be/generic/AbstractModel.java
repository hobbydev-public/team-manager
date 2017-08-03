package hobbydev.teammanager.api.models.be.generic;

import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;

public abstract class AbstractModel {
	
	protected Long id;
	
	protected AbstractModel(){}
	
	public AbstractModel(IdentifiedEntityInterface domain) {
		this.id = domain.getId();
	}
	
	public abstract Long getId();
	public abstract void setId(Long id);
}
