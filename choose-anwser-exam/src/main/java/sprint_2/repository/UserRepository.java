package sprint_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sprint_2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    Boolean existsByUsername(String username);
}
