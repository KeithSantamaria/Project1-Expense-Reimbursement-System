package com.ex;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IceCream {
    private String flavorName;
    private double price;

    public IceCream() {}

    public String getFlavorName() {
        return flavorName;
    }

    public IceCream(String name, double price) {
        this.flavorName = name;
        this.price = price;
    }

    public void setFlavorName(String flavorName) {
        this.flavorName = flavorName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
