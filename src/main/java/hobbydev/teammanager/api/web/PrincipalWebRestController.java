package hobbydev.teammanager.api.web;

import hobbydev.teammanager.api.models.be.AnonymousPrincipalModel;
import hobbydev.teammanager.api.models.be.PrincipalModel;
import hobbydev.teammanager.config.CurrentUser;
import hobbydev.teammanager.domain.accounts.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/web/principal")
public class PrincipalWebRestController {
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<PrincipalModel> getPrincipal(@CurrentUser User user){
		PrincipalModel principalModel = (user == null)? new AnonymousPrincipalModel(): new PrincipalModel(user);
		
		return new ResponseEntity<PrincipalModel>(principalModel, HttpStatus.OK);
	}
}
