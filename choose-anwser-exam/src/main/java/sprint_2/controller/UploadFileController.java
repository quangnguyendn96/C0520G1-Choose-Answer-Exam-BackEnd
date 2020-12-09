package sprint_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sprint_2.commonUtils.ExcelHelper;
import sprint_2.model.Question;
import sprint_2.commonUtils.ResponseMessage;
import sprint_2.service.UploadFileService;

import java.util.List;

@RestController
@CrossOrigin
public class UploadFileController {
    @Autowired
    UploadFileService uploadFileService;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        ExcelHelper excelHelper = new ExcelHelper();
        if (excelHelper.hasExcelFormat(file)) {
            try {
                List<Question> questions = uploadFileService.findAll(file);
                return new ResponseEntity<>(questions, HttpStatus.OK);
            } catch (Exception e) {
                message = "Không phân tích được tệp Excel: " + file.getOriginalFilename() + " Vui lòng kiểm tra lại !";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Vui lòng chọn định dạng file excel!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


    @PostMapping("/saveFile")
    public ResponseEntity<ResponseMessage> saveFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        ExcelHelper excelHelper = new ExcelHelper();
        if (excelHelper.hasExcelFormat(file)) {
            try {
                uploadFileService.save(file);
                message = "Lưu câu hỏi thành công từ file: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Không thể lưu câu hỏi vui long kiểm tra lại file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Vui lòng chọn định dạng file excel!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
}