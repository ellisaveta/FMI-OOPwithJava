package Zad1;


import java.util.Scanner;

public class MachineTest {
    public static void main(String[] args) {
        System.out.print("How much money do you have for chocolate eggs: ");
        Scanner scanner = new Scanner(System.in);
        double money = scanner.nextDouble();
        MachineForChocolateEggs machine = new MachineForChocolateEggs(money);
        machine.StartMachine();
        System.exit(0);
    }
}
