package sprint_2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sprint_2.model.Question;


import java.io.IOException;
import java.util.List;

@Service
public interface UploadFileService {

    List<Question> findAll(MultipartFile file) throws IOException;

    void save(MultipartFile file);
}
