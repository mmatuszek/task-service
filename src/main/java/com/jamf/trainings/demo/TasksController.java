package com.jamf.trainings.demo;

import static java.util.Collections.emptyList;
import static java.util.Collections.synchronizedMap;
import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpStatusCodeException;

import com.jamf.trainings.demo.domain.tasks.Employee;
import com.jamf.trainings.demo.domain.tasks.HrClient;

@RestController
public class TasksController {

  private static final Logger LOG = LoggerFactory.getLogger(TasksController.class);

  private Map<String, Task> tasksRepo = synchronizedMap(new HashMap());

  @Autowired
  private HrClient hrClient;

  @GetMapping(path = "/tasks", params = "managerId")
  public Collection<Task> find(
      @RequestParam("managerId") String managerId) {

    // TODO call for /subortinates by managerId
    try {
      Collection<Employee> employees = hrClient.getSubordinates(managerId);

      // return all tasks which are assigned to at least one employee of those manager
      return tasksRepo.values().stream().filter(task -> {
        for (Employee employee : employees) {
          if (task.getEmployees().stream().anyMatch(taskEmployee -> taskEmployee.equals(employee))) {
            return true;
          }
        }
        return false;
      }).collect(toList());

    } catch (HttpStatusCodeException e) {
      LOG.warn("Can not get employees.", e);
      return emptyList();
    }
  }

  @PostMapping(path = "/tasks")
  public String create(@RequestBody NewTaskDto newTask) {

    if(!StringUtils.hasText(newTask.getDocumentId())) {
      throw new BadRequestException("Document ID is required");
    }
    if(newTask.getDepartmentsId() == null || newTask.getDepartmentsId().isEmpty()) {
      throw new BadRequestException("Department IDs is required");
    }

    String id = randomUUID().toString();

    Task task = new Task();
    task.setId(id);
    task.setDocumentId(newTask.getDocumentId());

    try {
      task.setEmployees(hrClient.getEmployeesForDepartment(newTask.getDepartmentsId()));
    } catch (HttpStatusCodeException e) {
      LOG.warn("Can not get employees.", e);
    }
    tasksRepo.put(id, task);

    LOG.info("Saved {}: {}", id, task);

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
