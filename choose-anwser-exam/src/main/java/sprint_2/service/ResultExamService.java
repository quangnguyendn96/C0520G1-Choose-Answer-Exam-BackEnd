package sprint_2.service;
import sprint_2.model.Exam;
import sprint_2.model.ResultExam;

import java.util.List;


public interface ResultExamService {
    List<?> statisticsData();

    List<ResultExam> findAll();

    void save(ResultExam resultExam);

    void deleteById(Long id);

    ResultExam findById(Long id);

    List<?> statisticsCountExamSubject();
    List<?> getStatisticsResultExamUserBySubject(String subject);
    List<ResultExam> findUserByIdPointTime(Long idUser);

}
