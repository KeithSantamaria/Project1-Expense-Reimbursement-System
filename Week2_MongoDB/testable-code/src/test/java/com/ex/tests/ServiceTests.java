package com.ex.tests;

import com.ex.Dao;
import com.ex.Repository;
import com.ex.Service;
import com.ex.tests.mocks.MockDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ServiceTests {

    private Service service;
    private Repository dao;

    @Before
    public void initTestDependencies() {
//        dao = new Dao(); // this is using the production dao
        dao = new MockDao();
        service = new Service();
        service.setDao(dao);
    }

    @Test
    public void shouldReturnListWith1Item() {
        List list = (List)service.getAllData();
        Assert.assertEquals("From Mock Dao", list.get(0));
    }

}
