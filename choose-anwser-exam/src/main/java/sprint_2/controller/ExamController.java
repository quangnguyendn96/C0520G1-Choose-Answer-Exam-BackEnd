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
    @DeleteMapping("/deleteExam")
    public ResponseEntity<Void> deleteExam(@RequestParam("idExams") String idExams) {
        String[] idExamList = idExams.split("-#-");
        Exam exam = null;
        for (int i = 0; i < idExamList.length; i++){
            exam = examService.findById(Long.parseLong(idExamList[i]));
            if (exam == null) {
                return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            examService.deleteById(Long.parseLong(idExamList[i]));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/deleteQuestionInExam")
    public ResponseEntity<Void> deleteQuestionInExam(@RequestParam("idQuestions") String idQuestions) {
        String[] idQuestionList = idQuestions.split("-#-");
        Exam exam = examService.findById(Long.parseLong(idQuestionList[0]));
        Set<Question> questions = exam.getQuestions();
        for (int i = 1; i < idQuestionList.length; i++){
            questions.remove(questionService.findById(Long.parseLong(idQuestionList[i])));
        }
        exam.setQuestions(questions);
        examService.create(exam);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/addQuestionInExam")
    public ResponseEntity<Void> addQuestionInExam(@RequestParam("idQuestions") String idQuestions) {
        String[] idQuestionList = idQuestions.split("-#-");
        Exam exam = examService.findById(Long.parseLong(idQuestionList[0]));
        Set<Question> questions = exam.getQuestions();
        for (int i = 1; i < idQuestionList.length; i++){
            questions.add(questionService.findById(Long.parseLong(idQuestionList[i])));
        }
        exam.setQuestions(questions);
        examService.create(exam);
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
        for (int i = 0; i < 10; i++) {
            int randomIndex = (int) (Math.random() * (questionSubject.size()));
            questions.add(questionSubject.get(randomIndex));
            questionSubject.remove(randomIndex);
        }
        exam.setExamName(examName);
        Set<Question> questionSet = new HashSet<Question>(questions);
        exam.setQuestions(questionSet);
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
    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> findAllSubject(@RequestParam("idExam") String idExam) {
        List<Question> questions = questionService.findAll();
        Exam exam = examService.findById(Long.parseLong(idExam));
        List<Question> questionList = new ArrayList<Question>(exam.getQuestions());
        for (int i = 0 ; i < exam.getQuestions().size(); i++){
            questions.remove(questionList.get(i));
        }
        if (questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}
