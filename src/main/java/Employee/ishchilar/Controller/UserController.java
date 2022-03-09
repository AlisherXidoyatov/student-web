package Employee.ishchilar.Controller;

import Employee.ishchilar.Model.User;
import Employee.ishchilar.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public record UserController(UserService userService) {


    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        Boolean result = userService.create(user);
        if (!result) {
            return ResponseEntity.badRequest().body("User not created :(");
        }
        return ResponseEntity.ok("User created :)");

    }



    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<User> result = userService.getAll();
        if (result == null) {
            return ResponseEntity.badRequest().body("Users not found :(");
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/getOne")
    public ResponseEntity<?> getOne(@RequestParam("id") Integer id) {
         userService.getOne(id);
        if (id == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(userService.getOne(id));
    }



    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id")Integer id,
                                    @RequestBody User user){
        Boolean entity =  userService.update(id, user);

        if(!entity){
            return ResponseEntity.badRequest().body("User not found :(");
        }
        return ResponseEntity.ok("updated");
    }



    @DeleteMapping("/softDelete")
    public ResponseEntity<?> softDelete(@RequestParam("id")Integer id){
        Boolean result = userService.softDelete(id);
        if(!result){
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok().body("User deleted with soft delete");
    }

    @DeleteMapping("/hardDelete")
    public ResponseEntity<?> hardDelete(@RequestParam("id")Integer id){
        Boolean result = userService.hardDelete(id);
        if(!result){
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok().body("User deleted with hard delete");
    }



    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        userService.deleteAll();
        return ResponseEntity.ok("All Users deleted :)");
    }
}
