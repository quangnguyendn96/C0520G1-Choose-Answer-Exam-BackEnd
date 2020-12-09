package sprint_2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sprint_2.model.ExcelHelper;
import sprint_2.model.Question;
import sprint_2.repository.UploadFileRepository;
import sprint_2.service.UploadFileService;

import java.io.IOException;
import java.util.List;

@Service
public class UploadFileServiceImpl implements UploadFileService {
    @Autowired
    UploadFileRepository uploadFileRepository;

    @Autowired
    ExcelHelper excelHelper;

    @Override
    public List<Question> findAll(MultipartFile file) throws IOException {
        return excelHelper.excelQuestion(file.getInputStream());
    }

    public void save(MultipartFile file) {
        try {
            List<Question> questions = excelHelper.excelQuestion(file.getInputStream());
            uploadFileRepository.saveAll(questions);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
