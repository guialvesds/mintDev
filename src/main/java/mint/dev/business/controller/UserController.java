package mint.dev.business.controller;

import mint.dev.infrastructure.enums.PerfilUserStatus;
import mint.dev.business.services.UserService;
import mint.dev.infrastructure.entity.UserEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //ResponseEntity retorna um Objeto com uma lista de usuários
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
            var users = this.userService.findAll();
            return ResponseEntity.ok(users);
    }

    //Aqui não utilizei o List pois vamos retornar apenas um usuário
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable String id){
        var user = this.userService.findId(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/on")
    public String getApi(){
        return "Api no ar!";
    }

    @PostMapping
    public ResponseEntity<UserEntity> save(@RequestBody UserEntity user, @RequestParam(required = false) String perfil) {
        //Aqui converto o perfil recebido para o tipo ENUM
        if(perfil != null) {
            user.setPerfil(PerfilUserStatus.valueOf(perfil.toUpperCase()));
        }
        user = this.userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable String id, @RequestBody UserEntity user) {
        user.setId(id);
        UserEntity updatedUser = this.userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Build controi o objeto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(String id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
