package com.vivek.notes.controllers;
import java.util.List;
import com.vivek.notes.payload.ApiRes;

import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vivek.notes.payload.LoginBody;
import com.vivek.notes.payload.UserDto;
import com.vivek.notes.services.UserService;


@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    //create
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto userDto =  this.userService.createUser(user);
        return ResponseEntity.ok(userDto);
    }

    //update
//    @PostMapping("/{userId}")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user,@PathVariable Integer userId){
        UserDto userDto =  this.userService.updateUser(user, userId);
        return ResponseEntity.ok(userDto);
    }
    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }


    //get
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
        UserDto userDto =  this.userService.getUser(userId);
        return ResponseEntity.ok(userDto);
    }

    //get all
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos =  this.userService.getAllUser();
        return ResponseEntity.ok(userDtos);
    }
//    @PostMapping("/login")
//    public ResponseEntity<UserDto> userLogin(@RequestBody LoginBody loginBody){
//        UserDto apiRes = new UserDto();
//        try {
//            apiRes =  this.userService.userLogin(loginBody.getEmail(), loginBody.getPassword());
//            return ResponseEntity.ok(apiRes);
//        } catch (Exception e) {
//            return new ResponseEntity<UserDto>(apiRes, HttpStatus.UNAUTHORIZED);
//        }
//
//    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginBody loginBody) {
        try {
            UserDto apiRes = this.userService.userLogin(loginBody.getEmail(), loginBody.getPassword());
//            return ResponseEntity.ok(apiRes );
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
    }


}
