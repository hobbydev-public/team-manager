package hobbydev.teammanager.api.web;

import hobbydev.teammanager.api.models.be.AnonymousPrincipalModel;
import hobbydev.teammanager.api.models.be.PrincipalModel;
import hobbydev.teammanager.api.models.be.generic.ResponseObject;
import hobbydev.teammanager.config.CurrentUser;
import hobbydev.teammanager.domain.accounts.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/web/principal")
public class PrincipalWebRestController {
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getPrincipal(@CurrentUser User user){
		PrincipalModel principalModel = (user == null)? new AnonymousPrincipalModel(): new PrincipalModel(user);
		
		ResponseObject<PrincipalModel> principalResponse = new ResponseObject<>(principalModel);
		
		return new ResponseEntity<ResponseObject>(principalResponse, principalResponse.getStatus());
	}
}
