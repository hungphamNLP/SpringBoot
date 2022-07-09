package com.example.demo.controller;

import java.util.List;
import javax.validation.Valid;
// import javax.validation.Valid;

import com.example.demo.model.request.CreateUserReq;
import com.example.demo.model.request.UpdateUserReq;

// import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.entity.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {
    // private static String UPLOAD_DIR = System.getProperty("user.home") + "/upload";

    @Autowired
    private UserService userservice;

    

    @GetMapping("/search")
    public ResponseEntity<?> searchUser(@RequestParam(name="keyword") String name){
        List<UserDto> users = userservice.searchUser(name);
        return ResponseEntity.ok(users);
    }
    
    @ApiOperation(value = "Get list user", response = UserDto.class, responseContainer = "List")
    @ApiResponses({
        @ApiResponse(code=500,message = "")
    })

    @GetMapping("")
    public ResponseEntity<?> getListUser(){
        List<UserDto> users = userservice.getListUser();        
        return ResponseEntity.ok(users);
    }

    @ApiOperation(value = "Get user info by id", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code=404,message = "No user found"),
            @ApiResponse(code=500,message = "")
    })

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        UserDto result = userservice.getUserById(id);
        return ResponseEntity.ok(result);
    }


    @ApiOperation(value = "Create user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code=400,message = "Email already exists in the system"),
            @ApiResponse(code=500,message = "")
    })
    @PostMapping("")   
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserReq req){
        UserDto result = userservice.createUser(req);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "Update user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code=404,message = "No user found"),
            @ApiResponse(code=500,message = "")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserReq req, @PathVariable int id){
        UserDto result = userservice.updateUser(req, id);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "Delete user by id", response = String.class)
    @ApiResponses({
            @ApiResponse(code=404,message = "No user found"),
            @ApiResponse(code=500,message = "")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        userservice.deleteUser(id);
        return ResponseEntity.ok("Delete success");
    }
}
