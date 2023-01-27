package Zad4;

public class StreamLab {
    public static void main(String[] args) {
        // TODO code application logic here

        SummaryStatistics stats = new SummaryStatistics();
        stats.setup();

        stats.averageWithJava();
        stats.statsWithStreamReduce();
        stats.allStatsWithStream();

        stats.statsWithStreamAverage();
        stats.statsWithStreamMin();
        stats.statsWithStreamReduce();

        stats.setupCompanies();
        stats.companyStats();
        stats.companyStatsWithStreamAverage();

        Employee.statistics();
    }
}
