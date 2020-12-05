package sprint_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_2.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
