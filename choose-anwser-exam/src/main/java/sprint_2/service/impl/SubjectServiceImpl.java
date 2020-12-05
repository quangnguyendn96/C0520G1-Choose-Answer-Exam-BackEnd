package sprint_2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_2.model.Subject;
import sprint_2.repository.SubjectRepository;
import sprint_2.service.SubjectService;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public void create(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public void deleteById(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }
}
