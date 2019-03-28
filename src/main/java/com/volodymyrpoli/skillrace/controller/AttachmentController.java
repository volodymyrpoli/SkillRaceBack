package com.volodymyrpoli.skillrace.controller;

import com.volodymyrpoli.skillrace.entity.Attachment;
import com.volodymyrpoli.skillrace.entity.Subtopic;
import com.volodymyrpoli.skillrace.entity.dto.AttachmentDTO;
import com.volodymyrpoli.skillrace.exception.NotFoundException;
import com.volodymyrpoli.skillrace.repository.AttachmentRepository;
import com.volodymyrpoli.skillrace.repository.SubtopicRepository;
import com.volodymyrpoli.skillrace.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("attachments")
@CrossOrigin(origins = "${cross.origin}")
public class AttachmentController {

    private final AttachmentRepository attachmentRepository;
    private final SubtopicRepository subtopicRepository;

    @Autowired
    public AttachmentController(AttachmentRepository attachmentRepository, SubtopicRepository subtopicRepository) {
        this.attachmentRepository = attachmentRepository;
        this.subtopicRepository = subtopicRepository;
    }
    @GetMapping
    public List<Attachment> getAll() {
        return attachmentRepository.findAll();
    }

    @GetMapping("{id}")
    public Attachment getOne(@PathVariable("id") Integer id) throws NotFoundException {
        return attachmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found Attachment with this id"));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Attachment create(@RequestBody AttachmentDTO attachmentDTO) throws NotFoundException {
        Attachment attachment = new Attachment();
        map(attachment, attachmentDTO);
        return attachmentRepository.save(attachment);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Integer id) {
        attachmentRepository.deleteById(id);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Attachment patch(@PathVariable("id") Integer id, @RequestBody AttachmentDTO attachmentDTO) throws NotFoundException {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new NotFoundException(""));
        map(attachment, attachmentDTO);
        return attachmentRepository.save(attachment);
    }

    private void map(Attachment attachment, AttachmentDTO attachmentDTO) throws NotFoundException {
        Mapper.mapBaseEntity(attachment, attachmentDTO);

        if (Objects.nonNull(attachmentDTO.getUrl())) {
            attachment.setUrl(attachmentDTO.getUrl());
        }

        if (Objects.nonNull(attachmentDTO.getSubtopicId())) {
            Subtopic subtopic = subtopicRepository.findById(attachmentDTO.getSubtopicId()).orElseThrow(() -> new NotFoundException(""));
            attachment.setSubtopic(subtopic);
        }
    }

}
