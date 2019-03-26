package com.volodymyrpoli.skillrace.controller;

import com.volodymyrpoli.skillrace.entity.Level;
import com.volodymyrpoli.skillrace.entity.dto.LevelDTO;
import com.volodymyrpoli.skillrace.exception.NotFoundException;
import com.volodymyrpoli.skillrace.repository.LevelRepository;
import com.volodymyrpoli.skillrace.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("levels")
@CrossOrigin(origins = "${cross.origin}")
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
        return levelRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found level with id"));
    }

    @PostMapping
    public Level create(@RequestBody LevelDTO levelDTO) {
        Level level = new Level();
        map(level, levelDTO);
        return levelRepository.save(level);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        levelRepository.deleteById(id);
    }

    @PatchMapping("{id}")
    public Level patch(@PathVariable("id") Integer id, @RequestBody LevelDTO levelDTO) throws NotFoundException {
        Level level = levelRepository.findById(id).orElseThrow(() -> new NotFoundException(""));
        map(level, levelDTO);
        return levelRepository.save(level);
    }

    private void map(Level level, LevelDTO levelDTO) {
        Mapper.mapBaseEntity(level, levelDTO);

        if (Objects.nonNull(levelDTO.getColor())) {
            level.setColor(levelDTO.getColor());
        }

        if (Objects.nonNull(levelDTO.getRank())) {
            level.setRank(levelDTO.getRank());
        }
    }

}
