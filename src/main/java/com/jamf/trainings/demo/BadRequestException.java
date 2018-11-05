package com.jamf.trainings.demo;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = BAD_REQUEST)
public class BadRequestException extends IllegalArgumentException {

  public BadRequestException(String s) {
    super(s);
  }
}
