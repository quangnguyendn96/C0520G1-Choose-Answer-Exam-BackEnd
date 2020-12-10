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
    String SQL_QUERY_GET_SUM_POINT_USER = "SELECT `user`.username , SUM( result_exam.mark) AS sumPoint, COUNT(result_exam.user) AS countExam" +
            " FROM result_exam" +
            " INNER JOIN `user` ON `user`.id_user = result_exam.user" +
            " GROUP BY `user`.username" +
            " ORDER BY sumPoint DESC,countExam ASC LIMIT 5";

    /* câu truy vấn lấy tên và điểm top 5 người thi theo môn học */
    String SQL_QUERY_GET_TOP_BY_SUBJECT = " SELECT  `user`.username ,SUM( result_exam.mark) AS sumPointJava," +
            " COUNT(result_exam.user) AS countExamJava" +
            " FROM result_exam" +
            " INNER JOIN `user` ON `user`.id_user = result_exam.user" +
            " INNER JOIN Exam_quality_view ON Exam_quality_view.id_exam = result_exam.exam" +
            " where Exam_quality_view.subject_name = ?1" +
            " GROUP BY `user`.username" +
            " ORDER BY sumPointJava desc,countExamJava ASC LIMIT 5;";

    /* Câu lệnh lấy mộn thi theo quý của năm . vd: quý 1= tháng 1,2,3 */
    String SQL_QUERY_GET_SUBJECT_BY_MONTH = "select Exam_quality_view.subject_name, count(Exam_quality_view.id_exam) as CountSubject" +
            " from result_exam" +
            " inner join Exam_quality_view on Exam_quality_view.id_exam = result_exam.exam" +
            " where result_exam.taken_date like %?1 or result_exam.taken_date like %?2 or result_exam.taken_date like %?3 " +
            " group by Exam_quality_view.subject_name;";

    @Query(value = SQL_QUERY_GET_SUBJECT_BY_MONTH, nativeQuery = true)
    List<?> getCountSubjectByMonth(String string1, String string2, String string3);

    /* câu truy vấn lấy và điểm và số lần thi của từng user */
    String SQL_QUERY_GET_POINT_TIMES_USER = " select result_exam.user,sum(result_exam.mark) as `point`,count(result_exam.user) as `times` from result_exam\n" +
            "where result_exam.user = ?1\n" +
            "group by result_exam.user;";

    @Query(value = SQL_QUERY_GET_SUM_POINT_USER, nativeQuery = true)
    List<?> statisticsData();

    @Query(value = SQL_QUERY_GET_COUNT_EXAM_SUBJECT, nativeQuery = true)
    List<?> statisticsCountExamSubject();

    @Query(value = SQL_QUERY_GET_TOP_BY_SUBJECT, nativeQuery = true)
    List<?> getStatisticsResultExamUserBySubject(String subject);

    @Query(value = SQL_QUERY_GET_POINT_TIMES_USER, nativeQuery = true)
    ResultExam findUserByIdPointTime(Long idUser);

    List<ResultExam> findResultExamByUser_IdUser(Long idUser);
}
