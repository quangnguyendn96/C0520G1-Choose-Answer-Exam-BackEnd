package sprint_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sprint_2.model.ResultExam;

import java.util.List;

public interface ResultExamRepository extends JpaRepository<ResultExam, Long> {

    /*Tạo view lấy số lượng đề và môn thi*/
    String SQL_QUERY_CREATE_VIEW = " CREATE OR REPLACE VIEW Exam_quality_view AS" +
            " SELECT exam.id_exam, exam.exam_name, `subject`.subject_name" +
            " FROM question" +
            " INNER JOIN `subject` ON `subject`.id_subject = question.id_subject" +
            " INNER JOIN exam_question ON exam_question.id_question = question.id_question" +
            " INNER JOIN exam ON exam.id_exam = exam_question.id_exam" +
            " GROUP BY exam.id_exam;" +
            " SELECT * FROM Exam_quality_view;";

    /* Câu truy vấn lấy tổng số lần các môn học được người thi chọn để thi */
    String SQL_QUERY_GET_COUNT_EXAM_SUBJECT = " SELECT Exam_quality_view.subject_name, COUNT(Exam_quality_view.id_exam) AS CountSubject" +
            " FROM result_exam" +
            " INNER JOIN Exam_quality_view ON Exam_quality_view.id_exam = result_exam.exam" +
            " INNER JOIN `user` ON `user`.id_user = result_exam.`user`" +
            " GROUP BY Exam_quality_view.subject_name;";

    /* Câu truy vấn lấy tên , tổng số điểm và tổng số lần thi của người thi */
    String SQL_QUERY_GET_SUM_POINT_USER = "SELECT `user`.user_name , SUM( result_exam.mark) AS sumPoint, COUNT(result_exam.user) AS countExam" +
            " FROM result_exam" +
            " INNER JOIN `user` ON `user`.id_user = result_exam.user" +
            " GROUP BY `user`.user_name" +
            " ORDER BY sumPoint DESC,countExam ASC LIMIT 5";

    /* câu truy vấn lấy tên và điểm top 5 người thi theo môn học */
    String SQL_QUERY_GET_TOP_BY_SUBJECT = " SELECT  `user`.user_name ,SUM( result_exam.mark) AS sumPointJava," +
            " COUNT(result_exam.user) AS countExamJava" +
            " FROM result_exam" +
            " INNER JOIN `user` ON `user`.id_user = result_exam.user" +
            " INNER JOIN Exam_quality_view ON Exam_quality_view.id_exam = result_exam.exam" +
            " where Exam_quality_view.subject_name = ?1" +
            " GROUP BY `user`.user_name" +
            " ORDER BY sumPointJava desc,countExamJava ASC LIMIT 5;";

    @Query(value = SQL_QUERY_GET_SUM_POINT_USER, nativeQuery = true)
    List<?> statisticsData();

    @Query(value = SQL_QUERY_GET_COUNT_EXAM_SUBJECT, nativeQuery = true)
    List<?> statisticsCountExamSubject();

    @Query(value = SQL_QUERY_GET_TOP_BY_SUBJECT, nativeQuery = true)
    List<?> getStatisticsResultExamUserBySubject(String subject);
}
