package com.lab.mappers;

import com.lab.dto.UserDto;
import com.lab.models.User;
import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;

@UtilityClass
@ExtensionMethod(OwnerMapper.class)
public class UserMapper {
    public UserDto toDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getOwner().toDto()
        );
    }
}
