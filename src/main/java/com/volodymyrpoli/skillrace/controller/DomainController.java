package com.volodymyrpoli.skillrace.controller;

import com.volodymyrpoli.skillrace.entity.Domain;
import com.volodymyrpoli.skillrace.entity.dto.DomainDTO;
import com.volodymyrpoli.skillrace.exception.NotFoundException;
import com.volodymyrpoli.skillrace.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("domains")
public class DomainController {

    private final DomainRepository domainRepository;

    @Autowired
    public DomainController(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @GetMapping
    public List<Domain> getAll() {
        return domainRepository.findAll();
    }

    @GetMapping("{id}")
    public Domain getOne(@PathVariable("id") Integer id) throws NotFoundException {
        return domainRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found domain with this id"));
    }

    @PostMapping
    public Domain create(@RequestBody DomainDTO domainDTO) {
        Domain domain = new Domain();
        domain.setName(domainDTO.getName());
        domain.setRank(domainDTO.getRank());
        return domainRepository.save(domain);
    }
}
