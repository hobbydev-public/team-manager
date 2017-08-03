package hobbydev.teammanager.domain.projects;

import hobbydev.teammanager.domain.accounts.User;

public class ProjectMember {
	
	private Division division;
	private Project project;
	private User user;
	private ProjectRole projectRole;
	
	public Division getDivision() {
		return division;
	}
	
	public void setDivision(Division division) {
		this.division = division;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public ProjectRole getProjectRole() {
		return projectRole;
	}
	
	public void setProjectRole(ProjectRole projectRole) {
		this.projectRole = projectRole;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		ProjectMember that = (ProjectMember) o;
		
		if (!getProject().equals(that.getProject())) return false;
		return getUser().equals(that.getUser());
		
	}
	
	@Override
	public int hashCode() {
		int result = getProject().hashCode();
		result = 31 * result + getUser().hashCode();
		return result;
	}
}
