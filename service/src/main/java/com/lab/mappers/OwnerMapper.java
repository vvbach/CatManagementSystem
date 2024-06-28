package com.lab.mappers;

import com.lab.dto.OwnerDto;
import lombok.experimental.UtilityClass;
import com.lab.models.Cat;
import com.lab.models.Owner;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class OwnerMapper {
    public OwnerDto toDto(Owner owner){
        List<Long> catIdList = new ArrayList<>();
        for (Cat cat : owner.getCatList()){
            catIdList.add(cat.getId());
        }
        return new OwnerDto(
                owner.getId(),
                owner.getName(),
                owner.getDateOfBirth(),
                catIdList);
    }
}
