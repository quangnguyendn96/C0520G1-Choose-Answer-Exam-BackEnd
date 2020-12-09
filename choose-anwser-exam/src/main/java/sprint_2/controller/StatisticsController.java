package sprint_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
