package sprint_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sprint_2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
//    @Query(value = "select result_exam.user,sum(result_exam.mark) as `point`,count(result_exam.user) as `time` from result_exam\n" +
//            "\tgroup by result_exam.user;,",nativeQuery = true)

}
