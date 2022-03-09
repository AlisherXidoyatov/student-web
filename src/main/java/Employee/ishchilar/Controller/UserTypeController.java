package Employee.ishchilar.Controller;

import Employee.ishchilar.DTO.UserTypeDto;
import Employee.ishchilar.Model.UserType;
import Employee.ishchilar.Service.UserTypeService;
 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user-type")

public class UserTypeController {

    private UserTypeService userTypeService;

    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }




    @PostMapping
    public  ResponseEntity<?> create(@RequestBody @Valid UserTypeDto userTypeDto){
        UserTypeDto result = userTypeService.create(userTypeDto);
        if(result == null){
            return ResponseEntity.badRequest().body("User type  not created :(");
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll (){
        List<UserType> result = userTypeService.getAll();
        if(result == null){
            return  ResponseEntity.badRequest().body("UserTypes not found :(");
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam("id")Integer id){
        UserType result = userTypeService.get(id);
        if (result == null) {
            return  ResponseEntity.badRequest().body("User not found :(");
        }
        return  ResponseEntity.ok(result);

    }


    @GetMapping("/getByName")
    public ResponseEntity<?>getByName(@RequestParam("name")String name){
        List<UserType> result = userTypeService.getByName(name);
        if (result == null) {
            return ResponseEntity.badRequest().body("Users with this name not found :(");
        }
        return ResponseEntity.ok(result);
    }


    @PutMapping ("/update")
    public  ResponseEntity<?> update(@RequestParam("id")Integer id,@RequestBody UserTypeDto userTypeDto){
        UserTypeDto dto = userTypeService.update( id, userTypeDto );
       if(dto == null){
           return ResponseEntity.badRequest().body("User not found :(");
       }
       return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/softDelete")
    public ResponseEntity<?> softDelete(@RequestParam ("id")Integer id){
        Boolean result = userTypeService.softDelete(id);
        if(!result){
            return ResponseEntity.badRequest().body("User not found :(");
        }
        return ResponseEntity.ok("User deleted :)");
    }
    @DeleteMapping("/hardDelete")
    public ResponseEntity<?> hardDelete(@RequestParam ("id")Integer id){
        Boolean result = userTypeService.hardDelete(id);
        if(!result){
            return ResponseEntity.badRequest().body("UserType with this id not found:(");
        }
        return ResponseEntity.ok().body("You deleted this userType with hardDelete :)");
    }




    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll(){
        userTypeService.deleteAll();
        return ResponseEntity.ok("All UserTypes deleted :)");
    }


}
