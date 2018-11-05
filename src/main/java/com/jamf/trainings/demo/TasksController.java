package com.jamf.trainings.demo;

import static java.util.Collections.emptyList;
import static java.util.Collections.synchronizedMap;
import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TasksController {

  private Map<String, Task> tasksRepo = synchronizedMap(new HashMap());

  @GetMapping(path = "/tasks", params = "managerId")
  public Collection<Task> find() {

    // TODO call for /subortinates by managerId
    Collection<String> employees = emptyList();

    // return all tasks which are assigned to at least one employee of those manager
    return tasksRepo.values().stream().filter(task -> {
      for (String employee : employees) {
        if (task.getEmployees().contains(employee)) {
          return true;
        }
      }
      return false;
    }).collect(toList());
  }

  @PostMapping(path = "/tasks")
  public String create(@RequestBody NewTaskDto newTask) {

    String id = randomUUID().toString();

    Task task = new Task();
    task.setId(id);
    task.setDocumentId(newTask.getDocumentId());

    // TODO call for employees by departmentIds
//    task.setEmployees();

    tasksRepo.put(id, task);

    return id;
  }

  @PutMapping(path = "/tasks/{taskId}")
  public void update(
      @PathVariable("taskId") String taskId) {

    Task task = tasksRepo.get(taskId);

    if (task == null) {
      // TODO 404
    }
  }
}
