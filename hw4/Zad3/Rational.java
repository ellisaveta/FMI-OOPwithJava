package Zad3;

public class Rational {
    private int numerator;
    private int denominator;

    private int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b,a%b);
    }

    private void simplify()
    {
        int gcd;
        if(numerator >= denominator) {
            gcd = gcd(numerator, denominator);
        }
        else {
            gcd = gcd(denominator, numerator);
        }
        if(gcd < 0)
        {
            gcd = - gcd;
        }
        numerator = numerator / gcd;
        denominator = denominator / gcd;
    }

    public Rational() {
        setNumerator(1);
        setDenominator(1);
    }

    public Rational(int numerator, int denominator) {
        setNumerator(numerator);
        setDenominator(denominator);
        simplify();
    }

    public Rational(Rational rational)
    {
        if(rational != null) {
            setNumerator(rational.getNumerator());
            setDenominator(rational.getDenominator());
            simplify();
        }
        else {
            setNumerator(1);
            setDenominator(1);
        }
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
        if(denominator != 0) {
            simplify();
        }
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        if(denominator > 0) {
            this.denominator = denominator;
        }
        else if(denominator == 0){
            System.out.println("Division by 0!");
            this.denominator = 1;
        }
        else {
            this.numerator = -this.numerator;
            this.denominator = -denominator;
        }
        simplify();
    }

    @Override
    public String toString() {
        return String.format("%d / %d", numerator, denominator);
    }

    public Rational sumWith(Rational other) {
        if(other != null) {
            int newNumerator = (numerator * other.getDenominator()) + (other.getNumerator() * denominator);
            int newDenominator = denominator * other.getDenominator();

            Rational newRational = new Rational(newNumerator, newDenominator);
            newRational.simplify();

            return newRational;
        }
        return new Rational(this);
    }

    public Rational subtractFrom(Rational other) {
        if (other != null) {
            int newNumerator = (other.getNumerator() * denominator) - (numerator * other.getDenominator());
            int newDenomimator = other.getDenominator() * denominator;

            Rational newRational = new Rational(newNumerator, newDenomimator);
            newRational.simplify();

            return newRational;
        }
        return new Rational(this);
    }

    public Rational multiplyBy(Rational other) {
        if(other != null) {
            int newNumerator = numerator * other.getNumerator();
            int newDenominator = denominator * other.getDenominator();

            Rational newRational = new Rational(newNumerator, newDenominator);
            newRational.simplify();

            return newRational;
        }
        return new Rational(this);
    }

    public Rational divideBy(Rational other) {
        if(other != null) {
            int newNumerator = numerator * other.getDenominator();
            int newDenominator = denominator * other.getNumerator();

            Rational newRational = new Rational(newNumerator, newDenominator);
            newRational.simplify();

            return newRational;
        }
        return new Rational(this);
    }
}
