package com.jamf.trainings.demo.rest;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static java.util.Collections.synchronizedMap;
import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toList;

import java.util.Collection;
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
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;

import com.jamf.trainings.demo.domain.tasks.Employee;
import com.jamf.trainings.demo.domain.tasks.HrClient;
import com.jamf.trainings.demo.domain.tasks.Task;

@RestController
public class TasksController {

  private static final Logger LOG = LoggerFactory.getLogger(TasksController.class);

  private Map<String, Task> tasksRepo = synchronizedMap(new HashMap());

  @Autowired
  private HrClient hrClient;

  @GetMapping(path = "/tasks", params = "managerId")
  public Collection<TaskDto> find(
      @RequestParam("managerId") String managerId) {

    if (!StringUtils.hasText(managerId)) {
      throw new BadRequestException("managerId query parameter is required.");
    }

    Collection<Employee> managerEmployees = getEmployeesByManagerId(managerId);

    // return all tasks which are assigned to at least one employee of those manager
    return tasksRepo.values().stream()
        .map(task -> {

          TaskDto dto = new TaskDto();
          dto.setId(task.getId());
          dto.setDocumentId(task.getDocumentId());
          dto.setConfirmed(task.isConfirmed());

          Collection<String> dtoEmployees = task.getEmployees().stream()
              .filter(managerEmployees::contains)
              .map(employee -> format("%s %s", employee.getFirstName(), employee.getLastName()))
              .collect(toList());

          dto.setEmployees(dtoEmployees);

          return dto;
        })
        .collect(toList());
  }

  @PostMapping(path = "/tasks")
  public String create(@RequestBody NewTaskDto newTask) {

    if (!StringUtils.hasText(newTask.getDocumentId())) {
      throw new BadRequestException("documentId is required");
    }
    if (newTask.getDepartmentsId() == null || newTask.getDepartmentsId().isEmpty()) {
      throw new BadRequestException("departmentIds is required");
    }

    String id = randomUUID().toString();

    Task task = new Task();
    task.setId(id);
    task.setDocumentId(newTask.getDocumentId());

    try {
      task.setEmployees(hrClient.getEmployeesForDepartment(newTask.getDepartmentsId()));
    } catch (HttpStatusCodeException | ResourceAccessException e) {
      LOG.warn("Can not get employees.", e);
      task.setEmployees(emptyList());
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
      throw new NotFoundException(format("Task %s not found.", taskId));
    }
    task.setConfirmed(true);
  }

  private Collection<Employee> getEmployeesByManagerId(String managerId) {
    try {
      return hrClient.getSubordinates(managerId);
    } catch (HttpStatusCodeException | ResourceAccessException e) {
      LOG.warn("Can not get employees.", e);
      return emptyList();
    }
  }
}
