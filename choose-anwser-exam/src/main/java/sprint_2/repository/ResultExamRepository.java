package sprint_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sprint_2.model.ResultExam;

import java.util.List;

public interface ResultExamRepository extends JpaRepository<ResultExam, Long> {
    String SQL_QUERY_CREATE_VIEW = " CREATE OR REPLACE VIEW Exam_quality_view AS" +
            " SELECT exam.id_exam, exam.exam_name, `subject`.subject_name" +
            " FROM question" +
            " INNER JOIN `subject` ON `subject`.id_subject = question.id_subject" +
            " INNER JOIN exam_question ON exam_question.id_question = question.id_question" +
            " INNER JOIN exam ON exam.id_exam = exam_question.id_exam" +
            " GROUP BY exam.id_exam;" +
            " SELECT * FROM Exam_quality_view;";
    String SQL_QUERY_SUM_POINT = "SELECT `user`.user_name , SUM( result_exam.mark) AS sumPoint,COUNT(result_exam.user) AS countExam" +
            " FROM result_exam" +
            " INNER JOIN `user` ON `user`.id_user = result_exam.user" +
            " GROUP BY `user`.user_name" +
            " ORDER BY sumPoint DESC,countExam ASC LIMIT 5";

    @Query(value = SQL_QUERY_SUM_POINT, nativeQuery = true)
    List<?> statisticsData();

}
