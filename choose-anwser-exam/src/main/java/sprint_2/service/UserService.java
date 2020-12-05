package sprint_2.service;

import sprint_2.model.User;
import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void deleteById(Long id);
}
