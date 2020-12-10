package sprint_2.service;

import sprint_2.model.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findAll();

    void create(Question question);

    Question findById(Long id);

    void deleteById(Long id);

    List<Question> findBySubject_IdSubjectAndQuestionContentContains(Long id , String name);

}
