package hobbydev.teammanager.business.services;

import hobbydev.teammanager.domain.core.LogEntry;

import java.util.List;

public interface LoggingService {
	
	LogEntry addLog(LogEntry logEntry);
	List<LogEntry> getLogs();
}
