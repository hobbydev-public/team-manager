package hobbydev.teammanager.api.models.be;

import hobbydev.teammanager.api.models.be.generic.AbstractModel;
import hobbydev.teammanager.domain.accounts.Company;

public class CompanyModel extends AbstractModel {
    
    private String name = "";
    private UserModel owner;
    
    protected CompanyModel(){}
    
    public CompanyModel(Company domain) {
        super(domain);
        
        this.name = domain.getName();
        this.owner = new ShallowUserModel(domain.getOwner());
    }
    
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
    
    public UserModel getOwner() {
        return owner;
    }
    
    public void setOwner(UserModel owner) {
        this.owner = owner;
    }
}
