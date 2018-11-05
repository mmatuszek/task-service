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
    Collection<Employee> employees = hrClient.getEmployeesForDepartment(Arrays.asList("b5c4e3c4-1da0-41d5-9583-341b07d689b4", "546b0e19-768f-4fa1-a46a-2b8de3d67bba"));

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
