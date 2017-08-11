package hobbydev.teammanager.business.facades.impl;

import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.business.facades.ProjectFacade;
import hobbydev.teammanager.business.services.UserService;
import hobbydev.teammanager.domain.accounts.User;
import hobbydev.teammanager.domain.projects.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static hobbydev.teammanager.business.validations.UserToProjectAccessValidations.canUserDeleteProject;
import static hobbydev.teammanager.business.validations.UserToProjectAccessValidations.canUserEditProject;

@Service
public class ProjectFacadeImpl implements ProjectFacade {
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<Project> listUserCreatedProjects(Long userId) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		if(userId == null || Long.valueOf(0L).compareTo(userId) >= 0) {
			throw new ResourceForbiddenOperationException("Projects can be listed only for valid user ID.");
		}
		
		return userService.getUser(userId).getProjects();
	}
	
	@Override
	public List<Project> listUserMembershipProjects(Long userId) {
		return new ArrayList<>();
	}
	
	@Override
	public Collection<Project> listProjectsForUser(Long userId) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		Set<Project> projects = new HashSet<>();
		
		if(userId == null || Long.valueOf(0L).compareTo(userId) >= 0) {
			throw new ResourceForbiddenOperationException("Projects can be listed only for valid user ID.");
		}
		
		projects.addAll(listUserCreatedProjects(userId));
		projects.addAll(listUserMembershipProjects(userId));
		
		return projects;
	}
	
	@Override
	public Project getProject(Long projectId, Long userId) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		if(userId == null || Long.valueOf(0L).compareTo(userId) >= 0) {
			throw new ResourceForbiddenOperationException("Project can be obtained only for valid user ID.");
		}
		
		if(projectId == null || Long.valueOf(0L).compareTo(projectId) >= 0) {
			throw new ResourceNotFoundException(projectId, Project.class.getSimpleName());
		}
		
		User user = userService.getUser(userId);
		Project project = listProjectsForUser(userId).stream()
				.filter(p -> p.getId().equals(projectId))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(projectId, Project.class.getSimpleName()));
				
		
		return project;
	}
	
	@Override
	public Project addProject(Project project, Long userId) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		if(userId == null || Long.valueOf(0L).compareTo(userId) >= 0) {
			throw new ResourceForbiddenOperationException("Project can be created only by user with valid ID.");
		}
		
		if(project == null) {
			throw new IllegalArgumentException("Project is null");
		}
		
		project.setId(null);
		
		return userService.addProject(project, userId);
	}
	
	@Override
	public Project updateProject(Project project, Long userId) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		if(userId == null || Long.valueOf(0L).compareTo(userId) >= 0) {
			throw new ResourceForbiddenOperationException("Project can be updated only by user with valid ID.");
		}
		
		if(project == null) {
			throw new IllegalArgumentException("Project is null");
		}
		
		Long projectId = project.getId();
		if(projectId == null || Long.valueOf(0L).compareTo(projectId) >= 0) {
			throw new ResourceForbiddenOperationException("Project can be updated only if it has a valid ID.");
		}
		
		boolean projectForUser = listProjectsForUser(userId).stream()
				.anyMatch(p -> p.getId().equals(projectId));
		
		if(!projectForUser) {
			throw new ResourceForbiddenOperationException("User with ID=[" + userId + "] cannot edit project with ID=[" + projectId + "]");
		}
		
		User user = userService.getUser(userId);
		if(!canUserEditProject(user, project)) {
			throw new ResourceForbiddenOperationException("User with ID=[" + userId + "] cannot edit project with ID=[" + projectId + "]");
		}
		
		return userService.updateProject(project, userId);
	}
	
	@Override
	public boolean deleteProject(Long projectId, Long userId) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		if(userId == null || Long.valueOf(0L).compareTo(userId) >= 0) {
			throw new ResourceForbiddenOperationException("Project can be deleted only by user with valid ID.");
		}
		
		if(projectId == null || Long.valueOf(0L).compareTo(projectId) >= 0) {
			throw new ResourceForbiddenOperationException("Project can be deleted only if it has a valid ID.");
		}
		
		boolean projectForUser = listProjectsForUser(userId).stream()
				.anyMatch(p -> p.getId().equals(projectId));
		
		if(!projectForUser) {
			throw new ResourceForbiddenOperationException("User with ID=[" + userId + "] cannot delete project with ID=[" + projectId + "]");
		}
		
		User user = userService.getUser(userId);
		Project project = listProjectsForUser(userId).stream()
				.filter(p -> p.getId().equals(projectId))
				.findFirst()
				.orElseThrow(() -> new ResourceNotFoundException(projectId, Project.class.getSimpleName()));
		
		if(!canUserDeleteProject(user, project)) {
			throw new ResourceForbiddenOperationException("User with ID=[" + userId + "] cannot edit project with ID=[" + projectId + "]");
		}
		
		return userService.deleteProject(project, userId);
	}
}
