package hobbydev.teammanager.business.services;

import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import hobbydev.teammanager.domain.accounts.User;
        
import java.util.List;

public interface UserServiceInterface extends UserDetailsService {
    
    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;
    
    List<User> listUsers();
    User getUser(Long id)  throws ResourceNotFoundException;
    Long addUser(User user) throws ResourceForbiddenOperationException;
    void updateUser(User user) throws ResourceNotFoundException, ResourceForbiddenOperationException;
    
    String/*boolean*/ startPasswordRestore(String username) throws ResourceNotFoundException;
    boolean completePasswordRestore(String restoreKey, String newRawPassword) throws ResourceNotFoundException;
    
    /*
    
    boolean deleteUser(Long id) throws ResourceForbiddenOperationException;
    
    void changeManager(User user, User manager) throws ResourceNotFoundException, ResourceForbiddenOperationException;
    boolean manageUserSecrets(Long userId, String password, String restoreKey) throws ResourceNotFoundException;
    */
}
