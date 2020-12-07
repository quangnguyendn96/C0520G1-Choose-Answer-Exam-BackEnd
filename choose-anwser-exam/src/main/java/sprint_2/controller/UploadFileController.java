package sprint_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sprint_2.model.ExcelHelper;
import sprint_2.model.Question;
import sprint_2.model.ResponseMessage;
import sprint_2.service.UploadFileService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class UploadFileController {
    @Autowired
    UploadFileService uploadFileService;


    @PostMapping("/upload")
    public ResponseEntity<List<Question>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        List<Question> questions = uploadFileService.findAll(file);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping("/saveFile")
    public ResponseEntity<ResponseMessage> saveFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        ExcelHelper excelHelper = new ExcelHelper();
        if (excelHelper.hasExcelFormat(file)) {
            try {
                uploadFileService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }
}