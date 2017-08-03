package hobbydev.teammanager.api.models.fe.generic;

import hobbydev.teammanager.domain.core.IdentifiedEntityInterface;

interface View<ENTITY extends IdentifiedEntityInterface> {
    
    ENTITY toDomain();
}
