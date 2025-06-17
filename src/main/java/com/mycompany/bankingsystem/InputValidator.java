package com.mycompany.bankingsystem;

// Java core
import java.util.Scanner;
import java.util.Set;

// Bean-Validation API
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public final class InputValidator {
    
  //Creates factory and validator  
  private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
  private static final Validator VALIDATOR = FACTORY.getValidator();

  /**
   * Reads a line from the scanner, validates it against the given
   * beanType’s propertyName, and repeats until the value passes all
   * Bean-Validation constraints on that field.
   *
   * @param scanner     your Scanner(System.in)
   * @param beanType    the class with JSR-380 annotations
   * @param propertyName the name of the field you want to validate
   * @param prompt      text to show before reading
   * @return the first user input that yields zero violations
   */
  
  public static String checkInput(Scanner scanner, Class<?> beanType, String propertyName, String prompt) {
    while (true) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        Set<ConstraintViolation<?>> violations = (Set<ConstraintViolation<?>>) VALIDATOR.validateValue(beanType, propertyName, input);

      if (violations.isEmpty()) {
        return input;
      } violations.forEach(v -> System.out.println(v.getMessage()));
    }
  }
}
