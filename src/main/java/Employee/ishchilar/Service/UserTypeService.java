package Employee.ishchilar.Service;

import Employee.ishchilar.DTO.UserTypeDto;
import Employee.ishchilar.Exception.ServerBadRequestException;
import Employee.ishchilar.Model.UserType;
import Employee.ishchilar.Repository.UserTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserTypeService {

    @Autowired
    private  final UserTypeRepository userTypeRepository;

    public UserTypeService(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }




    //Main functions

    public UserTypeDto create(UserTypeDto dto){
        UserType entity = new UserType();
        UserTypeDto result = convertDtoToEntity(dto, entity);
//        UserType entity = new UserType();
//        UserTypeDto d = new UserTypeDto();
//        entity.setName(dto.getName());
//        entity.setDisplayName(dto.getDisplayName());
//        entity .setStatus(true);
//        entity .setCreatedAt(LocalDateTime.now());
//        userTypeRepository.save(entity );
//        d.setId(entity.getId());
//        d.setCreatedAt(entity.getCreatedAt());
//        d.setName(entity.getName());
//        d.setDisplayName(entity.getDisplayName());
//        d.setStatus(entity.getStatus());
//        return d;
        return result;
    }

    public List<UserType> getAll(){
        List<UserType> userTypeList = userTypeRepository.findAllByDeletedAtNull();
        if(userTypeList.isEmpty()){
            return null;
        }
        return userTypeList;
    }

    public UserType get(Integer id){
        return getEntity(id);
    }


    public List<UserType> getByName(String name){
    List<UserType> userTypeList = userTypeRepository.findAllByName(name);
        if (userTypeList.isEmpty()) {
        throw new ServerBadRequestException("userType with this name not found");
        }
    return userTypeList;
    }



    public UserTypeDto update (Integer id ,UserTypeDto userTypeDto){

        UserType oldEntity = getEntity(id);
        oldEntity.setUpdatedAt(LocalDateTime.now());
        oldEntity.setName(userTypeDto.getName());
        oldEntity.setDisplayName(userTypeDto.getDisplayName());
        userTypeRepository.save(oldEntity);
        userTypeDto.setId(id);
        userTypeDto.setStatus(oldEntity.getStatus());
        userTypeDto.setUpdatedAt(oldEntity.getUpdatedAt());
        userTypeDto.setCreatedAt(oldEntity.getCreatedAt());
        return  userTypeDto;
    }


    public Boolean softDelete (Integer id){

        UserType userType = getEntity(id);
        userType.setDeletedAt(LocalDateTime.now());
        userType.setStatus(false);
        userTypeRepository.save(userType);
        return true;
    }

    public Boolean hardDelete(Integer id){
        UserType userType = getold(id);
        userTypeRepository.delete(userType);
        return true;
    }



    public void deleteAll(){
        userTypeRepository.deleteAll();
    }

    //Secondary function
    public UserType getEntity(Integer id) {
        Optional<UserType> optional = userTypeRepository.findById(id);
        if(optional.isEmpty() ||  optional.get().getDeletedAt() != null){
           throw  new ServerBadRequestException("userType with this id not found");
        }
        return optional.get();
    }
    public UserType getold(Integer id){
        Optional<UserType> optional = userTypeRepository.findById(id);
        if(optional.isEmpty()){
            throw new ServerBadRequestException("userType not found");
        }
        return optional.get();
    }

    //convert
    public UserTypeDto convertDtoToEntity(  UserTypeDto d , UserType entity){

        entity.setName(d.getName());
        entity.setDisplayName(d.getDisplayName());
        entity .setStatus(true);
        entity .setCreatedAt(LocalDateTime.now());
        userTypeRepository.save(entity );
        d.setId(entity.getId());
        d.setCreatedAt(entity.getCreatedAt());
        d.setName(entity.getName());
        d.setDisplayName(entity.getDisplayName());
        d.setStatus(entity.getStatus());
        return d;

    }

}
