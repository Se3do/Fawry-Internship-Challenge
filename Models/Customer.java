package Models;

public class Customer {
    private String name;
    private String email;
    private double balance;
    
    public Customer(String name, String email, double balance) {
        this.name = name;
        this.email = email;
        this.balance = balance;
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean purchase(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Purchase amount must be positive");
        }
        if (amount <= balance) {
            balance -= amount;
            return true;
        } 
        return false;
    }
}