package Employee.ishchilar.Repository;

import Employee.ishchilar.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CourseRepostory extends JpaRepository<Course, Integer> {
    List<Course>findAll();
     List<Course> findByName(String name);

}
