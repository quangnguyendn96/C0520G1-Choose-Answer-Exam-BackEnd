package sprint_2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_2.model.ResultExam;
import sprint_2.repository.ResultExamRepository;
import sprint_2.service.ResultExamService;

import java.util.List;

@Service
public class ResultExamServiceImpl implements ResultExamService {
    @Autowired
    private ResultExamRepository resultExamRepository;

    @Override
    public List<?> statisticsData() {
        return resultExamRepository.statisticsData();
    }

    @Override

    public List<ResultExam> findAll() {
        return this.resultExamRepository.findAll();
    }

    @Override
    public void save(ResultExam resultExam) {
        this.resultExamRepository.save(resultExam);
    }

    @Override
    public void deleteById(Long id) {
        this.resultExamRepository.deleteById(id);
    }

    @Override
    public ResultExam findById(Long id) {
        return this.resultExamRepository.findById(id).orElse(null);
    }

    public List<?> statisticsCountExamSubject() {
        return resultExamRepository.statisticsCountExamSubject();
    }

    @Override
    public List<?> getStatisticsResultExamUserBySubject(String subject) {
        return resultExamRepository.getStatisticsResultExamUserBySubject(subject);
    }


}
