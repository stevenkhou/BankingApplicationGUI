package coe528.project;

/**
 *
 * @author steve
 * 
 * Comments for this class is pretty much the same as PlatCustomer.java
 */
public class GoldCustomer extends BankAccount{
    private String username, password;
    private int balance;

    public GoldCustomer(String username, String password, int balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    @Override
    public boolean login(String userParam, String passParam) {
        return username.equals(userParam) && password.equals(passParam);
    }
    
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public boolean logout() {
        return true;
    }

    @Override
    public boolean deposit(int amount) {
        if (amount >= 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean withdraw(int amount) {
        if (amount <= balance && amount >= 0) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean purchase(int price) {
        if (price < 50 || (price + 10) > balance) {
            return false;
        }
        else{
            balance -= (price + 10);
            return true;
        }
    }

    @Override
    public String toString() {
        return "Username: " + username + "\nPassword: " + password + "\nBalance: " + balance + "\nLevel: Gold";
    }
}
