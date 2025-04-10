package mint.dev.business.controller;



import mint.dev.business.exceptions.BusinessException;
import mint.dev.business.services.UserService;
import mint.dev.infrastructure.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity user){
        try{
            //UserEntity userEntity2 = userService.findEmail(user.getEmail());
            UserEntity userEntity = userService.findEmail(user.getEmail());

            if(passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {

                return ResponseEntity.ok().body("Login Successful");

            } else {
                return ResponseEntity.badRequest().body("Invalid credentials");            }

        } catch (BusinessException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
}
