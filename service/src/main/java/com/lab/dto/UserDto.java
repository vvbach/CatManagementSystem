package com.lab.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lab.models.Role;

import java.time.LocalDate;

public record UserDto (
        long id,
        String username,
        String password,
        Role role,
        OwnerDto ownerDto
) {}
