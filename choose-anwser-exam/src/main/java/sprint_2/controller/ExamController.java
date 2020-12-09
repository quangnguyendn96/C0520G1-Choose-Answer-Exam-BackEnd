package sprint_2.controller;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_2.model.Exam;
import sprint_2.model.Question;
import sprint_2.model.Subject;
import sprint_2.service.ExamService;
import sprint_2.service.QuestionService;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("exam")
public class ExamController {
    @Autowired
    ExamService examService;

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Exam>> showAllExam() {
        List<Exam> list = examService.findAll();

        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/deleteExam/{id}")
    public ResponseEntity deleteExam(@PathVariable long id) {
        Exam meetingRoom = examService.findById(id);
        if (meetingRoom == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        examService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/create-exam")
    public ResponseEntity<Void> addExam(@RequestParam("examName") String examName,@RequestParam("subject") String subject) {
        Exam exam = new Exam();
        List<Question> questions = new ArrayList<>();
        List<Question> questionSubject = new ArrayList<>();
        for (int i =0; i<questionService.findAll().size(); i++){
            if(questionService.findAll().get(i).getSubject().getIdSubject().equals(Long.parseLong(subject))){
                questionSubject.add(questionService.findAll().get(i));
            }
        }
        for (int i = 0; i < 9; i++) {
            int randomIndex = (int) (Math.random() * (questionSubject.size()));
            questions.add(questionSubject.get(randomIndex));
            questionSubject.remove(randomIndex);
        }
        exam.setExamName(examName);
        exam.setQuestions(questions);
        exam.setExamDuration("10");
        examService.create(exam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findExam/{id}")
    public ResponseEntity<Exam> findExamById(@PathVariable long id) {
        Exam exam = examService.findById(id);
        if (exam == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

//    @GetMapping("/findAllSubject")
//    public ResponseEntity<List<Subject>> findAllSubject() {
//        List<Subject> subjects = examService.findAll();
//        if (subjects.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(subjects, HttpStatus.OK);
//    }
}
