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
import sprint_2.service.RoleService;
import sprint_2.service.UserService;

import sprint_2.dto.ExamHistoryDTO;

import java.util.ArrayList;

//import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * UserController
 * <p>
 * Version 1.0
 * <p>
 * Date: 08-12-2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 08-12-2020         NhatL           CRUD
 */

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    /**
     * get data for User list page
     *
     * @param
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<User>> getListUser() {
        List<User> userList = userService.findAll();

       if (userList==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
    }
    /**
     * get data for User list page
     *
     * @param idUser
     * @return
     */
    @GetMapping("/{idUser}")
    public ResponseEntity<User> getUser(@PathVariable Long idUser) {
       User user = userService.findById(idUser);
       if (user==null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    /**
     * create user
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/create")
   public  ResponseEntity<Void> createUser(@RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            user.setRole(roleService.findById((long) 1));
            user.setImage("");
            userService.save(user);
            System.err.println(user.toString());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * edit user
     *
     * @param idUser,user
     * @return
     */
    @PutMapping("/edit/{idUser}")
    public  ResponseEntity<Void> editUser(@PathVariable Long idUser,@RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userNew= userService.findById(idUser);
        if (user == null){
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userNew.setFullName(user.getFullName());
            userNew.setEmail(user.getEmail());
            userNew.setAddress(user.getAddress());
            userNew.setPhoneNumber(user.getPhoneNumber());
            userService.save(userNew);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    /**
     * delete asset by idUser
     *
     * @param idUser
     * @return
     */
    @DeleteMapping("/delete/{idUser}")
    public  ResponseEntity<Void> deleteUser(@PathVariable Long idUser) {
        User user = userService.findById(idUser);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(idUser);

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
            List<Question> questionList;
            for (ResultExam resultExam : user.getResultExamCollection()) {
                questionList = new ArrayList<>();
                questionSet = resultExam.getExam().getQuestions();
                questionList.addAll(questionSet);
                examHistoryDTOList.add(new ExamHistoryDTO(
                        questionList.get(0).getSubject().getSubjectName(),
                        resultExam.getExam().getExamName(),
                        resultExam.getMark(),
                        resultExam.getTakenDate()));
            }
        }
        return new ResponseEntity<>(examHistoryDTOList, HttpStatus.OK);
    }
}
