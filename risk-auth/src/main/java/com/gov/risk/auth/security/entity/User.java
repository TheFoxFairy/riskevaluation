package com.gov.risk.auth.security.entity;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer status;
    private List<String> roles;
}

