public class Zad1 {
    public static void main(String[] args) {
        double probability = 0.0;
        int correctNums = 0;
        int divisibleBy12 = 0;

        for(int i = 10000; i<100000; ++i) {
            int currNum = i;
            if (currNum % 10 >= 6 && currNum % 10 <= 9) {
                currNum /= 10;
                if (currNum % 10 >= 1 && currNum % 10 <= 6) {
                    currNum /= 10;
                    if (currNum % 10 >= 4 && currNum % 10 <= 9) {
                        currNum /= 10;
                        if (currNum % 10 >= 2 && currNum % 10 <= 8) {
                            currNum /= 10;
                            if (currNum >= 3 && currNum <= 9) {
                                correctNums++;
                                if(i % 12 == 0) {
                                    divisibleBy12 ++;
                                }
                            }
                        }
                    }
                }
            }
        }

        probability = divisibleBy12 * (1.0) / correctNums;

        String output = String.format("Probabily is: %f\nThe number of numbers " +
                        "with these features are: %d\nFrom which divisible by 12 are: %d",
                probability, correctNums, divisibleBy12);

        System.out.println(output);
    }
}
