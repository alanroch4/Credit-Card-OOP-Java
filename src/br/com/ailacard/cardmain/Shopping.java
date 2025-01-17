package br.com.ailacard.cardmain;

public class Shopping implements Comparable<Shopping>{
    private String product;
    private double price;

    public Shopping(String product, double price) {
        this.product = product;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Produto: " + product + "|" + "Valor = R$" + price;
    }

    @Override
    public int compareTo(Shopping anotherShopping) {
        return Double.valueOf(this.price).compareTo(Double.valueOf(anotherShopping.price));
    }
}
