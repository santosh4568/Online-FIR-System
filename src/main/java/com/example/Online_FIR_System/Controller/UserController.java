package com.example.Online_FIR_System.Controller;

import com.example.Online_FIR_System.Model.User;
import com.example.Online_FIR_System.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
        userService.SaveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody User user) {
        if (!userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userService.updatePassword(user.getUsername(), user.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully");
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable(name = "username") String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUser();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/name/{username}")
    public ResponseEntity<String> userName(@PathVariable(name = "username") String username) {
        String name = userService.getName(username);
        if (name == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(name);
    }

    @GetMapping("/district/{district}")
    public ResponseEntity<List<User>> getUsersByDistrict(@PathVariable(name = "district") String district) {
        List<User> users = userService.getUsersByDistrict(district);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<User>> getUsersByState(@PathVariable(name = "state") String state) {
        List<User> users = userService.getUsersByState(state);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<User>> getUsersByCity(@PathVariable(name = "city") String city) {
        List<User> users = userService.getUsersByCity(city);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/policeStation/{policeStation}")
    public ResponseEntity<List<User>> getUsersByPoliceStation(@PathVariable(name = "policeStation") String policeStation) {
        List<User> users = userService.getUsersByPoliceStation(policeStation);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(users);
    }





    @PostMapping("/updateRole")
    public ResponseEntity<String> updateRole(@RequestBody User user) {
        if (!userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userService.updateRole(user.getUsername(), user.getRole().name());
        return ResponseEntity.status(HttpStatus.OK).body("Role updated successfully");
    }
}
