package sprint_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_2.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
