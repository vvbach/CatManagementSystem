package com.lab.mappers;

import com.lab.dto.CatDto;
import lombok.experimental.UtilityClass;
import com.lab.models.Cat;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CatMapper {
    public CatDto toDto(Cat cat){
        List<Long> catFriendIdList = new ArrayList<>();
        for(Cat friendCat : cat.getFriendList()){
            catFriendIdList.add(friendCat.getId());
        }
        return new CatDto(
                cat.getId(),
                cat.getName(),
                cat.getDateOfBirth(),
                cat.getBreed(),
                cat.getColor(),
                cat.getOwner().getId(),
                catFriendIdList);
    }
}
