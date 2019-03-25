package com.volodymyrpoli.skillrace.repository;

import com.volodymyrpoli.skillrace.entity.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtopicRepository extends JpaRepository<Subtopic, Integer> {
}
