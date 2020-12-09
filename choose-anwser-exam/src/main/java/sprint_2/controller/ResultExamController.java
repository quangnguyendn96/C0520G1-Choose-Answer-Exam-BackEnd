//package sprint_2.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import sprint_2.model.*;
//import sprint_2.service.ExamService;
//import sprint_2.service.ResultExamService;
//import sprint_2.service.UserService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/resultExam")
//@CrossOrigin
//public class ResultExamController {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ExamService examService;
//
//    @Autowired
//    private ResultExamService resultExamService;
//
//    @GetMapping("/list")
//    public ResponseEntity<List<ResultExam>> getListResultExam() {
//        List<ResultExam> list = resultExamService.findAll();
//        if (list.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
//
//    @GetMapping("/list-exam-by-subject/{subject}")
//    public ResponseEntity<List<Exam>> getListExamBySubject(@PathVariable String subject) {
//        List<Exam> examList = examService.findAll();
//        List<Exam> examListBySubject = new ArrayList<>();
//        if(examList.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        Question question = null;
//        List<Question> questions = null;
//        for(Exam exam : examList){
//            questions = exam.getQuestions();
//            if(questions.isEmpty()){
//                continue;
//            }
//            question = questions.get(0);
//            System.err.println(question.toString());
//            if(question.getSubject().getSubjectName().toLowerCase().contains(subject.toLowerCase())){
//                examListBySubject.add(exam);
//            }
//        }
//
//        if(examListBySubject.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        System.err.println(examListBySubject);
//        return new ResponseEntity<>(examListBySubject, HttpStatus.OK);
//    }
//
//    @GetMapping("/exam-by-id/{id}")
//    public ResponseEntity<Exam> findExamById(@PathVariable long id) {
//        Exam exam = examService.findById(id);
//        if(exam == null){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(exam, HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/create")
//    public  ResponseEntity<Void> createResultExam(@RequestBody ResultExamDTO result) {
//        if(result != null) {
//            System.err.println("DTO input"+ result.toString());
//            ResultExam resultExam = new ResultExam();
//            resultExam.setExam(this.examService.findById(result.getExamId()));
//            resultExam.setUser(this.userService.findById(result.getUserId()));
//            resultExam.setMark(result.getMark());
//            resultExam.setTakenDate(result.getTakenDate());
//            resultExam.setTakenDuration(result.getTakenDuration());
//            resultExamService.save(resultExam);
//            System.err.println("result-exam is not null and saved:");
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ResultExam> findResultExamById(@PathVariable long id) {
//        ResultExam resultExam = resultExamService.findById(id);
//        if(resultExam == null){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(resultExam, HttpStatus.OK);
//    }
//}
