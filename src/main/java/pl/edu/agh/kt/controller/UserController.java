package pl.edu.agh.kt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.kt.entity.User;
import pl.edu.agh.kt.model.AuthenticationRequest;
import pl.edu.agh.kt.model.AuthenticationResponse;
import pl.edu.agh.kt.service.UserService;
import pl.edu.agh.kt.util.JwtUtil;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping(path = "/test")
    public String hello() {
        return "Test successful";
    }

    @GetMapping(path = "/user")
    public List<User> hellou() {
      return  userService.getUsers();
    }


    @PostMapping(path = "/registration")
    public void hello(@RequestBody User user) {
        userService.save(user);
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final User user = userService.findUserByUsername(authRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
