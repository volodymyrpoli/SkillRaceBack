package com.volodymyrpoli.skillrace.repository;

import com.volodymyrpoli.skillrace.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends JpaRepository<Level, Integer> {
}
