package sprint_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_2.service.ResultExamService;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private ResultExamService resultExamService;

    @GetMapping("/sum-point")
    public ResponseEntity<List<?>> getListDataExam() {
        List<?> dataList = resultExamService.statisticsData();
        if (dataList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dataList, HttpStatus.OK);
    }

    @GetMapping("/count-subject")
    public ResponseEntity<List<?>> getListCountExamSubject() {
        List<?> countSubjectList = resultExamService.statisticsCountExamSubject();
        if (countSubjectList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(countSubjectList, HttpStatus.OK);
    }

    @GetMapping(value = "/search-by-subject")
    public ResponseEntity<List<?>> getUserResultExamBySubject(@RequestParam("codeSubject") String codeSubject) {
        List<?> topUserBySubjectList = null;
        if ("1".equals(codeSubject)) {
            String nameSubject = "JavaScript";
            topUserBySubjectList = resultExamService.getStatisticsResultExamUserBySubject(nameSubject);
        } else if ("2".equals(codeSubject)) {
            String nameSubject = "HTML";
            topUserBySubjectList = resultExamService.getStatisticsResultExamUserBySubject(nameSubject);
        } else if ("3".equals(codeSubject)) {
            String nameSubject = "Angular";
            topUserBySubjectList = resultExamService.getStatisticsResultExamUserBySubject(nameSubject);
        } else if ("4".equals(codeSubject)) {
            String nameSubject = "Java";
            topUserBySubjectList = resultExamService.getStatisticsResultExamUserBySubject(nameSubject);
        }
        return new ResponseEntity<>(topUserBySubjectList, HttpStatus.OK);
    }
}
