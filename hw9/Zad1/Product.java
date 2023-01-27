package Zad1;

public class Product {
    private final long INV_NUMBER;
    private String invDescription;
    private Type category;
    private double price;
    private static long cnt = 1000;

    public Product() {
        setInvDescription(null);
        category = Type.A;
        price = 0.0;
        INV_NUMBER = cnt++;
    }

    public Product(String invDescription, Type category, double price) {
        setInvDescription(invDescription);
        this.category = category;
        this.price = price;
        INV_NUMBER = cnt++;
    }

    public Product(Product product)
    {
        setInvDescription(product.getInvDescription());
        price = product.price;
        category = product.category;
        INV_NUMBER = cnt++;
    }

    public double getPrice() {
        return price;
    }

    public Type getCategory() {
        return category;
    }

    public long getINV_NUMBER() {
        return INV_NUMBER;
    }

    public String getInvDescription() {
        return invDescription;
    }

    public void setInvDescription(String invDescription) {
        if (invDescription == null) {
            this.invDescription = "No description";
        }
        else {
            this.invDescription = invDescription;
        }
    }

    @Override
    public String toString() {
        return String.format("Product No:%d; \"%s\"; price:%.2f",
                INV_NUMBER, invDescription, price);
    }
}
