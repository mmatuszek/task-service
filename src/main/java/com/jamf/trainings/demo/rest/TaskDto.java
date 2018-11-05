package com.jamf.trainings.demo.rest;

import java.util.Collection;

import com.jamf.trainings.demo.domain.tasks.Employee;

public class TaskDto {

  private String id;
  private String documentId;
  private Collection<String> employees;
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

  public Collection<String> getEmployees() {
    return employees;
  }

  public void setEmployees(Collection<String> employees) {
    this.employees = employees;
  }

  public boolean isConfirmed() {
    return confirmed;
  }

  public void setConfirmed(boolean confirmed) {
    this.confirmed = confirmed;
  }
}
