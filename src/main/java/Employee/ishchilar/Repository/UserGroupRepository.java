package Employee.ishchilar.Repository;

import Employee.ishchilar.Model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup,Integer> {

    List<UserGroup> findAllByDeletedAtIsNull();
}
