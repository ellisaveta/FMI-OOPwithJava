import javax.swing.*;

public class StockTest {
    public static void main(String[] args) {
        Stock stock1 = new Stock("A", "Apple");
        System.out.println(stock1);

        String oldPriceText = JOptionPane.showInputDialog("Please enter previous closing price : ");
        if(oldPriceText != null) {
            stock1.setPreviousClosingPrice(Double.parseDouble(oldPriceText));
        }

        String currentPriceText = JOptionPane.showInputDialog("Please enter current price: ");
        if(currentPriceText != null) {
            stock1.setCurrentPrice(Double.parseDouble(currentPriceText));
        }

        System.out.printf("Change of price by: %.2f%%\n", stock1.changePercent());

        System.out.printf("Old price: %.2f\n", stock1.getPreviousClosingPrice());
        System.out.printf("Current price: %.2f\n", stock1.getCurrentPrice());

    }
}
