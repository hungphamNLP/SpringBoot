package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.model.request.CreateUserReq;
import com.example.demo.model.request.UpdateUserReq;
import com.example.demo.model.dto.UserDto;

@Service
public interface UserService {
    public List<UserDto> getListUser();

    public UserDto getUserById(int id);

    public List<UserDto> searchUser(String keyword);

    public UserDto createUser(CreateUserReq req);

    public UserDto updateUser(UpdateUserReq req, int id);

    public boolean deleteUser(int id);
}
