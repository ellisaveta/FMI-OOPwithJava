package Zad4;

public class SavingsAccountTest {
    public static void main(String[] args) {
        SavingsAccount account1 = new SavingsAccount(1200.57);
        SavingsAccount account2 = new SavingsAccount(1221.83);

        System.out.printf("Account 1: %s\n", account1);
        System.out.printf("Account 2: %s\n", account2);
        if(account1.isGreater(account2)) {
            System.out.println("Account 1 has bigger savings balance than account 2!");
        }
        else {
            System.out.println("Account 1 hasn't bigger savings balance than account 2!");
        }

        account1.calculateMonthlyInterest();
        System.out.printf("Account 1: %s\n", account1);
        if(account1.isGreater(account2)) {
            System.out.println("Account 1 has bigger savings balance than account 2!");
        }
        else {
            System.out.println("Account 1 hasn't bigger savings balance than account 2!");
        }

        SavingsAccount.modifyInterestRate(3.76);

        SavingsAccount account3 = new SavingsAccount(account2);
        System.out.printf("Account 2: %s\n", account2);
        System.out.printf("Account 3: %s\n", account3);

        if(account3.isGreater(account2)) {
            System.out.println("Account 3 has bigger savings balance than account 2!");
        }
        else {
            System.out.println("Account 3 hasn't bigger savings balance than account 2!");
        }

        account3.calculateMonthlyInterest();
        System.out.printf("Account 2: %s\n", account2);
        System.out.printf("Account 3: %s\n", account3);
    }
}
