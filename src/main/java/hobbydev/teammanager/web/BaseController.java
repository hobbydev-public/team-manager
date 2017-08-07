/**
 * This software is licensed under the terms of the MIT license.
 * Copyright (C) 2016 Dmytro Romenskyi
 */
package hobbydev.teammanager.web;
		
import hobbydev.teammanager.business.exception.ResourceNotFoundException;
import hobbydev.teammanager.business.services.UserService;
import hobbydev.teammanager.config.CurrentUser;
import hobbydev.teammanager.domain.accounts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(method=RequestMethod.GET)
public class BaseController {
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path="/*", method = RequestMethod.GET)
	public ModelAndView getGenericPage(ModelAndView mv, @CurrentUser User currentUser) throws ResourceNotFoundException {
		mv.setViewName("pages/generic");
		
		User principal = userService.getUser(currentUser.getId());
		boolean companyCreated = principal.getCompany() != null;
		
		if(companyCreated) {
			mv.addObject("company", principal.getCompany().getName());
		}
		return mv;
	}
	
	@RequestMapping(path="/docs", method = RequestMethod.GET)
	public ModelAndView getApi(ModelAndView mv) {
		mv.setViewName("swagger-ui");
		return mv;
	}
}
