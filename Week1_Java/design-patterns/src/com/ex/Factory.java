package com.ex;

import java.util.HashMap;
import java.util.Map;

public class Factory {
    public static void main(String[] args) {
        StarshipFactory.registerClass("xwing", Xwing.class);
        StarshipFactory.registerClass("tie", TieFighter.class);

        Starship xwing = StarshipFactory.newStarship("xwing");
        Starship tie = StarshipFactory.newStarship("tie");

        System.out.println(xwing);
        System.out.println(tie);
        Starship ywing = StarshipFactory.newStarship("ywing");
    }
}

interface Starship {}
class Xwing implements Starship{}
class TieFighter implements Starship {}

class StarshipFactory {
    private static Map<String, Class> registry = new HashMap<>(); // contains the registry lookup information for constructing class instances
    public static Starship newStarship(String name) {
//        switch(name) {
//            case "xwing":
//                return new Xwing();
//            case "tie":
//                return new TieFighter();
//            default:
//                throw new IllegalArgumentException("Starship name " + name + " is not supported");
//        }
        Class clazz = registry.get(name);

        if(clazz == null) {
            // class not registered
            throw new IllegalArgumentException("Starship name " + name + " is not supported");
        }
        try {
            return (Starship) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void registerClass(String name, Class clazz) {
        registry.put(name, clazz);
    }
}
