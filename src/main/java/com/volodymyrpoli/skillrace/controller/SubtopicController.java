package com.volodymyrpoli.skillrace.controller;

import com.volodymyrpoli.skillrace.entity.Subtopic;
import com.volodymyrpoli.skillrace.entity.dto.SubtopicDTO;
import com.volodymyrpoli.skillrace.exception.NotFoundException;
import com.volodymyrpoli.skillrace.repository.LevelRepository;
import com.volodymyrpoli.skillrace.repository.SubtopicRepository;
import com.volodymyrpoli.skillrace.repository.TopicRepository;
import com.volodymyrpoli.skillrace.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("subtopics")
public class SubtopicController {

    private final SubtopicRepository subtopicRepository;
    private final LevelRepository levelRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public SubtopicController(SubtopicRepository subtopicRepository, LevelRepository levelRepository, TopicRepository topicRepository) {
        this.subtopicRepository = subtopicRepository;
        this.levelRepository = levelRepository;
        this.topicRepository = topicRepository;
    }

    @GetMapping
    public List<Subtopic> getAll() {
        return subtopicRepository.findAll();
    }

    @GetMapping("{id}")
    public Subtopic getOne(@PathVariable("id") Integer id) throws NotFoundException {
        return subtopicRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found with this id"));
    }

    @PostMapping
    public Subtopic create(@RequestBody SubtopicDTO subtopicDTO) throws NotFoundException {
        Subtopic subtopic = new Subtopic();
        map(subtopic, subtopicDTO);
        return subtopicRepository.save(subtopic);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        subtopicRepository.deleteById(id);
    }

    @PatchMapping("{id}")
    public Subtopic patch(@PathVariable("id") Integer id, @RequestBody SubtopicDTO subtopicDTO) throws NotFoundException {
        Subtopic subtopic = subtopicRepository.findById(id).orElseThrow(() -> new NotFoundException(""));
        map(subtopic, subtopicDTO);
        return subtopicRepository.save(subtopic);
    }

    private void map(Subtopic subtopic, SubtopicDTO subtopicDTO) throws NotFoundException {
        Mapper.mapBaseEntity(subtopic, subtopicDTO);

        if (Objects.nonNull(subtopicDTO.getLevelId())) {
            subtopic.setLevel(levelRepository.findById(subtopicDTO.getLevelId()).orElseThrow(() -> new NotFoundException("Not found level with id")));
        }

        if (Objects.nonNull(subtopicDTO.getTopicId())) {
            subtopic.setTopic(topicRepository.findById(subtopicDTO.getTopicId()).orElseThrow(() -> new NotFoundException("Not found topic with id")));
        }

    }

}
