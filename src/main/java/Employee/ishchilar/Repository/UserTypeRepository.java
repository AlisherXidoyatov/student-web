package Employee.ishchilar.Repository;

import Employee.ishchilar.Model.User;
import Employee.ishchilar.Model.UserType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

        List<UserType> findAllByDeletedAtNull();
       List<UserType> findAllByName(String name);
       @NotNull List<UserType>findAll();
       void deleteAll();

}
