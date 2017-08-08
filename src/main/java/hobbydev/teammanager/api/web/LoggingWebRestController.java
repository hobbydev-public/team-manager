package hobbydev.teammanager.api.web;

import hobbydev.teammanager.api.models.be.LogEntryModel;
import hobbydev.teammanager.api.models.fe.LogEntryView;
import hobbydev.teammanager.business.services.LoggingService;
import hobbydev.teammanager.config.CurrentUser;
import hobbydev.teammanager.domain.accounts.User;
import hobbydev.teammanager.domain.core.LogEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path="api/web/logs")
public class LoggingWebRestController {
	
	@Autowired
	private LoggingService loggingService;
	
	@RequestMapping(path = "{level}", method = RequestMethod.POST)
	public ResponseEntity<LogEntryModel> addLog(@RequestBody LogEntryView view, @CurrentUser User auth) {
		LogEntry entry = view.toDomain();
		entry.setSessionUser(auth);
		LogEntryModel logEntryModel = new LogEntryModel(loggingService.addLog(entry));
		
		return new ResponseEntity<LogEntryModel>(logEntryModel, HttpStatus.CREATED);
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public ResponseEntity<List<LogEntryModel>> getLogs() {
		List<LogEntryModel> models = loggingService.getLogs().stream()
				.map(domain -> new LogEntryModel(domain))
				.collect(Collectors.toList());
		
		return new ResponseEntity<List<LogEntryModel>>(models, HttpStatus.OK);
	}
}
