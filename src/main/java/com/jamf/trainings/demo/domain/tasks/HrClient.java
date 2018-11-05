package com.jamf.trainings.demo.domain.tasks;

import java.util.Collection;

public interface HrClient {

  Collection<Employee> getEmployeesForDepartment(Collection<String> deptIds);

  Collection<Employee> getSubordinates(String managerId);

}
