package com.jamf.trainings.demo.clients;

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

  private final static String HR_DEPARTMENT_URL = "http://192.168.1.111:8080/departments/%s";
  private final static String HR_SUBORDINATES_URL = "http://192.168.1.111:8080/employees/%s/subordinates";

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public List<Employee> getEmployeesForDepartment(List<String> deptIds) {
    StringBuilder idsParam = new StringBuilder("&");
    for (String id : deptIds) {
      idsParam.append(id).append(",");
    }
    idsParam = new StringBuilder(idsParam.substring(0, idsParam.length() - 1));
    String url = String.format(HR_DEPARTMENT_URL, idsParam.toString());
    return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
    }).getBody();
  }

  @Override
  public List<Employee> getSubordinates(String managerId) {
    String url = String.format(HR_SUBORDINATES_URL, managerId);
    return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
    }).getBody();
  }


}
