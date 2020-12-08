package sprint_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sprint_2.model.Question;


public interface UploadFileRepository extends JpaRepository<Question, Long> {

}
