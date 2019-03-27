package com.volodymyrpoli.skillrace.security.service;

import com.volodymyrpoli.skillrace.entity.ApplicationUser;
import com.volodymyrpoli.skillrace.repository.ApplicationUserRepository;
import com.volodymyrpoli.skillrace.security.JwtApplicationUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {


    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public JwtUserDetailsService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = applicationUserRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtApplicationUserFactory.create(user);
        }
    }
}
