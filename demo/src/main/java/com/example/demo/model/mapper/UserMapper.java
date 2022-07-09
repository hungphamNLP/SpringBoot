package com.example.demo.model.mapper;

import org.mindrot.jbcrypt.BCrypt;

import com.example.demo.entity.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.CreateUserReq;

public class UserMapper {
    public static UserDto toUserDto(User user){
        UserDto tmp = new UserDto();
        tmp.setId(user.getId());
        tmp.setName(user.getName());
        tmp.setPhone(user.getPhone());
        tmp.setEmail(user.getEmail());
        tmp.setAvatar(user.getAvatar());
        tmp.setPassword(user.getPassword());

        return tmp;
    }
    public static User toUser(CreateUserReq req) {
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        // Hash password using BCrypt
        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);

        return user;
    }
}
