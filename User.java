package coe528.project;
import java.io.*;

/**
 *
 * @author steve
 */
public class User {
    
    private String username, password;
    private int balance;
    private BankAccount currentAccount;
    
    public boolean authenticate(String username, String password) {
        boolean authenticated = false;
        
        try {
            FileReader fileReader = new FileReader(username + ".txt");
            BufferedReader reader = new BufferedReader(fileReader);
            
            String line = reader.readLine();
            String[] userInfo = line.split(", ");
            
            this.username = userInfo[0];
            this.password = userInfo[1];
            this.balance = Integer.parseInt(userInfo[2]);
            
            BankAccount account;
            if(this.balance < 10000) {
                account = new SilverCustomer(this.username, this.password, this.balance);
            } else if(this.balance >= 10000 && this.balance < 20000) {
                account = new GoldCustomer(this.username, this.password, this.balance);
            } else {
                account = new PlatCustomer(this.username, this.password, this.balance);
            }
            
            if (account.login(username, password)){
                currentAccount = account;
                authenticated = true;
            }             
        } catch (IOException | NumberFormatException event) {
            System.out.println("User doesn't exist");
        } 
        return authenticated;
    }
    
    public boolean duplicateUsername(String username) {
        File file = new File(username + ".txt");
        return file.exists();
    }
    
    public boolean logout() {
        return currentAccount.logout();
    }
    
    public void addCustomer(String username, String password) {
        File customerFile = new File(username + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customerFile))) {
            writer.write(username + ", " + password + ", " + 100);
        } catch (Exception e) {
            System.out.println("Error creating new user.");
        }
    }

    public void deleteCustomer(String username, String password) {
        File fileToDelete = new File(username + ".txt");
        if (!fileToDelete.delete()) {
            System.out.println("Error deleting user");
        }
    }
    
    public String getMembershipLevel() {
        if(currentAccount instanceof PlatCustomer) {
            return "Platinum";
        } else if (currentAccount instanceof GoldCustomer) {
            return "Gold";
        } else {
            return "Silver";
        }
    }
    
    public String getCustomerUsername() {
        return currentAccount.getUsername();
    }
    
    public void updateCustomer() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentAccount.getUsername() + ".txt"))) {
            writer.write(currentAccount.getUsername() + ", " + currentAccount.getPassword() + ", " + currentAccount.getBalance());
        } catch(IOException e) {
            System.out.println("Unable to update");
        }
        changeMembershipLevel();
    }
    
    private void changeMembershipLevel() {
        if(currentAccount.getBalance() < 10000) {
            currentAccount = new SilverCustomer(currentAccount.getUsername(), currentAccount.getPassword(), currentAccount.getBalance());        
        } else if(currentAccount.getBalance() >= 10000 && currentAccount.getBalance() < 20000) {
            currentAccount = new GoldCustomer(currentAccount.getUsername(), currentAccount.getPassword(), currentAccount.getBalance());
        } else {
            currentAccount = new PlatCustomer(currentAccount.getUsername(), currentAccount.getPassword(), currentAccount.getBalance());
        }
    }
    
    public int getBalance() {
        return currentAccount.getBalance();
    }
    
    public boolean deposit(int amount) {
        if(currentAccount.deposit(amount)) {
            updateCustomer();          
            return true;
        } else {
            return false;
        }
    }
    
    public boolean withdraw(int amount) {
        if(currentAccount.withdraw(amount)) {
            updateCustomer();
            return true;
        } else {
            return false;
        }
    }
    
    public boolean purchase(int price) {
        if(currentAccount.purchase(price)) {
            updateCustomer();
            return true;
        } else {
            return false;
        }
    }
}
