package hobbydev.teammanager.api.web;

import hobbydev.teammanager.api.models.be.UserModel;
import hobbydev.teammanager.api.models.be.generic.ResponseObject;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.business.services.UserService;
import hobbydev.teammanager.domain.accounts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path="api/web/users")
public class UserWebRestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "{userId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getUserById(@PathVariable Long userId) throws ResourceNotFoundException {
		User user = userService.getUser(userId);
		UserModel userModel = new UserModel(user);
		
		ResponseObject<UserModel> userResponse = new ResponseObject<>(userModel);
		
		return new ResponseEntity<ResponseObject>(userResponse, userResponse.getStatus());
	}
}
