package com.jamf.trainings.demo.domain.tasks;

import java.util.Collection;

import com.jamf.trainings.demo.domain.tasks.Employee;

public class Task {

  private String id;
  private String documentId;
  private Collection<Employee> employees;
  private boolean confirmed;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDocumentId() {
    return documentId;
  }

  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }

  public Collection<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Collection<Employee> employees) {
    this.employees = employees;
  }

  public boolean isConfirmed() {
    return confirmed;
  }

  public void setConfirmed(boolean confirmed) {
    this.confirmed = confirmed;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Task{");
    sb.append("id='").append(id).append('\'');
    sb.append(", documentId='").append(documentId).append('\'');
    sb.append(", employees=").append(employees);
    sb.append(", confirmed=").append(confirmed);
    sb.append('}');
    return sb.toString();
  }
}
