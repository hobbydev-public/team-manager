package hobbydev.teammanager.business.services.impl;

import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.business.services.UserService;
import hobbydev.teammanager.domain.accounts.Company;
import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hobbydev.teammanager.business.AbstractService;
import hobbydev.teammanager.domain.accounts.User;

import java.util.List;

/**
 * User Service
 */
@Service
public class UserServiceImpl extends AbstractService implements UserService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
    
    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User foundUser = listUsers().stream()
                    .filter(user -> user.getUsername().equals(username))
                    .findFirst()
                    .orElseThrow(() -> new UsernameNotFoundException("User with provided username was not found - " + username));
            return foundUser;
        } catch (UsernameNotFoundException unfe) {
            throw unfe;
        } catch (Throwable t) {
            throw new RuntimeException("Authentication service failure. Please contact the administrator", t);
        }
    }
    
    @Override
    @Transactional
    public List<User> listUsers() {
        return getDAO().getAll(getEntityClass());
    }
    
    @Override
    @Transactional
    public User getUser(Long id) throws ResourceNotFoundException {
        return listUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(id, getEntityClass().getSimpleName()));
    }
    
    @Override
    @Transactional
    public Long addUser(User user) throws ResourceForbiddenOperationException {
        if(user == null) {
            throw new IllegalArgumentException("User is null");
        }
        
        user.setId(null);
        
        boolean emailDuplicate = listUsers().stream()
                .anyMatch(dbUser -> dbUser.getEmail().equalsIgnoreCase(user.getEmail()));
        if(emailDuplicate) {
            throw new ResourceForbiddenOperationException("User with the " + user.getEmail() + " email address is already registered");
        }
        
        user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
        return getDAO().create(user);
    }
    
    @Override
    @Transactional
    public User updateUser(User user) throws ResourceNotFoundException, ResourceForbiddenOperationException {
        if(user == null) {
            throw new IllegalArgumentException("User is null");
        }
        
        if(user.getId() == null || Long.valueOf(0).compareTo(user.getId()) >= 0) {
            throw new ResourceNotFoundException("User ID does not exist. Only user with valid ID can be updated.");
        }
        
        // in the event of changing an email we should check that there are no other users with the same email but different ID
        boolean anotherWithSameEmail = listUsers().stream()
                .anyMatch(dbUser -> dbUser.getEmail().equalsIgnoreCase(user.getEmail()) && !dbUser.getId().equals(user.getId()));
        if(anotherWithSameEmail) {
            throw new ResourceForbiddenOperationException("User with the " + user.getEmail() + " email address is already registered");
        }
        
        User persistant = getUser(user.getId());
        persistant.setEmail(user.getEmail());
        persistant.setFirstName(user.getFirstName());
        persistant.setLastName(user.getLastName());
        
        return persistant;
    }
    
    @Override
    @Transactional
    public String startPasswordRestore(String username) throws ResourceNotFoundException {
        User persistant = null;
        try {
            persistant = loadUserByUsername(username);
        } catch (UsernameNotFoundException unfe) {
            throw new ResourceNotFoundException("User [" + username + "] was not found.", unfe);
        }
    
        // generate random password
        // generate restore key
    
        String key = KeyGenerators.string().generateKey();
        String randomPassword = KeyGenerators.string().generateKey();
        
        persistant.setPassword(passwordEncoder.encodePassword(randomPassword, null));
        persistant.setRestoreKey(passwordEncoder.encodePassword(key, null));
        
        //mailService.sendPasswordRestoreInstructions(user, key);
        
        return key;
    }
    
    @Override
    @Transactional
    public boolean completePasswordRestore(String restoreKey, String newRawPassword) throws ResourceNotFoundException {
        // check that restore key is valid
        // set new pass
        // reset restore key
        
        User persistant = listUsers().stream()
                .filter(user -> passwordEncoder.isPasswordValid(user.getRestoreKey(), restoreKey, null))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Security key is not valid"));
        
        persistant.setPassword(passwordEncoder.encodePassword(newRawPassword, null));
        persistant.setRestoreKey(null);
        
        return true;
    }
    
    @Override
    @Transactional
    public Company addCompanyAccount(String name, User owner) throws ResourceNotFoundException, ResourceForbiddenOperationException {
        if(owner == null) {
            throw new IllegalArgumentException("Owner is null");
        }
    
        if(owner.getId() == null || Long.valueOf(0).compareTo(owner.getId()) >= 0) {
            throw new ResourceNotFoundException("Owner ID does not exist. Company account can only be created with valid owner ID.");
        }
        
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Company name is not provided.");
        }
        
        User persistant = getUser(owner.getId());
        
        if(persistant.getCompany() != null) {
            throw new ResourceForbiddenOperationException("User can create only single company account.");
        }
        
        Company company = new Company();
        company.setName(name);
        
        persistant.setCompany(company);
        return company;
    }
}
