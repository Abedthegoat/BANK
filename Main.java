package BankingSystem;


import java.util.Scanner;

class BankAccount {
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (isValidAmount(amount)) {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Invalid input. Please enter a valid numeric amount for deposit.");
        }
    }

    public void withdraw(double amount) {
        if (isValidAmount(amount)) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawn $" + amount + ". New balance: $" + balance);
            } else {
                System.out.println("Insufficient funds. Current balance: $" + balance);
            }
        } else {
            System.out.println("Invalid input. Please enter a valid numeric amount for withdrawal.");
        }
    }

    public void checkBalance() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Current Balance: $" + balance);
    }

    private boolean isValidAmount(double amount) {
        return Double.isFinite(amount) && amount >= 0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = createAccount(scanner);

        while (true) {
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    depositOperation(scanner, account);
                    break;
                case 2:
                    withdrawOperation(scanner, account);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    exitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid menu option.");
            }
        }
    }

    private static BankAccount createAccount(Scanner scanner) {
        System.out.println("// WELCOME TO TCC BANK //");
        System.out.println();
            System.out.print("// CREATE AACOUNT //");
            System.out.println();
        System.out.print("Enter account holder name: ");
        String accountHolder = scanner.nextLine();

        double initialBalance;
        do {
            System.out.print("Enter initial balance: $");
            initialBalance = getValidAmount(scanner);
            if (initialBalance < 0) {
                System.out.println("Invalid initial balance. Please enter a non-negative value.");
            }
        } while (initialBalance < 0);

        return new BankAccount(accountHolder, initialBalance);
    }

    private static void depositOperation(Scanner scanner, BankAccount account) {
        System.out.print("Enter deposit amount: $");
        double amount = getValidAmount(scanner);
        account.deposit(amount);
    }

    private static void withdrawOperation(Scanner scanner, BankAccount account) {
        System.out.print("Enter withdrawal amount: $");
        double amount = getValidAmount(scanner);
        account.withdraw(amount);
    }

    private static void exitProgram() {
        System.out.println("Exiting TCC Bank. Thank you!");
        System.exit(0);
    }

    private static int getUserChoice(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid numeric choice.");
                // Consume the invalid input to avoid an infinite loop
                scanner.next();
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    private static double getValidAmount(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid numeric amount.");
                // Consume the invalid input to avoid an infinite loop
                scanner.next();
            }
        }
    }
}
