package coe528.project;

/**
 *
 * @author steve
 */
public abstract class BankAccount {
    
    abstract public boolean deposit(int amount);
    abstract public int getBalance();
    abstract public String getPassword();
    abstract public String getUsername();
    abstract public boolean login(String username, String password);
    abstract public boolean logout();
    abstract public boolean purchase(int price);
    abstract public boolean withdraw(int amount);

}
