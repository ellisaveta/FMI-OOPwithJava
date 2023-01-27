package Zad2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<String> list = Stream.of("aBc", "d", "ef", "123456")
                .sorted()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(list);
    }
}
