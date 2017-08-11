package hobbydev.teammanager.business.facades;

import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.domain.projects.Project;

import java.util.Collection;
import java.util.List;

public interface ProjectFacade {
	
	Collection<Project> listProjectsForUser(Long userId) throws ResourceForbiddenOperationException, ResourceNotFoundException;
	List<Project> listUserCreatedProjects(Long userId) throws ResourceForbiddenOperationException, ResourceNotFoundException;
	List<Project> listUserMembershipProjects(Long userId);
	
	Project getProject(Long projectId, Long userId) throws ResourceForbiddenOperationException, ResourceNotFoundException;
	
	Project addProject(Project project, Long userId) throws ResourceForbiddenOperationException, ResourceNotFoundException;
	Project updateProject(Project project, Long userId) throws ResourceForbiddenOperationException, ResourceNotFoundException;
	boolean deleteProject(Long projectId, Long userId) throws ResourceForbiddenOperationException, ResourceNotFoundException;
	
}
