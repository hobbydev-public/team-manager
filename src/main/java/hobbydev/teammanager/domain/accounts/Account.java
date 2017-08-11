package hobbydev.teammanager.domain.accounts;

import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;
import hobbydev.teammanager.domain.projects.Project;

import java.util.List;

public abstract class Account implements IdentifiedEntityInterface {
	
	public abstract String getName();
	public abstract void setName(String name);
	
	/*public abstract List<Client> getClients();
	public abstract void setClients(List<Client> clients);*/
	
	public abstract List<Project> getProjects();
	public abstract void setProjects(List<Project> projects);
	
}
