import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    String name;
    double amount;

    Expense(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }
}

public class ExpenseTracker {

    static ArrayList<Expense> expenses = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static double totalBudget;
    static double totalSpent = 0;

    public static void main(String[] args) {

        System.out.print("Enter Your Total Budget: ");
        totalBudget = sc.nextDouble();

        int choice;

        do {
            System.out.println("\n===== DAILY EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Show Total Spending");
            System.out.println("4. Delete Expense");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    showSummary();
                    break;
                case 4:
                    deleteExpense();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);
    }

    static void addExpense() {
        System.out.print("Enter Expense Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        expenses.add(new Expense(name, amount));
        totalSpent += amount;

        System.out.println("Expense Added Successfully!");
        showRemaining();
    }

    static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.println("\n---- Expense List ----");
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i).name + " - " + expenses.get(i).amount);
        }
    }

    static void showSummary() {
        System.out.println("Total Budget: " + totalBudget);
        System.out.println("Total Spent: " + totalSpent);
        showRemaining();
    }

    static void showRemaining() {
        double remaining = totalBudget - totalSpent;
        System.out.println("Remaining Balance: " + remaining);

        if (remaining < 0) {
            System.out.println("⚠ Warning: You have exceeded your budget!");
        }
    }

    static void deleteExpense() {
        viewExpenses();

        if (expenses.isEmpty()) return;

        System.out.print("Enter expense number to delete: ");
        int index = sc.nextInt();

        if (index > 0 && index <= expenses.size()) {
            totalSpent -= expenses.get(index - 1).amount;
            expenses.remove(index - 1);
            System.out.println("Expense Deleted Successfully!");
            showRemaining();
        } else {
            System.out.println("Invalid expense number.");
        }
    }
}