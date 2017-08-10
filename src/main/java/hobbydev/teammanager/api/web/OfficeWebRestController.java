package hobbydev.teammanager.api.web;

import hobbydev.teammanager.api.models.be.OfficeModel;
import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.business.facades.OfficeFacade;
import hobbydev.teammanager.business.services.UserService;
import hobbydev.teammanager.config.CurrentUser;
import hobbydev.teammanager.domain.accounts.Company;
import hobbydev.teammanager.domain.accounts.User;
import hobbydev.teammanager.domain.accounts.offices.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path="api/web/companies/{companyId}/offices")
public class OfficeWebRestController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private OfficeFacade officeFacade;
	
	private static final String ACCOUNT_COMPANY_ID = "account";
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<OfficeModel> addOffice(@PathVariable String companyId,
	                                             @RequestParam String name,
	                                             @CurrentUser User auth) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		Office domainOffice = null;
		
		if(ACCOUNT_COMPANY_ID.equalsIgnoreCase(companyId)) {
			domainOffice = addCompanyAccountOffice(name, auth);
		} else {
			domainOffice = addOfficeToCompany(Long.valueOf(companyId), name, auth);
		}
		
		return new ResponseEntity<OfficeModel>(new OfficeModel(domainOffice), HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<List<OfficeModel>> getCompanyOffices(@PathVariable String companyId, @CurrentUser User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		List<Office> domainOffices = new ArrayList<>();
		if(ACCOUNT_COMPANY_ID.equalsIgnoreCase(companyId)) {
			domainOffices = getCompanyAccountOffices(auth);
		} else {
			domainOffices = getOfficesByCompanyId(Long.valueOf(companyId), auth);
		}
		
		List<OfficeModel> officeModels = domainOffices.stream()
				.map(office -> new OfficeModel(office))
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<OfficeModel>>(officeModels, HttpStatus.OK);
	}
	
	private List<Office> getCompanyAccountOffices(User auth) throws ResourceNotFoundException {
		Company companyAccount = userService.getUser(auth.getId()).getCompany();
		if(companyAccount == null){
			throw new ResourceNotFoundException("Current user does not have a company account created.");
		}
		
		return companyAccount.getOffices();
	}
	
	private List<Office> getOfficesByCompanyId(Long companyId, User auth) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		if(companyId == null || Long.valueOf(0L).compareTo(companyId) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be obtained for only a company with valid ID");
		}
		
		if(auth == null || Long.valueOf(0L).compareTo(auth.getId()) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be obtained by an authenticated user only.");
		}
		
		return officeFacade.listOffices(companyId, auth.getId());
	}
	
	private Office addCompanyAccountOffice(String officeName, User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		Company companyAccount = userService.getUser(auth.getId()).getCompany();
		if(companyAccount == null){
			throw new ResourceNotFoundException("Current user does not have a company account created.");
		}
		
		return officeFacade.addOffice(companyAccount.getId(), officeName, auth.getId());
	}
	
	private Office addOfficeToCompany(Long companyId, String officeName, User auth) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		if(companyId == null || Long.valueOf(0L).compareTo(companyId) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be created for only a company with valid ID");
		}
		
		if(auth == null || Long.valueOf(0L).compareTo(auth.getId()) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be created by an authenticated user only.");
		}
		
		if(officeName == null || officeName.trim().isEmpty()) {
			throw new ResourceForbiddenOperationException("Offices without names cannot be created.");
		}
		
		return officeFacade.addOffice(companyId, officeName, auth.getId());
	}
}
