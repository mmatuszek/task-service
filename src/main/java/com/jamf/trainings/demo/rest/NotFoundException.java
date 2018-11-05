package com.jamf.trainings.demo.rest;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = NOT_FOUND)
public class NotFoundException extends IllegalArgumentException {

  public NotFoundException(String s) {
    super(s);
  }
}
