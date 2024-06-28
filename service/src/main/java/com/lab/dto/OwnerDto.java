package com.lab.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
public record OwnerDto(
        long id,
        String name,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirth,
        List<Long> catIdList
) {}
