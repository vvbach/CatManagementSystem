package com.lab.dto;

import com.lab.models.Color;

import java.time.LocalDate;
import java.util.List;


public record CatDto(
        long id,
        String name,
        LocalDate dateOfBirth,
        String breed,
        Color color,
        long ownerId,
        List<Long> catFriendsIdList
) {}
