package hobbydev.teammanager.api.web;

import hobbydev.teammanager.api.models.be.CompanyModel;
import hobbydev.teammanager.api.models.be.generic.SuccessModel;
import hobbydev.teammanager.api.models.fe.CompanyView;
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
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path="api/web/companies")
public class CompanyWebRestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = "account", method = RequestMethod.PUT)
	public ResponseEntity<CompanyModel> updateCompanyAccount(@RequestBody CompanyView view,
	                                                         @CurrentUser User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		Company domain = userService.updateCompanyAccount(view.toDomain(), auth);
		CompanyModel companyModel = new CompanyModel(domain);
		
		return new ResponseEntity<CompanyModel>(companyModel, HttpStatus.OK);
	}
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<CompanyModel> addCompanyAccount(@RequestParam String name, @CurrentUser User owner) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		Company company = userService.addCompanyAccount(name, owner);
		CompanyModel companyModel = new CompanyModel(company);
		
		return new ResponseEntity<CompanyModel>(companyModel, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<SuccessModel> deleteCompanyAccount(@PathVariable Long id, @CurrentUser User owner) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		Company ownerCompany = userService.getUser(owner.getId()).getCompany();
		if(ownerCompany == null || !ownerCompany.getId().equals(id)) {
			throw new ResourceForbiddenOperationException("User can delete only owned company accounts.");
		}
		
		boolean deleted = userService.deleteCompanyAccount(owner);
		SuccessModel successModel = new SuccessModel();
		successModel.setMessage(deleted? "Deleted": "No content");
		
		return new ResponseEntity<SuccessModel>(successModel, deleted? HttpStatus.OK : HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(path = "account", method = RequestMethod.GET)
	public ResponseEntity<CompanyModel> getCompanyAccount(@CurrentUser User owner) throws ResourceNotFoundException {
		Company company = userService.getUser(owner.getId()).getCompany();
		if(company == null) {
			throw new ResourceNotFoundException("Current user does not have a company account created.");
		}
		
		CompanyModel companyModel = new CompanyModel(company);
		return new ResponseEntity<CompanyModel>(companyModel, HttpStatus.OK);
	}
}
