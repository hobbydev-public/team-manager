package hobbydev.teammanager.api.models.be;

import hobbydev.teammanager.api.models.be.generic.AbstractModel;
import hobbydev.teammanager.domain.accounts.User;

public class UserModel extends AbstractModel {
    
    private String email = "";
    private CompanyModel company;
    
    protected UserModel(){}
    
    public UserModel(User domain) {
        super(domain);
        
        this.email = domain.getEmail();
        this.company = new CompanyModel(domain.getCompany());
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
    
    public CompanyModel getCompany() {
        return company;
    }
    
    public void setCompany(CompanyModel company) {
        this.company = company;
    }
}

class ShallowUserModel extends UserModel {
    
    ShallowUserModel(User domain) {
        setId(domain.getId());
        setEmail(domain.getEmail());
    }
    
    @Override
    public Long getId() {
        return super.getId();
    }
    
    @Override
    public void setId(Long id) {
        super.setId(id);
    }
    
    @Override
    public String getEmail() {
        return super.getEmail();
    }
    
    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }
}
