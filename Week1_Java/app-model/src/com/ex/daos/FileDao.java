package com.ex.daos;

import java.util.Arrays;
import java.util.List;

public class FileDao implements Dao{
    @Override
    public List getAllData() {
        return Arrays.asList("From File Dao");
    }
}
