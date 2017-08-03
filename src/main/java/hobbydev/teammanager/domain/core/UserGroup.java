package hobbydev.teammanager.domain.core;

import hobbydev.teammanager.domain.accounts.User;

import java.util.List;

public interface UserGroup extends Group {

    List<User> getUsers();
}
