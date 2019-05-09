package com.qa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.model.Task;

public interface TasklistRepository extends JpaRepository<Task, Long>{
	List<Task> findByUsername(String username);
}
