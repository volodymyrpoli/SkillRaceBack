package com.volodymyrpoli.skillrace.controller;

import com.volodymyrpoli.skillrace.entity.Subtopic;
import com.volodymyrpoli.skillrace.entity.dto.SubtopicDTO;
import com.volodymyrpoli.skillrace.exception.NotFoundException;
import com.volodymyrpoli.skillrace.repository.LevelRepository;
import com.volodymyrpoli.skillrace.repository.SubtopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subtopics")
public class SubtopicController {

    private final SubtopicRepository subtopicRepository;
    private final LevelRepository levelRepository;

    @Autowired
    public SubtopicController(SubtopicRepository subtopicRepository, LevelRepository levelRepository) {
        this.subtopicRepository = subtopicRepository;
        this.levelRepository = levelRepository;
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
        subtopic.setName(subtopicDTO.getName());
        subtopic.setLevel(levelRepository.findById(subtopicDTO.getLevelId()).orElseThrow(() -> new NotFoundException("Not found level with id")));
        return subtopicRepository.save(subtopic);
    }

}
