package hobbydev.teammanager.api.web;

import hobbydev.teammanager.api.models.be.UserModel;
import hobbydev.teammanager.api.models.fe.PasswordsView;
import hobbydev.teammanager.api.models.fe.UserView;
import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.business.services.UserService;
import hobbydev.teammanager.config.CurrentUser;
import hobbydev.teammanager.domain.accounts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path="api/web/users")
public class UserWebRestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "{userId}", method = RequestMethod.GET)
	public ResponseEntity<UserModel> getUserById(@PathVariable Long userId) throws ResourceNotFoundException {
		User user = userService.getUser(userId);
		UserModel userModel = new UserModel(user);
		
		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
	}
	
	@RequestMapping(path = "{userId}", method = RequestMethod.PUT)
	public ResponseEntity<UserModel> updateUser(@PathVariable Long userId,
	                                            @RequestBody UserView view,
	                                            @CurrentUser User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		
		if(userId.equals(view.getId()) && auth.getId().equals(view.getId())) {
			User domain = view.toDomain();
			UserModel userModel = new UserModel(userService.updateUser(domain));
			return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
		} else {
			throw new ResourceForbiddenOperationException("User can update only his own data.");
		}
	}
	
	@RequestMapping(path = "password", method = RequestMethod.PUT)
	public ResponseEntity<UserModel> changePassword(@RequestBody PasswordsView passwords, @CurrentUser User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		User user = userService.changePassword(auth.getId(), passwords.getOldPass(), passwords.getNewPass());
		UserModel userModel = new UserModel(user);
		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
	}
}
