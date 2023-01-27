package Zad2;

import Zad1.Product;
import Zad1.Type;

import java.util.ArrayList;
import java.util.Objects;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.stream.Collectors;

public class ListOfProducts extends Product {
    private ArrayList<Product> products = new ArrayList<>();
    private Random random = new Random();

    public void Add(Product product)
    {
        if(product != null)
        {
            products.add(product);
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        if(products != null) {
            this.products = products;
        }
    }

    public String[] toArray() {
        ArrayList<String> strings = new ArrayList<>();
        for(Product product : products)
        {
            strings.add(product.toString());
        }
        return (String[]) strings.toArray();
    }

    public void setup() {
        for (int i = 0; i < 10; i++) {
            products.add(new Product("", Type.values()[random.nextInt(4)], random.nextDouble(50)));
        }
    }

    public double averagePrice() {
        OptionalDouble average = products.stream()
                .mapToDouble(Product::getPrice)
                .average();
        return average.getAsDouble();
    }

    @Override
    public String toString() {
        return products.stream()
                .map(Object::toString)
                .collect(Collectors.joining(",")).toString();
    }
}
