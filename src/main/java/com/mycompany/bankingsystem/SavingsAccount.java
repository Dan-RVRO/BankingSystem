package com.mycompany.bankingsystem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * A savings account that accrues interest and limits the number of free withdrawals per month.
 */
public class SavingsAccount extends Cuenta {
    private final BigDecimal annualInterestRate;    // e.g. 0.04 for 4% p.a.
    private final int maxMonthlyWithdrawals;        // free withdrawals per calendar month
    private int currentMonthWithdrawals;

    /**
     * Creates a savings account with initial balance, interest rate, and withdrawal limit.
     *
     * @param initialFunds         starting balance; must be non‐negative
     * @param annualInterestRate   annual interest rate as a decimal; e.g. 0.05 = 5%
     * @param maxMonthlyWithdrawals max free withdrawals per month; must be ≥ 0
     */
    public SavingsAccount(BigDecimal initialFunds,
                         BigDecimal annualInterestRate,
                         int maxMonthlyWithdrawals) {
        
    /**
     * Order of initialization
     * Java mandates that a superclass’s constructor runs before any subclass code. A super(...) call (or implicit super()) must be the very first statement.
     */
        super(initialFunds);
        
        if (annualInterestRate == null || annualInterestRate.compareTo(BigDecimal.ZERO) < 0 || annualInterestRate.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException(
              "Interest rate must be between 0 and 1 (inclusive).");
        }
        if (maxMonthlyWithdrawals < 0) {
            throw new IllegalArgumentException(
              "maxMonthlyWithdrawals cannot be negative.");
        }
        this.annualInterestRate = annualInterestRate;
        this.maxMonthlyWithdrawals = maxMonthlyWithdrawals;
        this.currentMonthWithdrawals = 0;
    }

    /**
     * Withdraws money, enforcing the monthly free‐withdrawal limit.
     * Beyond the limit, this throws an IllegalStateException.
     */
    @Override
    public void withdraw(BigDecimal amount) {
        if (currentMonthWithdrawals >= maxMonthlyWithdrawals) {
            throw new IllegalStateException(
              "Monthly free-withdrawal limit exceeded.");
        }
        super.withdraw(amount);
        currentMonthWithdrawals++;
    }

    /**
     * Applies one month's worth of interest to the balance.
     * Interest = funds × (annualRate / 12), rounded to 2 decimal places.
     */
    public void applyMonthlyInterest() {
        BigDecimal monthlyRate = annualInterestRate
            .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
        BigDecimal interest = getFunds()
            .multiply(monthlyRate)
            .setScale(2, RoundingMode.HALF_UP);
        deposit(interest);
    }

    /** Resets the monthly withdrawal counter (invoke at month-end). */
    public void resetMonthlyWithdrawals() {
        currentMonthWithdrawals = 0;
    }

    public BigDecimal getAnnualInterestRate() {
        return annualInterestRate;
    }

    public int getMaxMonthlyWithdrawals() {
        return maxMonthlyWithdrawals;
    }

    public int getCurrentMonthWithdrawals() {
        return currentMonthWithdrawals;
    }

    @Override
    public String toString() {
        return "SavingAccount {"
            + "id=" + getAccountId()
            + ", balance=" + getFunds()
            + ", rate=" + annualInterestRate
            + ", withdrawalsThisMonth=" + currentMonthWithdrawals
            + "/" + maxMonthlyWithdrawals
            + "}";
    }
}