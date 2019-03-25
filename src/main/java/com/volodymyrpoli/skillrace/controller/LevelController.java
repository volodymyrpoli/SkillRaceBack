package com.volodymyrpoli.skillrace.controller;

import com.volodymyrpoli.skillrace.entity.Level;
import com.volodymyrpoli.skillrace.entity.dto.LevelDTO;
import com.volodymyrpoli.skillrace.exception.NotFoundException;
import com.volodymyrpoli.skillrace.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("levels")
public class LevelController {

    private final LevelRepository levelRepository;

    @Autowired
    public LevelController(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @GetMapping
    public List<Level> getAll() {
        return this.levelRepository.findAll();
    }

    @GetMapping("{id}")
    public Level getOne(@PathVariable("id") Integer id) throws NotFoundException {
        return this.levelRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found level with id"));
    }

    @PostMapping
    public Level create(@RequestBody LevelDTO levelDTO) {
        Level level = new Level();
        level.setName(levelDTO.getName());
        level.setRank(levelDTO.getRank());
        level.setColor(levelDTO.getColor());
        return levelRepository.save(level);
    }

}
