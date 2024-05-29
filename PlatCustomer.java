package coe528.project;

/**
 *
 * @author steve
 * 
 * The PlatCustomer class extends BankAccount to allow deposit, withdrawing, and purchasing.
 * A PlatCustomer has a login username and password as well as a balance.
 * This class is mutable since the balance can be changed through deposit, withdraw, and purchase
 * 
 *  AF(c) = A customer of platinum level with username c.username, password c.password, and balance c.balance
 * RI(c) = True if and only if username and password are both not null and not empty, and the balance is non-negative
 */

public class PlatCustomer extends BankAccount{
    private String username, password;
    private int balance;
    
    /** Initializes PlatCustomer customer's details
     * 
     * @param username is the customer's username
     * @param password is the customer's password
     * @param balance is the customer's initial balance
     */

    public PlatCustomer(String username, String password, int balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        assert repOK();
    }
    
    /** User login
     * 
     * @param userParam is the username input parameter
     * @param passParam password input parameter
     * @return True if login details are match, false otherwise
     */

    @Override
    public boolean login(String userParam, String passParam) {
        return username.equals(userParam) && password.equals(passParam);
    }
    
    /**
     * 
     * @return Customer's username
     */

    @Override
    public String getUsername() {
        return username;
    }
    
    /**
     * 
     * @return Customer's password
     */

    @Override
    public String getPassword() {
        return password;
    }
    
    /**
     * 
     * @return Customer's current balance 
     */

    @Override
    public int getBalance() {
        return balance;
    }
    
    /** User logout
     * 
     * @return True
     */ 

    @Override
    public boolean logout() {
        return true;
    }
    
    /** Deposits an amount to customer balance
     * 
     * @requires the amount to be non-negative
     * @modifies this.balance
     * @effects adds amount to the balance
     * 
     * @param amount value to added
     * @return True if deposited amount, false otherwise
     */

    @Override
    public boolean deposit(int amount) {
        if (amount >= 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }
    
    /** Withdraws an amount from customer balance
     * 
     * @requires the amount to be non-negative and less than the current balance
     * @modifies this.balance
     * @effects takes away amount from the balance
     * 
     * @param amount value to be removed
     * @return True if withdrew amount, false otherwise
     */

    @Override
    public boolean withdraw(int amount) {
        if (amount <= balance && amount >= 0) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
    
    /** Removes money from balance if amount is valid
     * 
     * @requires Price be greater than 50 and less than or equal to current balance
     * @modifies this.balance
     * @effects Price is taken away from balance if it is more than 50 and less than or equal to current balance
     * 
     * @param price Amount of the purchase
     * @return True if price is taken away from balance, false otherwise
     */

    @Override
    public boolean purchase(int price) {
        if (price < 50 || price > balance) {
            return false;
        }
        else{
            balance -= price;
            return true;
        }
    }
    
    /** Returns string representation of customer details
     * 
     * @return 
     */

    @Override
    public String toString() {
        return "Username: " + username + "\nPassword: " + password + "\nBalance: " + balance + "\nLevel: Platinum";
    }
    
    /**
     * 
     * @return True if RI holds, false otherwise
     */
    public boolean repOK(){
        return username != null && !username.isEmpty() && password != null && !password.isEmpty() && balance >= 0;
    }
}
