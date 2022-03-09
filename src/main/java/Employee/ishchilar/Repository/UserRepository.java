package Employee.ishchilar.Repository;

import Employee.ishchilar.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
   List<User> findAll();
   List<User> findAllByDeletedAtIsNull();
  Optional<User> findAllByIdAndDeletedAtNull(Integer id);

   void deleteAll();
}

