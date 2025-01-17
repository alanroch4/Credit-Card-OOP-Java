package br.com.ailacard.cardmain;

import java.util.ArrayList;
import java.util.List;

public class Card implements LimiteDefiner{
    private String name;
    private double limit;
    private double balance;
    private List<Shopping> purchases;

    public Card(String name) {
        this.name = name;
    }

    public boolean makeShop(Shopping shopping) {
        if (balance >= shopping.getPrice()) {
            this.balance -= shopping.getPrice();
            this.purchases.add(shopping);
            return true;
        }
        return false;
    }

    public List<Shopping> getPurchases() {
        return purchases;
    }

    public String getName() {
        return name;
    }

    public double getLimit() {
        return limit;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public void setLimit(double value) {
        if (value < 100 || value > 10000 ) {
            throw new IllegalArgumentException("Limite entre 100 e 10000");
        }
        this.limit = value;
        this.balance = value;
        this.purchases = new ArrayList<>();
    }
}
