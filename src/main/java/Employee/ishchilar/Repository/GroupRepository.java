package Employee.ishchilar.Repository;

import Employee.ishchilar.Model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
 List<Group> findAllByDeletedAtIsNull();
 List<Group> findByName(String name);


}
