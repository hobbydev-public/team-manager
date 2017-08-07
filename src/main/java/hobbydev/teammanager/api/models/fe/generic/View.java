package hobbydev.teammanager.api.models.fe.generic;

import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;

public interface View<ENTITY extends IdentifiedEntityInterface> {
    
    ENTITY toDomain();
}
