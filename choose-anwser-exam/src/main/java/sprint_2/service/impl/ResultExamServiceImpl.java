package sprint_2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
