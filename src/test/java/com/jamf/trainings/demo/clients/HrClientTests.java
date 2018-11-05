package com.jamf.trainings.demo.clients;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jamf.trainings.demo.domain.tasks.Employee;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HrClientTests {

  @Autowired
  HttpHrClient hrClient;

  @Test
  public void shouldReturnEmployees() {

    //when
    List<Employee> employees = hrClient.getEmployeesForDepartment(Arrays.asList("123", "345"));

    //then
    assertFalse(employees.isEmpty());
  }

  @Test
  public void shouldReturnSubordinates() {

    //when
    List<Employee> employees = hrClient.getSubordinates("123");

    //then
    assertFalse(employees.isEmpty());
  }

}
