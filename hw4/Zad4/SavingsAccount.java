package Zad4;

public class SavingsAccount {
    private static double mAnnualInterestRate = 5.6;
    private double mSavingsBalance;
    private final String mNumber;
    private static long cnt = 1000;

    public SavingsAccount() {
        setmSavingsBalance(0.0);
        mNumber = String.format("%010d", cnt++);
    }

    public SavingsAccount(double mSavingsBalance) {
        setmSavingsBalance(mSavingsBalance);
        mNumber = String.format("%010d", cnt++);
    }
    
    public SavingsAccount(SavingsAccount svAccount) {
        if(svAccount != null) {
            setmSavingsBalance(svAccount.getmSavingsBalance());
        }
        else {
            setmSavingsBalance(0.0);
        }
        mNumber = String.format("%010d", cnt++);
    }

    public double getmSavingsBalance() {
        return mSavingsBalance;
    }

    public void setmSavingsBalance(double mSavingsBalance) {
        if(mSavingsBalance >= 0.0) {
            this.mSavingsBalance = mSavingsBalance;
        }
        else {
            System.out.println("Invalid value of SavingsBalance!");
            this.mSavingsBalance = 0.0;
        }
    }

    public double getmAnnualInterestRate() {
        return mAnnualInterestRate;
    }

    public void calculateMonthlyInterest() {
        double monthlyInterest = (mSavingsBalance * mAnnualInterestRate) / 12;
        mSavingsBalance += monthlyInterest;
    }

    public static void modifyInterestRate(double newAnnualInterestRate) {
        if(newAnnualInterestRate >= 0.0) {
            SavingsAccount.mAnnualInterestRate = newAnnualInterestRate;
        }
        else {
            System.out.println("Negative value of new annual interest rate!");
        }
    }

    public boolean isGreater(SavingsAccount acc) {
        if(acc != null) {
            return this.mSavingsBalance > acc.getmSavingsBalance();
        }
        System.out.println("Null SavingsAccount for comparison!");
        return false;
    }

    @Override
    public String toString() {
        return String.format("mAnnualInterestRate: %.2f; mSavingsBalance: %.2f",
                mAnnualInterestRate, mSavingsBalance);
    }
}
