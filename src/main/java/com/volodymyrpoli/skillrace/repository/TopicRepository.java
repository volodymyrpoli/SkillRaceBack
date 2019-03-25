package com.volodymyrpoli.skillrace.repository;

import com.volodymyrpoli.skillrace.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
}
