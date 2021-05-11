package com.ex;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IceCreamService {

    private List<IceCream> icecream = new ArrayList<>(Arrays.asList(
       new IceCream("Cookie Dough", 1.99),
       new IceCream("Mint Chocolate Chip", 2.50),
       new IceCream("Maple Bacon Vanilla", 3.00),
       new IceCream("Shrek Surprise", 2.15),
       new IceCream("Dutch Chocolate", 1.99)
    ));

    public List<IceCream> getAllIceCream() {
        return icecream;
    }

    public IceCream getIceCreamFlavor(String flavorName) {
        return icecream.stream()
                .filter(i -> i.getFlavorName().equals(flavorName))
                .findFirst().orElse(null);
    }

    public void saveIceCream(IceCream obj) {
        icecream.add(obj);
    }
}
