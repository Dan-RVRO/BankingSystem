package com.mycompany.bankingsystem;

import javax.validation.constraints.*;

public class User {
   
    @NotNull(message = "ID Number is required")
    @Size(min = 10, max = 10, message = "ID number must be exactly 10 digits")
    @Pattern(regexp = "\\d+", message = "ID number must contain digits only")
    private String idNumber;

    @NotNull(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    // at least one digit, one lowercase, one uppercase
    @Pattern(
      regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).+",
      message = "Password must include upper, lower, and a number"
    )
    private String password;

    @NotNull(message = "User ID is required")
    @Size(min = 3, max = 20, message = "User ID must be between 3 and 20 characters")
    @Pattern(
      regexp = "[A-Za-z0-9_]+",
      message = "User ID can only contain letters, digits or underscore"
    )
    private String userId;

    @NotNull(message = "PIN is required")
    @Pattern(regexp = "\\d{4}", message = "PIN must be exactly 4 digits")
    private String pin;

  
    // Constructor
    public User(String idNumber, String name, String email, String password, String userId, String pin){
        this.idNumber = idNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.pin = pin;
    }
    
    // Constructor vacío
    public User(){}
    
    // Getters
    public String getIdNumber(){
        return idNumber;
    }
        
    public String getName() {
        return name;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String getUserId(){
        return userId;
    }
    
    public String getPin(){
        return pin;
    }
    
    // Setter    
    public void setIdNumber(String idNumber){
        this.idNumber = idNumber;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setUserId(String userId){
        this.userId = userId;
    }
    
    public void setPin(String Pin){
        this.pin = Pin;
    }

    @Override
    public String toString() {
    
    /*
    * Ternary Operator (?:)
    * condition ? valueIfTrue : valueIfFalse;
    * Essentially, the condition checks whether there is a valid password to mask.
    */
    
    String maskedPassword = (password != null && !password.isEmpty()) ? "*****" : "";
    String maskedPin = (pin != null && !pin.isEmpty()) ? "****": "";
        
    return "User {idNumber=" + idNumber +
           ", name=" + name +
           ", email=" + email +
           ", userId=" + userId +
           ", password=" + maskedPassword +
           ", pin=" + maskedPin + "}";
    }
}
