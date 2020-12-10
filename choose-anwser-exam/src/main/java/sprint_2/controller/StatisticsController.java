package sprint_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_2.service.ResultExamService;

import java.util.ArrayList;
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

    @GetMapping(value = "/search-container-subject")
    public ResponseEntity<List<?>> getResultExamSubjectBy() {
        List<?> subjectByQuarter1;
        List<?> subjectByQuarter2;
        List<?> subjectByQuarter3;
        List<?> subjectByQuarter4;
        List<Object[]> quarterList = new ArrayList<>();

        String month1 = "-01-2020";
        String month2 = "-02-2020";
        String month3 = "-03-2020";
        String month4 = "-04-2020";
        String month5 = "-05-2020";
        String month6 = "-06-2020";
        String month7 = "-07-2020";
        String month8 = "-08-2020";
        String month9 = "-09-2020";
        String month10 = "-10-2020";
        String month11 = "-11-2020";
        String month12 = "-12-2020";

        subjectByQuarter1 = resultExamService.getCountSubjectByMonth(month1, month2, month3);
        subjectByQuarter2 = resultExamService.getCountSubjectByMonth(month4, month5, month6);
        subjectByQuarter3 = resultExamService.getCountSubjectByMonth(month7, month8, month9);
        subjectByQuarter4 = resultExamService.getCountSubjectByMonth(month10, month11, month12);

        Object[] quarter1 = subjectByQuarter1.toArray();
        Object[] quarter2 = subjectByQuarter2.toArray();
        Object[] quarter3 = subjectByQuarter3.toArray();
        Object[] quarter4 = subjectByQuarter4.toArray();

        quarterList.add(quarter1);
        quarterList.add(quarter2);
        quarterList.add(quarter3);
        quarterList.add(quarter4);
        return new ResponseEntity<>(quarterList, HttpStatus.OK);
    }
}
