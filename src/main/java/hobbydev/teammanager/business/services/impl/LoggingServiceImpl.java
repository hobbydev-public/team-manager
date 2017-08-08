package hobbydev.teammanager.business.services.impl;

import hobbydev.teammanager.business.AbstractService;
import hobbydev.teammanager.business.services.LoggingService;
import hobbydev.teammanager.domain.core.LogEntry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoggingServiceImpl extends AbstractService implements LoggingService {
	
	@Override
	protected Class<LogEntry> getEntityClass() {
		return LogEntry.class;
	}
	
	@Override
	@Transactional
	public LogEntry addLog(LogEntry logEntry) {
		getDAO().create(logEntry);
		return logEntry;
	}
	
	@Override
	@Transactional
	public List<LogEntry> getLogs() {
		return getDAO().getAll(getEntityClass());
	}
}
