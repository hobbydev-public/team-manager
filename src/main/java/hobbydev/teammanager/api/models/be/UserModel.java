package hobbydev.teammanager.api.models.be;

import hobbydev.teammanager.api.models.be.generic.AbstractModel;
import hobbydev.teammanager.domain.accounts.User;

public class UserModel extends AbstractModel {
    
    private String email = "";
    
    protected UserModel(){}
    
    public UserModel(User domain) {
        super(domain);
        
        this.email = domain.getEmail();
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
}
