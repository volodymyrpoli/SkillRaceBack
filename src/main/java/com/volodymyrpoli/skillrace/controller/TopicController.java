package com.volodymyrpoli.skillrace.controller;

import com.volodymyrpoli.skillrace.entity.Topic;
import com.volodymyrpoli.skillrace.entity.dto.TopicDTO;
import com.volodymyrpoli.skillrace.exception.NotFoundException;
import com.volodymyrpoli.skillrace.repository.DomainRepository;
import com.volodymyrpoli.skillrace.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("topics")
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
    public Topic create(@RequestBody TopicDTO topicDTO) throws NotFoundException {
        Topic topic = new Topic();
        topic.setName(topicDTO.getName());
        topic.setDomain(domainRepository.findById(topicDTO.getDomainId()).orElseThrow(() -> new NotFoundException("")));
        return topicRepository.save(topic);
    }
}
