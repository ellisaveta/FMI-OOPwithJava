package Problem4;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RollDice {
    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Integer> results = new ArrayList<>();
        int dice;
        for (int i = 0; i < 6000000; i++) {
            dice = random.nextInt(6) + 1;
            results.add(dice);
        }

        System.out.printf("Face%10s\n", "Frequency");
        results.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach((x) -> System.out.printf("%d%10d\n", x.getKey(), x.getValue()));

    }
}
