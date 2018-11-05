package com.jamf.trainings.demo;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = NOT_FOUND)
public class BadRequestException extends IllegalArgumentException {

  public BadRequestException(String s) {
    super(s);
  }
}
