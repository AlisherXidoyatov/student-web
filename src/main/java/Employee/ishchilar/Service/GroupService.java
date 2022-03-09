package Employee.ishchilar.Service;

import Employee.ishchilar.Exception.ServerBadRequestException;
import Employee.ishchilar.Model.Group;
import Employee.ishchilar.Repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
 @Autowired
    private GroupRepository groupRepository;
 @Autowired
 private CourseService courseService;

   public Boolean create (Group group){
       courseService.getOne(group.getCourseId());
       group.setCreatedAt(LocalDateTime.now());
       group.setStatus(true);
       groupRepository.save(group);
       return true;
   }

   public List<Group> getAll(){
       List<Group> groupList = groupRepository.findAllByDeletedAtIsNull();
       if(groupList.isEmpty()){
           throw new ServerBadRequestException("group not found");
       }
       return groupList;
   }

   public Boolean update(Integer id, Group group){
       courseService.getEntity(group.getCourseId());
       Group old = getEntity(id);
       group.setId(id);
       group.setStatus(old.getStatus());
       group.setCreatedAt(old.getCreatedAt());
       group.setUpdatedAt(LocalDateTime.now());
       groupRepository.save(group);
       return true;
   }

   public Boolean softDelete(Integer id){
       Group group = getEntity(id);
       group.setDeletedAt(LocalDateTime.now());
       groupRepository.save(group);
       return true;
   }

   public Boolean hardDelete(Integer id){
       Group group = getEntity(id);
       groupRepository.delete(group);
       return  true;
   }


public Group get(Integer id){
    Group group =  getEntity(id);
    group.setCourse(courseService.getOne(id));
    return group;

}



 public Group getEntity(Integer id){
     Optional<Group> optional = groupRepository.findById(id);
     if(optional.isEmpty() || optional.get().getDeletedAt() != null){
         throw new ServerBadRequestException("group not found");
     }
    return optional.get();
 }
}
