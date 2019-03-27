package com.volodymyrpoli.skillrace.security.controller;

import lombok.Data;

@Data
public class JwtAuthenticationRequest {

    private String username;
    private String password;

}
