package com.ex;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Singleton {

    public static void main(String[] args) {
	   // DeathStar d1 = new DeathStar(); // doesn't work, the constructor is private
        DeathStar d1 = DeathStar.getInstance();
        DeathStar d2 = DeathStar.getInstance();

        System.out.println("Are d1 and d2 the same object: " + (d1 == d2));
        System.out.println(d1);
        System.out.println(d2);


        System.out.println(Color.YELLOW instanceof Color);

        LukeSkywalker l1 = LukeSkywalker.luke;
        LukeSkywalker l2 = LukeSkywalker.luke;

        System.out.println(l1 == l2);

        System.out.println(Jedi.ANAKIN.getTitle()); // ANAKIN is an instance of Jedi and has a title with value 'JEDI_KNIGHT'

    }
}

class DeathStar {
    private static DeathStar me;
    private DeathStar(){} // vulnerable to Reflection

    public static DeathStar getInstance() {
        if(me == null) {
            me = new DeathStar();
        }
        return me;
    }
}

enum LukeSkywalker {
    luke;

    LukeSkywalker() {}
}

enum Color  {
    RED, BLUE, GREEN, YELLOW, BLACK;
}

enum Jedi {
    ANAKIN("JEDI_KNIGHT"), // ANAKIN = new Jedi("JEDI_KNIGHT")
    LUKE("GRANDMASTER"),
    OBIWAN_KENOBI("MASTER");

    private final String title;

    public String getTitle() {
        return this.title;
    }

    Jedi(String title) {
        this.title = title;
    }
}
