package Employee.ishchilar.Controller;

import Employee.ishchilar.Model.UserGroup;
import Employee.ishchilar.Service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-group")
public class UserGroupController {
    @Autowired
    private UserGroupService userGroupService;

    @GetMapping("/getOne")
    public ResponseEntity<?> getOne(@RequestParam("id") Integer id){
        UserGroup userGroup = userGroupService.get(id);
        if(userGroup == null){
            return ResponseEntity.badRequest().body("user Group not found");
        }
        return ResponseEntity.ok(userGroup);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        List<UserGroup> result = userGroupService.getAll();
        if(result.isEmpty()){
            return ResponseEntity.badRequest().body("userGroup not found");
        }
        return ResponseEntity.ok(result);
    }
 @PostMapping("/create")
    public  ResponseEntity<?> create (@RequestBody UserGroup userGroup){
        Boolean result = userGroupService.create(userGroup);
       return ResponseEntity.ok().body("UserGroup created");
 }

@PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id") Integer id,
                                    @RequestBody UserGroup userGroup){
        Boolean result = userGroupService.update(id,userGroup);
        return ResponseEntity.ok().body("userGroup updated");
}

@DeleteMapping("/softDelete")
public ResponseEntity<?> softDelete(@RequestParam("id") Integer id){
        Boolean result = userGroupService.softDelete(id);
        return ResponseEntity.ok().body("UserGroup deleted with softDelete");
}
@DeleteMapping("/hardDelete")
    public ResponseEntity<?> hardDelete(@RequestParam("id") Integer id){
        Boolean result = userGroupService.hardDelete(id);
        return ResponseEntity.ok().body("User group deleted with hardDelete");
}


}
