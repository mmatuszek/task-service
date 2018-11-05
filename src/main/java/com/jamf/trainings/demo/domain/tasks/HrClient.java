package com.jamf.trainings.demo.domain.tasks;

import java.util.List;

public interface HrClient {

  List<Employee> getEmployeesForDepartment(List<String> deptIds);

  List<Employee> getSubordinates(String managerId);

}
