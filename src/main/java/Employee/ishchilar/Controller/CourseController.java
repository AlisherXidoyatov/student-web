package Employee.ishchilar.Controller;

import Employee.ishchilar.Model.Course;
import Employee.ishchilar.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
 @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Course course){

      Boolean result =   courseService.create(course);
      return ResponseEntity.ok("Your course is created :)");
 }

     @GetMapping("/getAll")
    public ResponseEntity<?>getAll(){
        List<Course> result = courseService.getAll();
        if(result == null){
            return ResponseEntity.badRequest().body("Courses not found :(");
        }
        return ResponseEntity.ok(result);
 }
     @GetMapping("/getOne")
    public ResponseEntity<?> getOne(@RequestParam("id") Integer id){
        Course result = courseService.getOne(id);
        if(result == null ){
            return ResponseEntity.badRequest().body("Course with this id not found :(");
        }
        return ResponseEntity.ok(result);

 }
 @GetMapping("/getByName")
 public ResponseEntity<?> getByName(@RequestParam("name")String name){
        List<Course> result = courseService.getByName(name);
        if(result.isEmpty()){
            return ResponseEntity.badRequest().body("Course with this name not found");
        }
        return ResponseEntity.ok(result);
 }


    @DeleteMapping("/softDelete")
    public ResponseEntity<?> softDelete (@RequestParam("id") Integer id){
        Boolean result = courseService.softDelete(id);
        if(result == false){
            return ResponseEntity.badRequest().body("Course with this id not deleted :(");
        }
        return ResponseEntity.ok("You have deleted this course with softDelete :)" );
 }

    @DeleteMapping("/hardDelete")
    public ResponseEntity<?> hardDelete(@RequestParam ("id")Integer id){
        Boolean result = courseService.hardDelete(id);
        if(result == false){
            return ResponseEntity.badRequest().body("Course with this id not deleted :(");
        }
        return ResponseEntity.ok().body("You have deleted this course :)");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("id")Integer id,
                                    @RequestBody Course course) {
        Boolean entity = courseService.update(id, course);
        if (entity == null) {
            return ResponseEntity.badRequest().body("User not found :(");
        }
        return ResponseEntity.ok(entity);
        ////////////////////////////ghhdhtedhg
    }


}
