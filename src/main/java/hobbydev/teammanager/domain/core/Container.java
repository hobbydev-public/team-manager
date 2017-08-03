package hobbydev.teammanager.domain.core;

import java.util.List;

public interface Container {

    List<? extends IdentifiedEntityInterface> getElements();
}
