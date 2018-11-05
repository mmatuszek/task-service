package com.jamf.trainings.demo.domain.tasks;

import java.util.Collection;
import java.util.List;

public interface HrClient {

  Collection<Employee> getEmployeesForDepartment(Collection<String> deptIds);

  Collection<Employee> getSubordinates(String managerId);

}
