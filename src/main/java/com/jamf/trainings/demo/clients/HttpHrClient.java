package com.jamf.trainings.demo.clients;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jamf.trainings.demo.domain.tasks.Employee;
import com.jamf.trainings.demo.domain.tasks.HrClient;

@Component
public class HttpHrClient implements HrClient {

  private final static String HR_DEPARTMENT_URL = "http://192.168.1.111:8080/employees?departmentId=%s";
  private final static String HR_SUBORDINATES_URL = "http://192.168.1.111:8080/employees/%s/subordinates";

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public Collection<Employee> getEmployeesForDepartment(Collection<String> deptIds) {
    List<Employee> employees = new ArrayList<>();
    for (String id : deptIds) {
      String url = String.format(HR_DEPARTMENT_URL, id);
      List<Employee> employeesForDept = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
      }).getBody();
      employees.addAll(employeesForDept);
    }
    return employees;
  }

  @Override
  public Collection<Employee> getSubordinates(String managerId) {
    String url = String.format(HR_SUBORDINATES_URL, managerId);
    return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
    }).getBody();
  }


}
