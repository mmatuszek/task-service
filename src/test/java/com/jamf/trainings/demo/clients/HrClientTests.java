package com.jamf.trainings.demo.clients;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collection;
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
    Collection<Employee> employees = hrClient.getEmployeesForDepartment(Arrays.asList("c0538165-4dc2-44b8-9b58-67b850bb7369"));

    //then
    assertFalse(employees.isEmpty());
  }

  @Test
  public void shouldReturnSubordinates() {

    //when
    Collection<Employee> employees = hrClient.getSubordinates("123");

    //then
    assertFalse(employees.isEmpty());
  }

}
