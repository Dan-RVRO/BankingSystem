package com.mycompany.bankingsystem;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a bank account with an ID and funds.
 * Provides operations for deposit, withdrawal, and transfer based on proper validations.
 */
public class Cuenta {
    private final String accountId;  // Unique identifier for the account.
    private BigDecimal funds;

    /**
     * Default constructor.
     * Initializes funds to 0 and generates a unique account ID.
     */
    public Cuenta() {
        this(UUID.randomUUID().toString(), BigDecimal.ZERO);
    }
    
    /**
     * Constructs an account with an initial balance.
     *
     * @param funds the initial balance; if null, defaults to 0.
     * @throws IllegalArgumentException if funds is negative.
     */
    public Cuenta(BigDecimal funds) {
        this(UUID.randomUUID().toString(), funds);
    }
    
    /**
     * Private constructor to allow assignment of accountId.
     *
     * @param accountId the unique identifier for the account.
     * @param funds the initial balance.
     */
    private Cuenta(String accountId, BigDecimal funds) {
        Objects.requireNonNull(accountId, "accountId cannot be null.");
        if (funds == null) {
            this.funds = BigDecimal.ZERO;
        } else if (funds.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial funds cannot be negative.");
        } else {
            this.funds = funds;
        }
        this.accountId = accountId;
    }
    
    /**
     * Returns the unique account identifier.
     *
     * @return account ID.
     */
    public String getAccountId() {
        return accountId;
    }
    
    /**
     * Returns the current balance.
     *
     * @return current funds.
     */
    public BigDecimal getFunds() {
        return funds;
    }

    /**
     * Deposits a given amount into the account.
     *
     * @param amount the amount to deposit; must be positive.
     * @throws IllegalArgumentException if amount is null or not positive.
     */
    public void deposit(BigDecimal amount) {
        validateAmount(amount, "deposit");
        funds = funds.add(amount);
    }

    /**
     * Withdraws a given amount from the account.
     *
     * @param amount the amount to withdraw; must be positive and not exceed current funds.
     * @throws IllegalArgumentException if amount is null or not positive.
     * @throws IllegalStateException if there are insufficient funds for the withdrawal.
     */
    public void withdraw(BigDecimal amount) {
        validateAmount(amount, "withdraw");
        if (funds.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient funds for withdrawal.");
        }
        funds = funds.subtract(amount);
    }
    
    /**
     * Transfers a given amount from the current account to the target account.
     *
     * @param target the target account; must not be null.
     * @param amount the amount to transfer; must be positive and not exceed current funds.
     * @throws IllegalArgumentException if the target account is null.
     
    public void transfer(Cuenta target, BigDecimal amount) {
        Objects.requireNonNull(target, "Target account cannot be null.");
        this.withdraw(amount);
        target.deposit(amount); 
    }
    */
    
    /**
     * Validates that the provided amount is not null and positive.
     *
     * @param amount the amount to validate.
     * @param operationName the name of the operation (for clearer error messages).
     * @throws IllegalArgumentException if amount is null or non-positive.
     */
    private void validateAmount(BigDecimal amount, String operationName) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount for " + operationName + " cannot be null.");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount for " + operationName + " must be greater than zero.");
        }
    }
    
    /**
     * Returns a string representation of the account.
     *
     * @return string representation including account ID and funds.
     */
    @Override
    public String toString() {
        return "Cuenta {accountId='" + accountId + "', funds=" + funds + "}";
    }
    
    /**
     * Determines equality based on the unique account ID.
     *
     * @param o the object to compare with.
     * @return true if both accounts have the same ID, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cuenta)) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(accountId, cuenta.accountId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }
}