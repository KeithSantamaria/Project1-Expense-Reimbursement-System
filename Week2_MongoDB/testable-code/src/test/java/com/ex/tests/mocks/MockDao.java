package com.ex.tests.mocks;

import com.ex.Repository;

import java.util.Arrays;
import java.util.Collection;

public class MockDao implements Repository {
    @Override
    public Collection findAll() {
        return Arrays.asList("From Mock Dao");
    }
}
