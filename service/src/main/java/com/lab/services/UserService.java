package com.lab.services;

import com.lab.dto.CatDto;
import com.lab.dto.UserDto;
import com.lab.exceptions.user.UsernameAlreadyExistsException;
import com.lab.mappers.CatMapper;
import com.lab.mappers.UserMapper;
import com.lab.models.Cat;
import com.lab.models.Owner;
import com.lab.models.User;
import com.lab.repositories.OwnerRepository;
import com.lab.repositories.UserRepository;
import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ExtensionMethod({UserMapper.class, CatMapper.class})
public class UserService {
    public final UserRepository userRepository;
    public final OwnerRepository ownerRepository;
    @Autowired
    public UserService(UserRepository userRepository, OwnerRepository ownerRepository){
        this.userRepository = userRepository;
        this.ownerRepository = ownerRepository;
    }
    public UserDto createUser(UserDto userDto){
        if (userRepository.existsByUsername(userDto.username())){
            throw new UsernameAlreadyExistsException("This username is already taken");
        }

        Owner owner = new Owner();
        owner.setName(userDto.ownerDto().name());
        owner.setDateOfBirth(userDto.ownerDto().dateOfBirth());
        ownerRepository.save(owner);

        User user = new User();
        user.setUsername(userDto.username());
        user.setPassword(getEncoder().encode(userDto.password()));
        user.setRole(userDto.role());
        user.setOwner(owner);
        userRepository.save(user);
        return user.toDto();
    }

    public List<UserDto> getAllUser(){
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : userRepository.findAll()){
            userDtoList.add(user.toDto());
        }
        return userDtoList;
    }

    public List<CatDto> getAllCats(){
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       if (auth == null || auth instanceof AnonymousAuthenticationToken){
           return List.of();
       }
       String username = auth.getName();
       User user = userRepository.findByUsername(username);
       List<Cat> catList = user.getOwner().getCatList();
       List<CatDto> catDtoList = new ArrayList<>();
       for (Cat cat : catList){
           catDtoList.add(cat.toDto());
       }
       return catDtoList;
    }
    private PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
}
