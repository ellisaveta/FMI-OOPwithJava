package Zad3;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTesting {
    public static void main(String[] args) {
        int[] nums = new int[100];
        for (int i = 1; i < 101; i++) {
            nums[i - 1] = i;
        }
        IntStream stream = IntStream.of(nums);
        String str = stream
                .mapToObj(String::valueOf)
                .reduce("", (x, y) -> x + String.format("%s#", y));
        System.out.printf("%s%n", str);
        
        
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            numbers.add(random.nextInt(31));
        }
        Supplier<Stream<Integer>> streamSupplier = numbers::stream;
        streamSupplier.get().forEach(n -> System.out.printf("%d ", n));
        System.out.println();
        boolean dividableBy5 = streamSupplier.get().anyMatch((s) -> s % 5 == 0);
        if(dividableBy5)
            System.out.println("At least one number is divides by 5!");
        else
            System.out.println("No number divides by 5!");
        boolean allLessThan15 = streamSupplier.get().allMatch((s) -> s < 15);
        if(allLessThan15)
            System.out.println("All numbers are less than 15!");
        else
            System.out.println("Not all numbers are less than 15!");

        boolean bool = streamSupplier.get()
                .filter((s) -> (double)s > IntStream.of(streamSupplier.get().mapToInt(Integer::intValue).toArray()).average().getAsDouble())
                .count() > 5;
        if(bool){
            System.out.println("At least 5 numbers are larger than average!");
        }
        else
            System.out.println("Less than 5 numbers are larger then average!");
    }
}
