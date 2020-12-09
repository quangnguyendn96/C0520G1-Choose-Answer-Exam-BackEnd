package sprint_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sprint_2.dto.ChangePasswordDTO;
import sprint_2.model.Question;
import sprint_2.model.ResultExam;
import sprint_2.model.User;
import sprint_2.service.UserService;
import sprint_2.dto.ExamHistoryDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @Autowired

    @GetMapping()
    public ResponseEntity<List<User>> getListUser() {
        List<User> userList = userService.findAll();
        if (userList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<User> findUserById(@PathVariable long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateAccount(@RequestBody User user, @PathVariable Long id) {
        User user1 = userService.findById(id);
        user1.setFullName(user.getFullName());
        user1.setEmail(user.getEmail());
        user1.setAddress(user.getAddress());
        user1.setPhoneNumber(user.getPhoneNumber());
        userService.save(user1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * change password user
     *
     * @param changePasswordDTO
     * @return message
     */
    @PutMapping(value = "/{id}/change-password")
    public ResponseEntity<?> changePassWordUser(@Validated @RequestBody ChangePasswordDTO changePasswordDTO,
                                                @PathVariable("id") long id) {
        User user = userService.findById(id);
        List<ChangePasswordDTO> errorsList = new ArrayList<>();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
//        if (BCrypt.checkpw(changePasswordDTO.getOldPassword(), user.getPassword())) {
//            userService.changePassWord(id, passwordEncoder.encode(changePasswordDTO.getNewPassword()));
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
        if (user.getPassword().equals(changePasswordDTO.getOldPassword())) {
            userService.changePassWord(id, changePasswordDTO.getNewPassword());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            errorsList.add(new ChangePasswordDTO("Mật khẩu không chính xác"));
            return new ResponseEntity<>(errorsList, HttpStatus.OK);
        }
    }

    @GetMapping("/findExamHistoryById/{id}")
    public ResponseEntity<List<ExamHistoryDTO>> getExamHistory(@PathVariable Long id) {
        List<ExamHistoryDTO> examHistoryDTOList = new ArrayList<>();
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            Set<Question> questionSet;
            List<Question> questionList = new ArrayList<>();
            for (ResultExam resultExam : user.getResultExamCollection()) {
                questionSet = resultExam.getExam().getQuestions();
                questionList.addAll(questionSet);
                examHistoryDTOList.add(new ExamHistoryDTO(
                        questionList.get(1).getSubject().getSubjectName(),
                        resultExam.getExam().getExamName(),
                        resultExam.getMark(),
                        resultExam.getTakenDate()));
            }
        }
        return new ResponseEntity<>(examHistoryDTOList, HttpStatus.OK);
    }
}
