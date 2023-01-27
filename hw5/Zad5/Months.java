package Zad5;

public enum Months {
    JAN("January"), FEB("February"), MAR("March"), APR("April"), MAY("May"),
    JUN("June"), JUL("July"), AUG("August"), SEB("September"), OCT("October"), NOV("November"), DEC("December");
    public static final String[] Month = new String[]{"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    private String month;

    private Months(String month){
        if(month != null) {
            this.month = month;
        }
        else {
            this.month = "January";
        }
    }

    @Override
    public String toString() {
        return String.format("%s", month);
    }
}
