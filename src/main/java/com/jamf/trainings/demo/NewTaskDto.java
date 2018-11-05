package com.jamf.trainings.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;

public class NewTaskDto {

  private String documentId;
  private Collection<String> departmentsId;

  @JsonCreator
  public NewTaskDto(
      @JsonProperty("documentId") String documentId,
      @JsonProperty("departmentIds") Collection<String> departmentIds) {
    this.documentId = documentId;
    this.departmentsId = departmentIds;
  }

  public String getDocumentId() {
    return documentId;
  }

  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }

  public Collection<String> getDepartmentsId() {
    return departmentsId;
  }

  public void setDepartmentsId(Collection<String> departmentsId) {
    this.departmentsId = departmentsId;
  }
}
