package sprint_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_2.model.*;
import sprint_2.service.ExamService;
import sprint_2.service.ResultExamService;
import sprint_2.service.UserService;

import java.util.*;
/**
 * controller QuestionController
 *
 * Version 1.0
 *
 * Date: 10/12/2020
 *
 * Copyright
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * ------------------------------------------------------------------------
 * 13/12/2020        Chuong HKV          Taking exams and result of the exam
 */
@RestController
@RequestMapping("/resultExam")
@CrossOrigin
public class ResultExamController {
    @Autowired
    private UserService userService;

    @Autowired
    private ExamService examService;

    @Autowired
    private ResultExamService resultExamService;

    /**
     * show exam-result list
     *
     * @return list
     */
    @GetMapping("/list")
    public ResponseEntity<List<ResultExam>> getListResultExam() {
        List<ResultExam> list = resultExamService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * show exam list by subject
     *
     * @param @subject
     * @return list
     */
    @GetMapping("/list-exam-by-subject/{subject}")
    public ResponseEntity<List<Exam>> getListExamBySubject(@PathVariable String subject) {
        List<Exam> examList = examService.findAll();
        List<Exam> examListBySubject = new ArrayList<>();
        if(examList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Question question = null;
        Set<Question> questions = null;
        for(Exam exam : examList){
            questions = exam.getQuestions();
            if(questions.isEmpty()){
                continue;
            }
            for (Iterator<Question> iterator = questions.iterator(); iterator.hasNext();){
                question = iterator.next();
                break;
            }
            System.err.println(question.toString());
            if(question.getSubject().getSubjectName().toLowerCase().contains(subject.toLowerCase())){
                examListBySubject.add(exam);
            }
        }

        if(examListBySubject.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        System.err.println(examListBySubject);
        return new ResponseEntity<>(examListBySubject, HttpStatus.OK);
    }

    /**
     * get exam by Id
     *
     * @param @id
     * @return exam
     */
    @GetMapping("/exam-by-id/{id}")
    public ResponseEntity<Exam> findExamById(@PathVariable long id) {
        Exam exam = examService.findById(id);
        if(exam == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    /**
     * save result of exam
     *
     * @param
     * @return void
     */
    @PostMapping(value = "/create")
    public  ResponseEntity<Void> createResultExam(@RequestBody ResultExamDTO result) {
        if(result != null) {
            System.err.println("DTO input"+ result.toString());
            ResultExam resultExam = new ResultExam();
            resultExam.setExam(this.examService.findById(result.getExamId()));
            resultExam.setUser(this.userService.findById(result.getUserId()));
            resultExam.setMark(result.getMark());
            resultExam.setTakenDate(result.getTakenDate());
            resultExam.setTakenDuration(result.getTakenDuration());
            resultExamService.save(resultExam);
            System.err.println("result-exam is not null and saved:");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * get result-exam by Id
     *
     * @param id
     * @return result-exam
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResultExam> findResultExamById(@PathVariable long id) {
        ResultExam resultExam = resultExamService.findById(id);
        if(resultExam == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(resultExam, HttpStatus.OK);
    }
}
