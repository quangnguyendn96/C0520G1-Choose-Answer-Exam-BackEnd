package sprint_2.service;

import sprint_2.model.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> findAll();

    void create(Exam exam);

    void deleteById(Long id);

    Exam findById(Long id);

    List<Exam> findByExamNameContains(String name);
}
