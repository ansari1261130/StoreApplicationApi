package com.springboot.StoreApplicationApi.controllers;

import com.springboot.StoreApplicationApi.dtos.UserDto;
import com.springboot.StoreApplicationApi.entities.User;
import com.springboot.StoreApplicationApi.mappers.UserMapper;
import com.springboot.StoreApplicationApi.repositories.CategoryRepository;
import com.springboot.StoreApplicationApi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers(
           // @RequestHeader(required = false, name = "x-auth-token") String authToken,
            @RequestParam(required = false,defaultValue = "",name = "sort") String sortBy
    ) {
       // System.out.println(authToken);
        if(!Set.of("name","email").contains(sortBy)) sortBy="name";
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        var userDto = userRepository.findById(id).orElse(null);
        if(userDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userMapper.toDto(userDto));
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto data) {
        return data;
    }
}
