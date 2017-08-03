package hobbydev.teammanager.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Base implementation for access violations routing
 */
@Service
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e)
			throws IOException, ServletException {
		
		req.getRequestDispatcher("/errors/accessDenied").forward(req, resp);
	}

}
