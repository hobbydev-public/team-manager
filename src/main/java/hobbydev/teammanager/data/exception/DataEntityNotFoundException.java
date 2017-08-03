package hobbydev.teammanager.data.exception;

import java.io.Serializable;

public class DataEntityNotFoundException extends Exception {
	public DataEntityNotFoundException(Serializable id, String entityName) {
		super(entityName + " data entity with ID=[" + id + "] cannot be found in data storage.");
	}
}
