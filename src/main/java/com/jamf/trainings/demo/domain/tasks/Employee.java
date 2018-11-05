package com.jamf.trainings.demo.domain.tasks;

import java.util.Objects;

public class Employee {

  private String id;
  private Department department;
  private String firstName;
  private String lastName;

  public Employee() {
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getId() {
    return id;
  }

  public Department getDepartment() {
    return department;
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
        Objects.equals(department, employee.department) &&
        Objects.equals(firstName, employee.firstName) &&
        Objects.equals(lastName, employee.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, department, firstName, lastName);
  }

}
