package Employee.ishchilar.Service;

import Employee.ishchilar.Exception.ServerBadRequestException;
import Employee.ishchilar.Model.Course;
import Employee.ishchilar.Model.User;
import Employee.ishchilar.Repository.CourseRepostory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
 private final CourseRepostory courseRepostory;

    public CourseService(CourseRepostory courseRepostory) {
        this.courseRepostory = courseRepostory;
    }


    //Main functions
    public Boolean create(Course course){
        course.setCreatedAt(LocalDateTime.now());
        course.setStatus(true);
        courseRepostory.save(course);
        return true;
    }



    public List<Course> getAll(){
        List<Course> courseList = courseRepostory.findAll();
        if(courseList.isEmpty()){
            return null;
        }
        return courseList;
    }


    public Course getOne(Integer id){
        Course course = getEntity(id);
        if(course== null){
           throw new ServerBadRequestException("Course not found");
        }
        return course;
    }


    public Boolean softDelete(Integer id){
        Course entity = getEntity(id);
        entity.setStatus(false);
        entity.setDeletedAt(LocalDateTime.now());
        courseRepostory.save(entity);
        return true;
    }


    public Boolean hardDelete(Integer id){
        Course entity = getEntity(id);
        courseRepostory.delete(entity);
        return true;
    }

    public Boolean update (Integer id, Course course){
        Course old = getEntity(id);
        course.setId(id);
        course.setCreatedAt(old.getCreatedAt());
        course.setStatus(old.getStatus());
        course.setUpdatedAt(LocalDateTime.now());
        courseRepostory.save(course);
        return  true;
    }
    public List<Course> getByName(String name){
        List<Course> courseList = courseRepostory.findByName(name);
        if(courseList.isEmpty()){
            throw new ServerBadRequestException("Course with this name not found");
        }
        return courseList;
    }



    //Secondary function
    public Course getEntity(Integer id){
        Optional<Course> optional = courseRepostory.findById(id);
        if(optional.isEmpty() || optional.get().getDeletedAt() != null ){
            throw new ServerBadRequestException("Course with this id not found :( ");
        }
        return optional.get();
    }


}
