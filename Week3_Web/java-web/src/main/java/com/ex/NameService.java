package com.ex;

import java.util.Arrays;
import java.util.List;

public class NameService {
    private List<String> names = Arrays.asList("August", "John", "Karen", "Eddie", "Lisa");

    public String getName(int id) {
        if(id < 0 || id > names.size()) return null;
        return names.get(id);
    }

    public List<String> getAllNames() {
        return names;
    }
}
