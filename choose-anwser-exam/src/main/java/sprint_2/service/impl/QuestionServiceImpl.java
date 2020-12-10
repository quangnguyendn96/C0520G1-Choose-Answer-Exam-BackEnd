package sprint_2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sprint_2.model.Question;
import sprint_2.repository.QuestionRepository;
import sprint_2.service.QuestionService;
import java.util.List;

/**
 * service QuestionServiceImpl
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
 * 10/12/2020        Nguyễn Tiến Hải            CRUD question
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public void create(Question question) {
        questionRepository.save(question);
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }
}
