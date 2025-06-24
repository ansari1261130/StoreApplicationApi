package com.springboot.StoreApplicationApi.controllers;

import com.springboot.StoreApplicationApi.dtos.user.ChangePasswordRequest;
import com.springboot.StoreApplicationApi.dtos.user.RegisterUserRequest;
import com.springboot.StoreApplicationApi.dtos.user.UserDto;
import com.springboot.StoreApplicationApi.mappers.UserMapper;
import com.springboot.StoreApplicationApi.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

//    @PostMapping
//    public UserDto createUser(@RequestBody UserDto data) {
//        return data;
//    }

    @PostMapping
    public ResponseEntity<?> registerUser(
           @Valid @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(
                    Map.of("email","Email is already registered.")
            );
        }
        var user = userMapper.toEntity(request);
        userRepository.save(user);
        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable(name = "id") Long id,
            @RequestBody RegisterUserRequest request) {
        var user = userRepository.findById(id).orElse(null);
        if(user == null) return ResponseEntity.notFound().build();
        userMapper.update(request,user);
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id) {
        var user = userRepository.findById(id).orElse(null);
        if(user == null) return ResponseEntity.notFound().build();
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<UserDto> changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequest request) {
        var user = userRepository.findById(id).orElse(null);
        if(user == null) return ResponseEntity.notFound().build();
        if(!user.getPassword().equals(request.getOldPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

}
