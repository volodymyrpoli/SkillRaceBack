package com.volodymyrpoli.skillrace.controller;

import com.volodymyrpoli.skillrace.entity.Domain;
import com.volodymyrpoli.skillrace.entity.dto.DomainDTO;
import com.volodymyrpoli.skillrace.exception.NotFoundException;
import com.volodymyrpoli.skillrace.repository.DomainRepository;
import com.volodymyrpoli.skillrace.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("domains")
@CrossOrigin(origins = "${cross.origin}")
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
    @PreAuthorize("hasRole('ADMIN')")
    public Domain create(@RequestBody DomainDTO domainDTO) {
        Domain domain = new Domain();
        map(domain, domainDTO);
        return domainRepository.save(domain);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Integer id) {
        domainRepository.deleteById(id);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Domain patch(@PathVariable("id") Integer id, @RequestBody DomainDTO domainDTO) throws NotFoundException {
        Domain domain = domainRepository.findById(id).orElseThrow(() -> new NotFoundException(""));
        map(domain, domainDTO);
        return domainRepository.save(domain);
    }

    private void map(Domain domain, DomainDTO domainDTO) {
        Mapper.mapBaseEntity(domain, domainDTO);

        if (Objects.nonNull(domainDTO.getRank())) {
            domain.setRank(domainDTO.getRank());
        }
    }

}
