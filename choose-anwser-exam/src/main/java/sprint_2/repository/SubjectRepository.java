package sprint_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_2.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
