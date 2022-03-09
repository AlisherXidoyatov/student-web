package Employee.ishchilar.Service;

import Employee.ishchilar.Exception.ServerBadRequestException;
import Employee.ishchilar.Model.User;
import Employee.ishchilar.Model.UserType;
import Employee.ishchilar.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public record UserService(UserRepository userRepository,
                          UserTypeService userTypeService) {


    //Main functions
    public Boolean create( User user) {
        Optional<User> optional = userRepository.findAllByIdAndDeletedAtNull(user.getId());
       if(optional.isPresent()){
           throw new ServerBadRequestException("User already exist!");
       }
       userTypeService.getEntity(user.getUserTypeId());
        user.setStatus(true);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return true;
    }


    public List<User> getAll() {
        List<User> userList = userRepository.findAllByDeletedAtIsNull();
//       for(User user : userList){
//           user.setUserType(userTypeService.get(user.getUserTypeId()));
//       }
        return userList;
    }


    public User getOne(Integer id) {
        User user = getEntity(id);
        UserType userType = userTypeService.get(user.getUserTypeId());
        user.setUserType(userType);
        return user;
    }

    public Boolean update (Integer id, User user){
        User old = getEntity(id);
        user.setId(id);
        user.setUpdatedAt(LocalDateTime.now());
        user.setCreatedAt(old.getCreatedAt());
        user.setStatus(old.getStatus());
        userRepository.save(user);
        return  true;
    }
    public Boolean softDelete(Integer id){
        User user = getEntity(id);
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
        return true;
    }
    public Boolean hardDelete(Integer id){
        User user = getEntity(id);
        userRepository.delete(user);
        return true;
    }





    //Secondary function
    public User getEntity(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty() || optional.get().getDeletedAt() != null) {
         throw new ServerBadRequestException("not found") ;
        }
        return optional.get();
    }


    public void deleteAll() {
        userRepository.deleteAll();
    }
}
