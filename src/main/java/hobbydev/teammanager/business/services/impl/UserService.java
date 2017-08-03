package hobbydev.teammanager.business.services.impl;

import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.business.services.UserServiceInterface;
import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hobbydev.teammanager.business.AbstractService;
import hobbydev.teammanager.domain.accounts.User;

import java.util.List;

/**
 * User Service
 */
@Service
public class UserService extends AbstractService implements UserServiceInterface {
    
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
    public List<User> listUsers() {
        return getDAO().getAll(getEntityClass());
    }
    
    @Override
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
    public void updateUser(User user) throws ResourceNotFoundException, ResourceForbiddenOperationException {
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
        /*persistant.setFirstName(user.getFirstName());
        persistant.setLastName(user.getLastName());*/
    }
}
