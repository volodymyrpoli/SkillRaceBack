package com.volodymyrpoli.skillrace.controller;

import com.volodymyrpoli.skillrace.entity.Topic;
import com.volodymyrpoli.skillrace.entity.dto.TopicDTO;
import com.volodymyrpoli.skillrace.exception.NotFoundException;
import com.volodymyrpoli.skillrace.repository.DomainRepository;
import com.volodymyrpoli.skillrace.repository.TopicRepository;
import com.volodymyrpoli.skillrace.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("topics")
@CrossOrigin(origins = "${cross.origin}")
public class TopicController {

    private final TopicRepository topicRepository;
    private final DomainRepository domainRepository;

    @Autowired
    public TopicController(TopicRepository topicRepository, DomainRepository domainRepository) {
        this.topicRepository = topicRepository;
        this.domainRepository = domainRepository;
    }

    @GetMapping
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    @GetMapping("{id}")
    public Topic getOne(@PathVariable("id") Integer id) throws NotFoundException {
        return topicRepository.findById(id).orElseThrow(() -> new NotFoundException(""));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Topic create(@RequestBody TopicDTO topicDTO) throws NotFoundException {
        Topic topic = new Topic();
        topic.setName(topicDTO.getName());
        topic.setDomain(domainRepository.findById(topicDTO.getDomainId()).orElseThrow(() -> new NotFoundException("")));
        return topicRepository.save(topic);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Integer id) {
        topicRepository.deleteById(id);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Topic patch(@PathVariable("id") Integer id, @RequestBody TopicDTO topicDTO) throws NotFoundException {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new NotFoundException(""));
        map(topic, topicDTO);
        return topicRepository.save(topic);
    }

    private void map(Topic topic, TopicDTO topicDTO) throws NotFoundException {
        Mapper.mapBaseEntity(topic, topicDTO);

        if (Objects.nonNull(topicDTO.getDomainId())) {
            topic.setDomain(domainRepository.findById(topicDTO.getDomainId()).orElseThrow(() -> new NotFoundException("")));
        }
    }
}
