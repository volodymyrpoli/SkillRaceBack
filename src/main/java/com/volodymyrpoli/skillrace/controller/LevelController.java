package com.volodymyrpoli.skillrace.controller;

import com.volodymyrpoli.skillrace.entity.Level;
import com.volodymyrpoli.skillrace.exception.NotFoundException;
import com.volodymyrpoli.skillrace.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("columns")
public class LevelController {

    private final ColumnRepository columnRepository;

    @Autowired
    public LevelController(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    @GetMapping
    public List<Level> getAll() {
        return this.columnRepository.findAll();
    }

    @GetMapping("{id}")
    public Level getOne(@PathVariable("id") Integer id) throws NotFoundException {
        return this.columnRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found level with id"));
    }

}
