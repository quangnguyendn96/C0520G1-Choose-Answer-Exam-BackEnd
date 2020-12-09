package sprint_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_2.model.Question;
import sprint_2.model.Subject;
import sprint_2.service.QuestionService;
import sprint_2.service.SubjectService;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Question>> showAllQuestion() {
        List<Question> list = questionService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        Question meetingRoom = questionService.findById(id);
        if (meetingRoom == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        questionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create-question")
    public ResponseEntity<Void> add(@RequestBody Question question) {
        Question question1 = new Question();
        question1.setTrueAnswer(question.getTrueAnswer());
        question1.setQuestionContent(question.getQuestionContent());
        question1.setAnswerA(question.getAnswerA());
        question1.setAnswerB(question.getAnswerB());
        question1.setAnswerC(question.getAnswerC());
        question1.setAnswerD(question.getAnswerD());
        question1.setSubject(subjectService.findById(question.getSubject().getIdSubject()));
        questionService.create(question1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Question> findQuestionById(@PathVariable long id) {
        Question question = questionService.findById(id);
        if (question == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @GetMapping("/findAllSubject")
    public ResponseEntity<List<Subject>> findAllSubject() {
        List<Subject> subjects = subjectService.findAll();
        if (subjects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

}
