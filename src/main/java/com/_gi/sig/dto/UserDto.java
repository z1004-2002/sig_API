package com._gi.sig.dto;

import java.util.UUID;

import com._gi.sig.models.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String login;
    private String phone;
    private Role role;
}
