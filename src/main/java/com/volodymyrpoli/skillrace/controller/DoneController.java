package com.volodymyrpoli.skillrace.controller;

import com.volodymyrpoli.skillrace.entity.ApplicationUser;
import com.volodymyrpoli.skillrace.entity.Subtopic;
import com.volodymyrpoli.skillrace.entity.SubtopicWithDone;
import com.volodymyrpoli.skillrace.exception.NotFoundException;
import com.volodymyrpoli.skillrace.repository.ApplicationUserRepository;
import com.volodymyrpoli.skillrace.repository.SubtopicRepository;
import com.volodymyrpoli.skillrace.security.JwtApplicationUser;
import com.volodymyrpoli.skillrace.service.DoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("done")
@CrossOrigin(origins = "${cross.origin}")
public class DoneController {

    private final DoneService doneService;
    private final ApplicationUserRepository applicationUserRepository;
    private final SubtopicRepository subtopicRepository;

    @Autowired
    public DoneController(DoneService doneService, ApplicationUserRepository applicationUserRepository, SubtopicRepository subtopicRepository) {
        this.doneService = doneService;
        this.applicationUserRepository = applicationUserRepository;
        this.subtopicRepository = subtopicRepository;
    }

    @GetMapping
    public List<Subtopic> getAllForCurrentUser() {
        return doneService.getDoneForUser(getUser());
    }

    @PatchMapping("{id}")
    public SubtopicWithDone changeStatus(@PathVariable("id") Integer subtopicId, @RequestBody Map<String, Object> request) throws NotFoundException {
        Subtopic subtopic = subtopicRepository.findById(subtopicId).orElseThrow(() -> new NotFoundException(""));
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(getUser().getUsername());
        if ((Boolean) request.get("status")) {
            applicationUser.getDoneSubtopics().add(subtopic);
            applicationUserRepository.save(applicationUser);
            return SubtopicWithDone.getSubtopicWithDone(subtopic, Boolean.TRUE);
        } else {
            applicationUser.getDoneSubtopics().removeIf(subtopic1 -> subtopic1.getId().equals(subtopic.getId()));
            applicationUserRepository.save(applicationUser);
            return SubtopicWithDone.getSubtopicWithDone(subtopic, Boolean.FALSE);
        }
    }

    private JwtApplicationUser getUser() {
        return (JwtApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
