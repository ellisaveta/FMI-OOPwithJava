package Zad1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MachineForChocolateEggs {
    ArrayList<ChocolateEgg> eggs = new ArrayList<>();
    Random random = new Random();
    double money;

    public MachineForChocolateEggs(double money) {
        this.money = money;
    }

    private ChocolateEgg ChooseEgg(int eggNumber)
    {
        ArrayList<ChocolateEgg> chooseFrom = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            chooseFrom.add(i, ChocolateEgg.values()[random.nextInt(10)]);
        }
        return chooseFrom.get(eggNumber - 1);
    }

    public void StartMachine()
    {
        while(money - 0.5 >= 0)
        {
            int choice;
            String[] possibilities = {"1", "2", "3", "4", "5"};
            String input = (String) JOptionPane.showInputDialog(new JFrame(), "Choose an egg!", "Machine",
                    JOptionPane.PLAIN_MESSAGE, null, possibilities, "1");
            choice = Integer.parseInt(input);
            ChocolateEgg chosenEgg = ChooseEgg(choice);
            eggs.add(chosenEgg);
            money -= 0.5;
        }
        print();
    }

    private void print()
    {
        System.out.println("You have collected these heroes:");
        Map<ChocolateEgg, Long> result = eggs.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(result);
    }
}
