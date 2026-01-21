package com.desafio.validation;

import java.time.LocalDate;

public class Validators {
  public static boolean isAdult(LocalDate birthDate) {
    return birthDate != null && birthDate.plusYears(18).isBefore(LocalDate.now()) || birthDate.plusYears(18).isEqual(LocalDate.now());
  }
}
