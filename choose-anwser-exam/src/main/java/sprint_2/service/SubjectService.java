package sprint_2.service;


import sprint_2.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> findAll();

    void create(Subject subject);

    void deleteById(Long id);

    Subject findById(Long id);
}
