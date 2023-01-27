package Lab8F20_problem1;

public class Money {
    private double lv;

    public Money() {
        lv = 0;
    }

    public Money(double lv) {
        setLv(lv);
    }

    public double getLv() {
        return lv;
    }

    public void setLv(double lv) {
        this.lv = lv;
    }

    public double LvToEur()
    {
        return 0.51 * lv;
    }

    public double LvToUsd()
    {
        return 0.58 * lv;
    }

    @Override
    public String toString() {
        return String.format("%.2f lv.", lv);
    }
}
