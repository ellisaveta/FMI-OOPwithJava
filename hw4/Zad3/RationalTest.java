package Zad3;

public class RationalTest {
    public static void main(String[] args) {
        Rational r1 = new Rational(-1, -3);
        Rational r2 = new Rational(4, 12);

        System.out.printf("R1 = %s\n", r1);
        System.out.printf("R2 = %s\n", r2);
        System.out.printf("R1 + R2 = %s\n", r1.sumWith(r2));
        System.out.printf("R2 - R1 = %s\n", r1.subtractFrom(r2));
        System.out.printf("R1 * R2 = %s\n", r1.multiplyBy(r2));
        System.out.printf("R1 / R2 = %s\n", r1.divideBy(r2));

        r1.setNumerator(6);
        System.out.printf("R1 = %s\n", r1);

        r2.setDenominator(0);
        System.out.printf("R2 = %s\n", r2);

    }
}
