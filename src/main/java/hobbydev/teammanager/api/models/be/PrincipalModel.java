package hobbydev.teammanager.api.models.be;

import hobbydev.teammanager.api.models.be.generic.AbstractModel;
import hobbydev.teammanager.domain.accounts.User;

public class PrincipalModel extends AbstractModel {
    
    protected String email;
    protected String firstName;
    protected String lastName;
    protected boolean authenticated;
    
    protected PrincipalModel(){}
    
    public PrincipalModel(User domain) {
        super(domain);
        
        this.email = domain.getEmail();
        this.firstName = domain.getFirstName();
        this.lastName = domain.getLastName();
        this.authenticated = true;
    }
    
    @Override
    public Long getId() {
        return id;
    }
    
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    
    public boolean isAuthenticated() {
        return authenticated;
    }
    
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
