package com.volodymyrpoli.skillrace.security;


import com.volodymyrpoli.skillrace.entity.ApplicationUser;

public class JwtApplicationUserFactory {

    private JwtApplicationUserFactory() {
    }

    public static JwtApplicationUser create(ApplicationUser user) {
        return new JwtApplicationUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
    }

}
