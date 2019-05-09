package com.qa.controller;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qa.model.Task;
import com.qa.repository.TasklistRepository;


@RestController
@RequestMapping("/api/tasklist/")

public class TasklistController {
	
	@Autowired
	private TasklistRepository tasklistRepository;

	@RequestMapping(value = "alltasks/{username}", method = RequestMethod.GET)
    public List<Task> list(@PathVariable String username){
        return tasklistRepository.findByUsername(username);
    }
	@RequestMapping(value = "tasks", method = RequestMethod.GET)
    public List<Task> list(){
        return tasklistRepository.findAll();
    }
	@RequestMapping(value = "tasks", method = RequestMethod.POST)
    public Task create(@RequestBody Task task){
        return tasklistRepository.saveAndFlush(task);
    }
	@RequestMapping(value = "tasks/{id}", method = RequestMethod.GET)
    public Task get(@PathVariable Long id){
        return tasklistRepository.findOne(id);
    }
	@RequestMapping(value = "tasks/{id}", method = RequestMethod.PUT)
    public Task update(@PathVariable Long id, @RequestBody Task task){
		Task existingTask = tasklistRepository.findOne(id);
        BeanUtils.copyProperties(task, existingTask);
        return tasklistRepository.saveAndFlush(task);
    }
	@RequestMapping(value = "tasks/{id}", method = RequestMethod.DELETE)
    public Task delete(@PathVariable Long id){
        Task existingTask = tasklistRepository.findOne(id);
        tasklistRepository.delete(existingTask);
        return existingTask;
    }
}
