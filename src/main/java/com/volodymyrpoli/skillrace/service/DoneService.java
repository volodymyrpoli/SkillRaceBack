package com.volodymyrpoli.skillrace.service;

import com.volodymyrpoli.skillrace.entity.ApplicationUser;
import com.volodymyrpoli.skillrace.entity.Subtopic;
import com.volodymyrpoli.skillrace.repository.ApplicationUserRepository;
import com.volodymyrpoli.skillrace.security.JwtApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoneService {

    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public DoneService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public List<Subtopic> getDoneForUser(JwtApplicationUser jwtApplicationUser) {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(jwtApplicationUser.getUsername());
        List<Subtopic> doneSubtopics = applicationUser.getDoneSubtopics();
        return doneSubtopics;
    }
}
