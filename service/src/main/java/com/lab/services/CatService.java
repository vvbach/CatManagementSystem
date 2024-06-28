package com.lab.services;

import com.lab.dto.CatDto;
import com.lab.exceptions.cat.NoSuchCatException;
import com.lab.exceptions.owner.NoSuchOwnerException;
import lombok.experimental.ExtensionMethod;
import com.lab.mappers.CatMapper;
import com.lab.models.Cat;
import com.lab.models.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lab.repositories.CatRepository;
import com.lab.repositories.OwnerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@ExtensionMethod(CatMapper.class)
public class CatService {
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;
    @Autowired
    public CatService(CatRepository catRepository, OwnerRepository ownerRepository){
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
    }

    public CatDto findByID(long id){
        if (!catRepository.existsById(id)){
            throw new NoSuchCatException();
        }
        return catRepository.getReferenceById(id).toDto();
    }
    public List<CatDto> findByName(String name){
        List<CatDto> catDtoList = new ArrayList<>();
        for(Cat cat : catRepository.findByName(name)){
            catDtoList.add(cat.toDto());
        }
        return catDtoList;
    }

    public List<CatDto> findByBreed(String breed){
        List<CatDto> catDtoList = new ArrayList<>();
        for(Cat cat : catRepository.findByBreed(breed)){
            catDtoList.add(cat.toDto());
        }
        return catDtoList;
    }

    public List<CatDto> findByColor(Color color){
        List<CatDto> catDtoList = new ArrayList<>();
        for(Cat cat : catRepository.findByColor(color)){
            catDtoList.add(cat.toDto());
        }
        return catDtoList;
    }

    public List<CatDto> getAllCats(){
        List<CatDto> catDtoList = new ArrayList<>();
        for(Cat cat : catRepository.findAll()){
            catDtoList.add(cat.toDto());
        }
        return catDtoList;
    }

    public List<CatDto> getAllFriend(long id){
        if (!catRepository.existsById(id)){
            throw new NoSuchCatException();
        }
        List<CatDto> catDtoList = new ArrayList<>();
        for(Cat cat : catRepository.getReferenceById(id).getFriendList()){
            catDtoList.add(cat.toDto());
        }
        return catDtoList;
    }

    public CatDto createCat(CatDto catDto){
        if (!ownerRepository.existsById(catDto.ownerId())){
            throw new NoSuchOwnerException();
        }

        Cat cat = new Cat();
        cat.setName(catDto.name());
        cat.setBreed(catDto.breed());
        cat.setColor(catDto.color());
        cat.setDateOfBirth(catDto.dateOfBirth());
        cat.setOwner(ownerRepository.getReferenceById(catDto.ownerId()));

        catRepository.save(cat);
        return cat.toDto();
    }

    public CatDto updateCat(CatDto catDto){
        if (!catRepository.existsById(catDto.id())){
            throw new NoSuchOwnerException();
        }

        if (!ownerRepository.existsById(catDto.ownerId())){
            throw new NoSuchOwnerException();
        }
        Cat cat = catRepository.getReferenceById(catDto.id());
        cat.setName(catDto.name());
        cat.setBreed(catDto.breed());
        cat.setColor(catDto.color());
        cat.setDateOfBirth(catDto.dateOfBirth());
        cat.setOwner(ownerRepository.getReferenceById(catDto.ownerId()));

        catRepository.save(cat);
        return cat.toDto();
    }

    public boolean deleteCat(long id){
        if (!catRepository.existsById(id)){
            throw new NoSuchCatException();
        }
        catRepository.delete(catRepository.getReferenceById(id));
        return true;
    }

    public boolean addFriend(long mainCatId, long friendCatId){
        if (!catRepository.existsById(mainCatId)){
            throw new NoSuchCatException("Main cat not found");
        }
        if (!catRepository.existsById(friendCatId)) {
            throw new NoSuchCatException("Friend cat not found");
        }
        Cat mainCat = catRepository.getReferenceById(mainCatId);
        Cat friendCat = catRepository.getReferenceById(friendCatId);

        mainCat.getFriendList().add(friendCat);
        friendCat.getFriendList().add(mainCat);

        catRepository.save(mainCat);
        catRepository.save(friendCat);
        return true;
    }

    public boolean removeFriend(long mainCatId, long friendCatId){
        if (!catRepository.existsById(mainCatId)){
            throw new NoSuchCatException("Main cat not found");
        }
        if (!catRepository.existsById(friendCatId)) {
            throw new NoSuchCatException("Friend cat not found");
        }
        Cat mainCat = catRepository.getReferenceById(mainCatId);
        Cat friendCat = catRepository.getReferenceById(friendCatId);

        mainCat.getFriendList().remove(friendCat);
        friendCat.getFriendList().remove(mainCat);

        catRepository.save(mainCat);
        catRepository.save(friendCat);

        return true;
    }
}
