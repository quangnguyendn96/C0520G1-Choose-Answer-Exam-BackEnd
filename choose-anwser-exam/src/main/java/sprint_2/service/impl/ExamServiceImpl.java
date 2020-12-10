package sprint_2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_2.model.Exam;
import sprint_2.repository.ExamRepository;
import sprint_2.service.ExamService;

import java.util.List;

/**
 * service ExamServiceImpl
 * <p>
 * Version 1.0
 * <p>
 * Date: 10/12/2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 10/12/2020        Nguyễn Tiến Hải            CRUD exam
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public void create(Exam exam) {
        examRepository.save(exam);

    }

    @Override
    public void deleteById(Long id) {
        examRepository.deleteById(id);

    }

    @Override
    public Exam findById(Long id) {
        return examRepository.findById(id).orElse(null);
    }
}
