package com.example.jpaexercise.Controller;

import com.example.jpaexercise.Model.User;
import com.example.jpaexercise.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

@GetMapping("/get")
public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
}

@PostMapping("/add")
public ResponseEntity addUser(@Valid @RequestBody User user , Errors errors){
    if(errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
    userService.addUser(user);
    return ResponseEntity.status(200).body("Successfully added!");
}

    @PutMapping("/update/{id}")
    public  ResponseEntity updateUser (@PathVariable Integer id, @Valid @RequestBody User user , Errors errors){
    if(errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
    boolean isUpdated = userService.updateUser(id, user);
    if(isUpdated){
        return ResponseEntity.status(200).body("Successfully updated!");
    }
    return ResponseEntity.status(400).body("Error updating user!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
    boolean isDeleted = userService.deleteUser(id);
    if(isDeleted){
        return ResponseEntity.status(200).body("Successfully deleted!");
    }
    return ResponseEntity.status(400).body("Error deleting user!");
    }



}
