package hobbydev.teammanager.api.web;

import hobbydev.teammanager.api.models.be.ProjectModel;
import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.business.facades.ProjectFacade;
import hobbydev.teammanager.config.CurrentUser;
import hobbydev.teammanager.domain.accounts.User;
import hobbydev.teammanager.domain.projects.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path="api/web/projects")
public class ProjectWebRestController {
	
	@Autowired
	private ProjectFacade projectFacade;
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<List<ProjectModel>> getProjectsForUser(@CurrentUser User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		if(auth == null || Long.valueOf(0L).compareTo(auth.getId()) >= 0) {
			throw new ResourceForbiddenOperationException("Projects can be obtained by an authenticated user only.");
		}
		
		Collection<Project> domainProjects = projectFacade.listProjectsForUser(auth.getId());
		
		List<ProjectModel> projectModels = domainProjects.stream()
				.map(project -> new ProjectModel(project))
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<ProjectModel>>(projectModels, HttpStatus.OK);
	}
}
