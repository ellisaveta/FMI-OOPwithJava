package Zad4;

import java.util.*;
import java.util.stream.Collectors;


public class SummaryStatistics {
    private List<Double> numbers = new ArrayList<>();
    private List<Company> companies = new ArrayList<>();
    private Random rand = new Random();

    private class Company {

        private double revenue;

        public Company(double revenue) {
            this.revenue = revenue;
        }

        public double getRevenue() {
            return revenue;
        }
    }

    public void setupCompanies() {
        for (int i = 0; i < 10; i++) {
            companies.add(new Company(rand.nextInt(101) / 10.0));
        }
    }

    public void setup() {
        for (int i = 0; i < 10; i++) {
            numbers.add(rand.nextInt(101) / 10.0);
        }
    }

    public double companyStats() {
        DoubleSummaryStatistics stats = companies.stream()
                .collect(Collectors.summarizingDouble(Company::getRevenue));
        System.out.println("Company stats: " + stats);// print all the statistics!!!
        return stats.getAverage();
    }

    public double companyStatsWithStreamAverage() {
        // mapToDouble because it is a stream specialized to work with primitives!!!
        OptionalDouble average = companies.stream()
                .mapToDouble(c -> (c.revenue))
                .average();
        System.out.println("Stream company average: " + average);
        return  average.getAsDouble(); // return the average
    }

    public double averageWithJava() {
        double sum = 0d;
        for (double number : numbers) {
            sum += number;
        }
        double average = numbers.size() > 0 ? sum / numbers.size() : 0d;
        System.out.println("Java Numbers average: " + average);
        return average;
    }

    public double statsWithStreamAverage() {
        // mapToDouble because it is a stream specialized to work with primitives!!!
        OptionalDouble average = numbers.stream().mapToDouble(Double::doubleValue).average();
        System.out.println("Stream Numbers average: " + average);
        return average.getAsDouble();
    }

    public double statsWithStreamMin() {
        // mapToDouble because it is a stream specialized to work with primitives!!!
        OptionalDouble min = numbers.stream().mapToDouble(Double::doubleValue).min();
        System.out.println("Stream Numbers min: " + min);
        return min.getAsDouble();
    }

    public double statsWithStreamReduce() {
        // Find the sum
        Optional<Double> sum = numbers.stream().reduce((a, b) -> a + b);
        System.out.println("Stream Numbers sum: " + sum);
        return sum.get();
    }

    public double allStatsWithStream() {
        // Find the sum
        DoubleSummaryStatistics stats = numbers.stream().collect(Collectors.summarizingDouble(Double::doubleValue));
        //return stats.getAverage();
        // return stats.getCount();
        // return stats.getMin();
        System.out.println(stats);// print all the statistics!!!
        System.out.println("Stream Numbers all stats: " + stats);
        return stats.getSum();
    }
}
