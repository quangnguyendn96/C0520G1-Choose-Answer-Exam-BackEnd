package sprint_2.service;
import java.util.List;


public interface ResultExamService {
    List<?> statisticsData();
    List<?> statisticsCountExamSubject();
    List<?> getStatisticsResultExamUserBySubject(String subject);
}
