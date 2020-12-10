package sprint_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_2.model.Question;
import sprint_2.model.Subject;
import sprint_2.service.ExamService;
import sprint_2.service.QuestionService;
import sprint_2.service.SubjectService;

import java.util.ArrayList;
import java.util.List;

/**
 * controller QuestionController
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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamService examService;

    @Autowired
    private SubjectService subjectService;

    /**
     * Show all question
     *
     * @return list <Question>
     */
    @GetMapping
    public ResponseEntity<List<Question>> showAllQuestion() {
        List<Question> list = questionService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Show all question by name and subject
     *
     * @param valueName, subject
     * @return list <Question>
     */
    @GetMapping("/allQuestionByNameAndSubject")
    public ResponseEntity<List<Question>> getAllQuestionByNameAndSubject(@RequestParam("valueName") String valueName, @RequestParam("subject") String subject) {
        List<Question> listQuestion = questionService.findBySubject_IdSubjectAndQuestionContentContains(Long.parseLong(subject), valueName);
        if (listQuestion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listQuestion, HttpStatus.OK);
    }

    /**
     * delete question
     *
     * @param id
     * @return void
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteQuestion(@PathVariable long id) {
        boolean check = false;
        int length = examService.findAll().size();
        List<Question> questionList;
        for (int i = 0; i< length; i++){
            questionList = new ArrayList<Question>(examService.findAll().get(i).getQuestions());
            for (int k = 0; k < questionList.size(); k++) {
                if (questionList.get(k).getIdQuestion().equals(id)){
                    check= true;
                    break;
                }
            }
        }
        if (!check) {
            Question question = questionService.findById(id);
            if (question == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            questionService.deleteById(id);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.OK);
    }

    /**
     * Add new question
     *
     * @param question
     * @return void
     */
    @PostMapping("/create-question")
    public ResponseEntity<Void> addQuestion(@RequestBody Question question) {
        if(subjectService.findById(question.getSubject().getIdSubject()) == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        question.setSubject(subjectService.findById(question.getSubject().getIdSubject()));
        questionService.create(question);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Add new question
     *
     * @param question
     * @return void
     */
    @PutMapping("/update-question")
    public ResponseEntity<Void> editQuestion(@RequestBody Question question) {
        Question question1  = new Question();
        question1.setAnswerD(question.getAnswerD());
        question1.setAnswerC(question.getAnswerC());
        question1.setAnswerB(question.getAnswerB());
        question1.setAnswerA(question.getAnswerA());
        question1.setQuestionContent(question.getQuestionContent());
        if(subjectService.findById(question.getSubject().getIdSubject()) == null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        question1.setSubject(subjectService.findById(question.getSubject().getIdSubject()));
        question1.setIdQuestion(question.getIdQuestion());
        questionService.create(question);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * find question by id
     *
     * @param id
     * @return Question
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<Question> findQuestionById(@PathVariable long id) {
        Question question = questionService.findById(id);
        if (question == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    /**
     * find all subject
     *
     * @return List <Subject>
     */
    @GetMapping("/findAllSubject")
    public ResponseEntity<List<Subject>> findAllSubject() {
        List<Subject> subjects = subjectService.findAll();
        if (subjects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

}
