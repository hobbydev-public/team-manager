package hobbydev.teammanager.web;
		
import hobbydev.teammanager.business.exception.ResourceForbiddenOperationException;
import hobbydev.teammanager.business.services.UserServiceInterface;
import hobbydev.teammanager.domain.accounts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
	
	@Autowired
	private UserServiceInterface userService;
	
	@RequestMapping(path="/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(ModelAndView mv, Authentication auth) {
		
		if(auth != null && auth.isAuthenticated()) {
			mv.setViewName("redirect:/");
			return mv;
		}
		mv.setViewName("pages/auth/login");
		return mv;
	}
	
	@RequestMapping(path="/register", method = RequestMethod.GET)
	public ModelAndView getRegisterPage(ModelAndView mv, Authentication auth) {
		
		if(auth != null && auth.isAuthenticated()) {
			mv.setViewName("redirect:/");
			return mv;
		}
		mv.setViewName("pages/auth/register");
		return mv;
	}
	
	@RequestMapping(path="/register", method = RequestMethod.POST)
	public ModelAndView doRegister(@RequestParam String email,
	                               @RequestParam String password,
	                               ModelAndView mv,
	                               Authentication auth,
	                               RedirectAttributes ra) {
		
		if(auth != null && auth.isAuthenticated()) {
			mv.setViewName("redirect:/");
			return mv;
		}
		
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setPassword(password);
		
		try {
			userService.addUser(newUser);
			mv.setViewName("redirect:/login");
			ra.addFlashAttribute("message", "Registration successfull. Login with new credentials.");
			ra.addFlashAttribute("email", email);
		} catch (ResourceForbiddenOperationException rfoe) {
			mv.setViewName("redirect:/register?error=");
			ra.addFlashAttribute("error", rfoe.getMessage());
		} catch (RuntimeException rte) {
			mv.setViewName("redirect:/register?error=");
			ra.addFlashAttribute("error", rte.getMessage());
		}
		
		return mv;
	}
	
	@RequestMapping(path="/restore", method = RequestMethod.GET)
	public ModelAndView getPasswordRestorePage(ModelAndView mv, Authentication auth) {
		
		if(auth != null && auth.isAuthenticated()) {
			mv.setViewName("redirect:/");
			return mv;
		}
		mv.setViewName("pages/auth/restorePassword");
		return mv;
	}
	
	@RequestMapping(path="/restore", method = RequestMethod.POST)
	public ModelAndView startRestore(@RequestParam String email,
	                                 ModelAndView mv,
	                                 RedirectAttributes ra,
	                                 Authentication auth) {
		
		if(auth != null && auth.isAuthenticated()) {
			mv.setViewName("redirect:/");
			return mv;
		}
		
		// generate random password
		// generate restore key
			
		mv.setViewName("redirect:/restore?success=");
		ra.addFlashAttribute("success", "Instructions for password restore are sent to your email.");
		
		//mv.setViewName("redirect:/restore?error=");
		//ra.addFlashAttribute("error", rnfe.getMessage());
		
		return mv;
	}
	
	@RequestMapping(path="/restore/{key}", method = RequestMethod.GET)
	public ModelAndView getCompletePasswordRestorePage(@PathVariable String key,
	                                             ModelAndView mv,
	                                             Authentication auth) {
		
		if(auth != null && auth.isAuthenticated()) {
			mv.setViewName("redirect:/");
			return mv;
		}
		
		mv.setViewName("pages/auth/doRestorePassword");
		mv.addObject("key", key);
		return mv;
	}
	
	@RequestMapping(path="/doRestore", method = RequestMethod.POST)
	public ModelAndView completeRestore(@RequestParam String key,
	                                    @RequestParam String password,
	                                    ModelAndView mv,
	                                    Authentication auth,
	                                    RedirectAttributes ra) {
		
		if(auth != null && auth.isAuthenticated()) {
			mv.setViewName("redirect:/");
			return mv;
		}
		
		
		// check that restore key is valid
		// set new pass
		// reset restore key
		
		mv.setViewName("redirect:/login");
	
		//mv.setViewName("redirect:/restore?error=");
		//ra.addFlashAttribute("error", rnfe.getMessage());
		
		return mv;
	}
}
