package sprint_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_2.model.User;
import sprint_2.service.RoleService;
import sprint_2.service.UserService;
//import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

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
            user.setRole(roleService.finById((long) 1));
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
}
