package com.volodymyrpoli.skillrace.controller;

import com.volodymyrpoli.skillrace.entity.Attachment;
import com.volodymyrpoli.skillrace.entity.BaseEntity;
import com.volodymyrpoli.skillrace.entity.Subtopic;
import com.volodymyrpoli.skillrace.entity.dto.SubtopicDTO;
import com.volodymyrpoli.skillrace.exception.NotFoundException;
import com.volodymyrpoli.skillrace.repository.AttachmentRepository;
import com.volodymyrpoli.skillrace.repository.LevelRepository;
import com.volodymyrpoli.skillrace.repository.SubtopicRepository;
import com.volodymyrpoli.skillrace.repository.TopicRepository;
import com.volodymyrpoli.skillrace.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("subtopics")
@CrossOrigin(origins = "${cross.origin}")
public class SubtopicController {

    private final SubtopicRepository subtopicRepository;
    private final LevelRepository levelRepository;
    private final TopicRepository topicRepository;
    private final AttachmentRepository attachmentRepository;

    @Autowired
    public SubtopicController(SubtopicRepository subtopicRepository, LevelRepository levelRepository, TopicRepository topicRepository, AttachmentRepository attachmentRepository) {
        this.subtopicRepository = subtopicRepository;
        this.levelRepository = levelRepository;
        this.topicRepository = topicRepository;
        this.attachmentRepository = attachmentRepository;
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
    @PreAuthorize("hasRole('ADMIN')")
    public Subtopic create(@RequestBody SubtopicDTO subtopicDTO) throws NotFoundException {
        Subtopic subtopic = new Subtopic();
        map(subtopic, subtopicDTO);
        return subtopicRepository.save(subtopic);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable("id") Integer id) {
        subtopicRepository.deleteById(id);
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
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

        if (Objects.nonNull(subtopicDTO.getAttachments())) {
            List<Attachment> existsAttachments = attachmentRepository.findAllById(
                    subtopicDTO.getAttachments().stream()
                            .filter(attachment -> Objects.nonNull(attachment.getId()))
                            .map(BaseEntity::getId)
                            .collect(Collectors.toList())
            );
            if (existsAttachments.size() != 0) {
                subtopic.setAttachments(existsAttachments);
            } else {
                subtopic.setAttachments(new ArrayList<>());
            }

            List<Attachment> newAttachment = subtopicDTO.getAttachments().stream()
                    .filter(attachment -> Objects.isNull(attachment.getId()))
                    .peek(attachment -> attachment.setSubtopic(subtopic))
                    .collect(Collectors.toList());
            List<Attachment> savedAttachments = attachmentRepository.saveAll(newAttachment);
            subtopic.getAttachments().addAll(savedAttachments);
        }

    }

}
