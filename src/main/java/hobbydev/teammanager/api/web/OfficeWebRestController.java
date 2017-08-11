package hobbydev.teammanager.api.web;

import hobbydev.teammanager.api.models.be.OfficeModel;
import hobbydev.teammanager.api.models.fe.OfficeView;
import hobbydev.teammanager.api.web.exception.HttpBadRequestException;
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
		if(auth == null || Long.valueOf(0L).compareTo(auth.getId()) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be obtained by an authenticated user only.");
		}
		
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
		if(auth == null || Long.valueOf(0L).compareTo(auth.getId()) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be obtained by an authenticated user only.");
		}
		
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
	
	@RequestMapping(path = "{officeId}", method = RequestMethod.GET)
	public ResponseEntity<OfficeModel> getOffice(@PathVariable  Long companyId,
	                                             @PathVariable Long officeId,
	                                             @CurrentUser User auth) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		
		if(auth == null || Long.valueOf(0L).compareTo(auth.getId()) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be obtained by an authenticated user only.");
		}
		
		Office domainOffice = officeFacade.getOffice(companyId, officeId, auth.getId());
		OfficeModel officeModel = new OfficeModel(domainOffice);
		
		return new ResponseEntity<OfficeModel>(officeModel, HttpStatus.OK);
	}
	
	@RequestMapping(path = "{officeId}", method = RequestMethod.PUT)
	public ResponseEntity<OfficeModel> updateOffice(@PathVariable Long companyId,
	                                                @PathVariable Long officeId,
	                                                @RequestBody OfficeView view,
	                                                @CurrentUser User auth) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		if(!officeId.equals(view.getId()) || !companyId.equals(view.getCompany().getId())) {
			throw new HttpBadRequestException("Inconsistent data provided.");
		}
		
		if(auth == null || Long.valueOf(0L).compareTo(auth.getId()) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be obtained by an authenticated user only.");
		}
		
		Office updatedOffice = view.toDomain();
		Office office = officeFacade.updateOffice(updatedOffice, auth.getId());
		OfficeModel officeModel = new OfficeModel(office);
		return new ResponseEntity<OfficeModel>(officeModel, HttpStatus.OK);
	}
	
	private List<Office> getCompanyAccountOffices(User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		if(auth == null || Long.valueOf(0L).compareTo(auth.getId()) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be obtained by an authenticated user only.");
		}
		
		Company companyAccount = userService.getUser(auth.getId()).getCompany();
		if(companyAccount == null){
			throw new ResourceNotFoundException("Current user does not have a company account created.");
		}
		
		return companyAccount.getOffices();
	}
	
	private List<Office> getOfficesByCompanyId(Long companyId, User auth) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		
		if(auth == null || Long.valueOf(0L).compareTo(auth.getId()) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be obtained by an authenticated user only.");
		}
		
		return officeFacade.listOffices(companyId, auth.getId());
	}
	
	private Office addCompanyAccountOffice(String officeName, User auth) throws ResourceNotFoundException, ResourceForbiddenOperationException {
		if(auth == null || Long.valueOf(0L).compareTo(auth.getId()) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be obtained by an authenticated user only.");
		}
		
		Company companyAccount = userService.getUser(auth.getId()).getCompany();
		if(companyAccount == null){
			throw new ResourceNotFoundException("Current user does not have a company account created.");
		}
		
		return officeFacade.addOffice(companyAccount.getId(), officeName, auth.getId());
	}
	
	private Office addOfficeToCompany(Long companyId, String officeName, User auth) throws ResourceForbiddenOperationException, ResourceNotFoundException {
		
		if(auth == null || Long.valueOf(0L).compareTo(auth.getId()) >= 0) {
			throw new ResourceForbiddenOperationException("Offices can be created by an authenticated user only.");
		}
		
		return officeFacade.addOffice(companyId, officeName, auth.getId());
	}
}
