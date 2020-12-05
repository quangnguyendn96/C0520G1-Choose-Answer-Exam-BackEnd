package sprint_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
