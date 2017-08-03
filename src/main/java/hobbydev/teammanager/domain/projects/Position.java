package hobbydev.teammanager.domain.projects;

import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;

public class Position implements IdentifiedEntityInterface {
	
	private Long id;
	private String name;
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Position position = (Position) o;
		
		return getId().equals(position.getId());
		
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
}
