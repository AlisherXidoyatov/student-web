package Employee.ishchilar.Controller;

import Employee.ishchilar.Model.Group;
import Employee.ishchilar.Service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Group group){
        Boolean result = groupService.create(group);
        if(!result){
            return ResponseEntity.badRequest().body("Group not created");
        }
        return ResponseEntity.ok().body("Group created");
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        List<Group> groupList = groupService.getAll();
        if(groupList.isEmpty()){
            return ResponseEntity.badRequest().body("Groups not found");
        }
        return ResponseEntity.ok(groupList);
    }
    @GetMapping("/getOne")
    public ResponseEntity<?> getOne(@RequestParam("id") Integer id){
        Group group = groupService.get(id);
        if(group == null){
            return  ResponseEntity.badRequest().body("Group not found");
        }
        return ResponseEntity.ok(group);
    }
    @PutMapping("/update")
    public  ResponseEntity<?> update(@RequestParam("id") Integer id,
                                     @RequestBody Group group){
        Boolean result = groupService.update(id, group);
        if(!result){
            return ResponseEntity.badRequest().body("group not updated");
        }
        return ResponseEntity.ok().body("group updated");
    }

    @DeleteMapping("/softDelete")
    public  ResponseEntity<?> softDelete(@RequestParam("id") Integer id){

        Boolean result = groupService.softDelete(id);
        if(!result){
            return ResponseEntity.badRequest().body("group not deleted");
        }
        return ResponseEntity.ok().body("group deleted with softDelete");
    }
    @DeleteMapping("/hardDelete")
    public ResponseEntity<?> hardDelete(@RequestParam("id") Integer id){
        Boolean result = groupService.hardDelete(id);
        if(!result){
            return ResponseEntity.badRequest().body("group not deleted");
        }
        return ResponseEntity.ok().body("group deleted with hardDelete");
    }
}
