package hobbydev.teammanager.api.web;

import hobbydev.teammanager.api.models.be.CompanyModel;
import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.business.services.UserService;
import hobbydev.teammanager.config.CurrentUser;
import hobbydev.teammanager.domain.accounts.Company;
import hobbydev.teammanager.domain.accounts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path="api/web/companies")
public class CompanyWebRestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<CompanyModel> addCompanyAccount(@RequestParam String name, @CurrentUser User owner) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		Company company = userService.addCompanyAccount(name, owner);
		CompanyModel companyModel = new CompanyModel(company);
		
		return new ResponseEntity<CompanyModel>(companyModel, HttpStatus.OK);
	}
}
