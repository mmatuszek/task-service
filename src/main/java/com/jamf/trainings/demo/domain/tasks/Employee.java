package com.jamf.trainings.demo.domain.tasks;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Objects;

public class Employee {

  private final String id;
  private final String deptId;
  private final String firstName;
  private final String lastName;

  @JsonCreator
  public Employee(String id, String deptId, String firstName, String lastName) {
    this.id = id;
    this.deptId = deptId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getId() {
    return id;
  }

  public String getDeptId() {
    return deptId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Employee employee = (Employee) o;
    return Objects.equals(id, employee.id) &&
        Objects.equals(deptId, employee.deptId) &&
        Objects.equals(firstName, employee.firstName) &&
        Objects.equals(lastName, employee.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, deptId, firstName, lastName);
  }
}
