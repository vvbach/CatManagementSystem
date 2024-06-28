package com.lab.services;

import com.lab.exceptions.owner.NoSuchOwnerException;
import com.lab.mappers.CatMapper;
import com.lab.mappers.OwnerMapper;
import com.lab.dto.CatDto;
import com.lab.dto.OwnerDto;
import lombok.experimental.ExtensionMethod;
import com.lab.models.Cat;
import com.lab.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lab.repositories.OwnerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ExtensionMethod({OwnerMapper.class, CatMapper.class})
public class OwnerService {
    private final OwnerRepository ownerRepository;
    @Autowired
    public OwnerService(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }

    public List<OwnerDto> getAllOwner(){
        List<OwnerDto> ownerDtoList = new ArrayList<>();
        for(Owner owner : ownerRepository.findAll()){
            ownerDtoList.add(owner.toDto());
        }
        return ownerDtoList;
    }

    public OwnerDto findById(long id){
        if (!ownerRepository.existsById(id)){
            throw new NoSuchOwnerException();
        }
        return ownerRepository.getReferenceById(id).toDto();
    }

    public List<OwnerDto> findByName(String name){
        List<OwnerDto> ownerDtoList = new ArrayList<>();
        for(Owner owner : ownerRepository.findByName(name)){
            ownerDtoList.add(owner.toDto());
        }
        return ownerDtoList;
    }

    public List<CatDto> getAllCats(long id){
        if (!ownerRepository.existsById(id)){
            throw new NoSuchOwnerException();
        }
        List<CatDto> catDtoList = new ArrayList<>();
        for(Cat cat : ownerRepository.getReferenceById(id).getCatList()){
            catDtoList.add(cat.toDto());
        }
        return catDtoList;
    }

    public OwnerDto createOwner(OwnerDto ownerDto){
        Owner owner = new Owner();
        owner.setName(ownerDto.name());
        owner.setDateOfBirth(ownerDto.dateOfBirth());

        ownerRepository.save(owner);
        return owner.toDto();
    }

    public OwnerDto updateOwner(OwnerDto ownerDto){
        if (!ownerRepository.existsById(ownerDto.id())){
            throw new NoSuchOwnerException();
        }

        Owner owner = ownerRepository.getReferenceById(ownerDto.id());
        owner.setName(ownerDto.name());
        owner.setDateOfBirth(ownerDto.dateOfBirth());

        ownerRepository.save(owner);
        return owner.toDto();
    }

    public boolean deleteOwner(long id){
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        if (ownerOptional.isEmpty()){
            throw new NoSuchOwnerException();
        }
        ownerRepository.delete(ownerOptional.get());
        return true;
    }
}
